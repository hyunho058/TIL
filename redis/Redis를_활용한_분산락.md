# Redis 활용한 분산락 

> 분산락과 Redis
>
> 분산락 적용 적용 코드

## 분산락과 Redis

분산 서비스 환경에서 여러 요청, 작업이 동일한 자원(공유 자원)에 접근하여 경쟁상태가 발생하지 않도록 원자성을 보장 하는 것이 분산락 입니다.

지난번 **[좌석예매를 통한 동시성 문제 알아보기](https://velog.io/@hyunho058/%EC%A2%8C%EC%84%9D%EC%98%88%EB%A7%A4%EB%A5%BC-%ED%86%B5%ED%95%9C-%EB%8F%99%EC%8B%9C%EC%84%B1-%EB%AC%B8%EC%A0%9C-%EC%95%8C%EC%95%84%EB%B3%B4%EA%B8%B0)**에서 MySQL Lock을 이용해 공유자원에 대한 동시성 문제를 해결하였는데 이번에는 Redis를 사용해 분산락을 적용하였습니다. 
(MySQL도 락을 사용할 수 있지만 lock과 관련된 부하를 RDS가 받는 점과 lock 정보가 휘발성 데이터라고 판단되어 Redis를 사용하는 것이 더 효율적이라 생각하였습니다.)

Redis는 메모리에 데이터를 저장하는 in-memory 데이터 베이스입니다.
single thread와 I/O Multiplexing을 조합하여 사용해 동시 요청을 처리합니다 (싱글스레드로 모든 요청을 처리하며, 상대적으로 시간이 걸리는 I/O작업은 백그라운드에서 여러 스레드를 사용합니다.)

![image](https://github.com/hyunho058/TIL/assets/58923731/93e5277c-4491-4c27-9e95-61e88d7f82e3)

[Redis document - Single threaded nature of Redis](https://redis.io/docs/management/optimization/latency/)

### Redis 분산락 

> Redis를 활용해 분산락을 적용 할 때 **스핀락 방식**과 Redisson을 이용한 **pub/sub방식**이 있습니다.

#### Lettuce

`Lettuce` 는 `setnx`, `setex` 명령어를 통해 Redis에 lock획득 또는 해제 요청을 보내며, lock을 얻지 못할 경우 처리를 직접 작성하여 분산락을 구현합니다.
대표적으로 스핀락이 있는데 lock을 얻지 못하면 반복적으로 lock을 얻기위해 요청하는 방식입니다. (요청이 많을수록 Redis에 부하가 받게됩니다.)
setnx(set if not exist) - key 값이 존재하지 않을 경우 값을 저장(MySQL의 named lock과 유사하게 lock을 획득할 수 있습니다.)

좌석예매 서비스는 lock을 얻지 못할 경우 이미 선택(예매) 좌석  으로 판단하여, lock을 획득하지 못한 요청이 반복적으로 Lock을 얻기위한 행위를 하지 않아도 되어 lettuce를 사용 하였습니다.

redis cli에 접속하여 간단하게 setnx명령어를 사용하는 방법에 대해 알아보겠습니다. 
`setnx key value`형식으로 명령어를 사용하며 반환 값이 1이면 lock획득에 성공한 것이며, 0이면 이미 요청하려는 key가 존재 하는 상태입니다.

```bash
MacBookPro > docker exec -it redis redis-cli
127.0.0.1:6379> setnx 1A22 lock
(integer) 1  => lock 획득
127.0.0.1:6379> setnx 1A22 lock
(integer) 0  => lock 실패(해당 key값이 존재)
127.0.0.1:6379> del 1A22
(integer) 1  => lock 삭제
127.0.0.1:6379> setnx 1A22 lock
(integer) 1
```

#### Redisson

`Redisson`은 **pub/sub 구조를 사용**하여 스핀락에 비해 Redis에 주는 부하를 줄여주며,. lock이 해제 될 때마다 subscribe하는 클라이언트에게 알림을 주어 Redis에 lock상태 조회 요청을 보내 체크를 하지 않아도 되도록 되어있습니다.



## 분산락 적용 코드

### Spring에 Redis 적용

> 버전 정보
> Spring boot:3.1.0
> Java:17
> Redis:7.0.11

```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-redis'
}
```

```yaml
spring:
  data:
    redis:
      host: 127.0.0.1
      port: 6379
      lettuce:
        pool:
          min-idle: 0
          max-idle: 8
          max-active: 8
```

```java
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories
public class RedisRepositoryConfig {
    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private int redisPort;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(redisHost, redisPort);
    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate() {
        RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        return redisTemplate;
    }
}
```

```java
public interface LockRepository {
    Boolean lock(String key, String value, Long leaseTime);
    void unlock(String key);
}
```

```java
import com.reservation.domain.performance.repository.LockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Slf4j
@RequiredArgsConstructor
@Repository
public class RedisLockRepository implements LockRepository {
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public Boolean lock(String key, String value, Long leaseTime) {
        log.info("START LOCK | KEY : {}", key);

        return redisTemplate.opsForValue()
            .setIfAbsent(key, value, Duration.ofMillis(leaseTime));
    }

    @Override
    public void unlock(String key) {
        redisTemplate.delete(String.valueOf(key));
        log.info("END LOCK | KEY : {}", key);
    }
}
```

동시성 문제를 해결하기 위해 하나의 작업이 비즈니스 로직 실행전 lock을 획득하고 작업이 완료(commit, rollback)된 이후 획득한 lock을 반환해야 합니다.
만약 commit 되기 전 lock을 반환해 버린다면, 동시성 문제가 발생하게 될 수도 있습니다.
예를 들어 A-10 좌석을 A유저가 예매를 진행하는 과정에 commit이 되기전 unlock이 되고 해당 시점에 B유저가 동일한 A-10 좌석을 예매하면 중복 예약이 되어버립니다.

아래 코드를 보면 하나의 작업 안에서 lock을 획득하고, commit 또는 rollback전에 반환하는 로직이 있는 코드입니다.

```java
@Transactional
public void create(Long performanceId, CreateReservationValue requestValue) {
    LockKey lockKey = new LockKey(
        performanceId, requestValue.seatLocation(), requestValue.seatNumber()
    );

    if (!lockRepository.lock(lockKey.combination(), lockKey.combination(), 3000L)){ //(1)
        throw new AlreadyReservedSeatException();
    }
  
		//...예약 로직
  
    lockRepository.unlock(lockKey.combination()); //(2)
}
```

![before_commit](https://github.com/hyunho058/TIL/assets/58923731/0da601ff-855f-4be4-aebb-9d37ee163218)

위 문제를 해결하기 위해 `event listener`와 `AOP` 두 가지 방법을 이용해 작업이 완료 되는 시점에  `unlock`을 실행할 수 있게 코드를 작성하였습니다.

> 1. Event Listener 사용
> 2. AOP 사용

### Event Listener를 이용한 코드 

spring에서 제공하는 `@TransactionalEventListener` 를 활용하여, 작업이 완료되는 시점에 unlock을 수행하는 코드를 실행하였습니다.
아래 코드에서 중요하게 볼건 @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)` 입니다.  
AFTER_COMPLETION는 이벤트를 발행한 작업에서 **commit 또는 rollback되었을때, 이벤트를 실행**하게 하는 옵션입니다. 이 외 AFTER_COMMIT, BEFORE_COMMIT, ROLLBACK 이 있습니다.

```java
import com.reservation.domain.performance.repository.LockRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class RedisLockListener {
    private final LockRepository lockRepository;

    public RedisLockListener(LockRepository lockRepository) {
        this.lockRepository = lockRepository;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
    public void unLockEvent(String lockKey) {
        lockRepository.unlock(lockKey);
    }
}
```

```java
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ReservationService {
    //...생략
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public void createReservation(Long performanceId, ReservationCreateValue requestValue) {
        LockKey lockKey = new LockKey(
                performanceId, requestValue.seatLocation(), requestValue.seatNumber()
        );

        if (!lockRepository.lock(lockKey.combination())){ //(1)
            throw new AlreadyReservedSeatException();
        } 
	      eventPublisher.publishEvent(lockKey.combination()); //(2)
      
        Performance performance = performanceRepository.findById(performanceId)
            .orElseThrow(PerformanceNotFoundException::new);

        Seat seat = seatRepository.findByPerformanceIdAndLocationAndNumber(
            performanceId, requestValue.seatLocation(), requestValue.seatNumber()
        ).orElseThrow(() -> new IllegalArgumentException("not found data"));

        if (seat.isReserved()) {
            throw new AlreadyReservedSeatException();
        }

        User user = userRepository.findById(requestValue.userId())
                .orElseThrow(UserNotFoundException::new);

        reservationRepository.save(
            new Reservation(
                user,
                performance,
                seat,
                LocalDateTime.now()
            )
        );

        seat.reserve();
      
        //eventPublisher.publishEvent(lockKey.combination()); //(3)
    }
```

아래 결과를 보면 정상적으로 작업이 완료(commit, rollback)시점에 unlockr가 동작하는 것을 확인 할 수 있습니다.

![unlock_after_commit](https://github.com/hyunho058/TIL/assets/58923731/3f45c20d-2b5e-43ce-ab10-c2bac2892cf2)

![unlock_event](https://github.com/hyunho058/TIL/assets/58923731/dee97175-abb2-47c3-9821-0c99c433be6d)

아래 결과 화면은 예외가 발생하였는데 unlock이 되지 않은 것인데요, 그 이유는 이벤트 발행하는 코드가 (3) 에 있을 경우 이러한 결과가 나오는데, **이벤트 발행 전 예외가 발생**하여 이벤트가 동작하지 않아서 입니다. 
그래서 이벤트 발행 코드는 lock을 획득 후 바로 이벤트를 발행을 하도록 하여 아래와 같은 상황을 방지할 수 있습니다.

![unlock_event_실패](https://github.com/hyunho058/TIL/assets/58923731/8c618998-9a50-4c7b-8f73-a7e607ba792d)

### AOP를 이용한 코드 

> aop는 프록시 패턴 이반으로 관심사(부가적인 기능)를 비즈니스 로직으로 부터 분리하여, 모듈로 만든것 입니ㅇ다.  spring에서 대표적으로 사용하는 aop는 `@Transactional`이 있습니다.

기존 event listener를 이용해 lock을 반환하는 코드를 작성 하였는데, 비즈니스 로직(예약)에서 lock을 획득하고 반환하는 코드가 부가적인 기능 이라고 생각 되어 aop를 활용해 에약(비즈니스 로직)과, 분산락(부가기능)을 처리하는 기능을 분리하였습니다. 

```java
@DistributedLock(leaseTime = 3000)
public void create(Long performanceId, CreateReservationValue requestValue) {
    LockKey lockKey = new LockKey(
        performanceId, requestValue.seatLocation(), requestValue.seatNumber()
    );

    if (!lockRepository.lock(lockKey.combination(), lockKey.combination(), 3000L)){
        throw new AlreadyReservedSeatException();
    }

    Performance performance = performanceRepository.findById(performanceId)
        .orElseThrow(() -> new IllegalArgumentException(ErrorCode.PERFORMANCE_NOT_FOUND.name()));

    Seat seat = seatRepository.findByPerformanceIdAndLocationAndNumber(
        performanceId,
        requestValue.seatLocation(),
        requestValue.seatNumber()
    ).orElseThrow(() -> new IllegalArgumentException(ErrorCode.SEAT_NOT_FOUND.name()));

    if (seat.isReserved()) {
        throw new IllegalArgumentException(ErrorCode.ALREADY_RESERVED_SEAT.name());
    }

    User user = userRepository.findById(requestValue.userId())
        .orElseThrow(() -> new IllegalArgumentException(ErrorCode.USER_NOT_FOUND.name()));

    Reservation reservation = reservationRepository.save(
        new Reservation(
            user,
            performance,
            seat,
            LocalDateTime.now()
        )
    );

    seat.reserve();
}
```

```java
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributedLock {
    String key() default "LOCK";

    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;

    /**
     * 락을 기다리는 시간 (락 획득을 위해 wait time 만큼 대기)
     */
    long waitTime() default 1000L;

    /**
     * 락 소유 시간 (lease time 이 지나면 락 해제)
     */
    long leaseTime() default 1000L;
}
```

```java
@Component
public class AopForTransaction {
    @Transactional
    public Object proceed(final ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed();
    }
}
```

```java
import com.reservation.application.performance.dto.CreateReservationValue;
import com.reservation.application.performance.exception.AlreadyReservedSeatException;
import com.reservation.domain.performance.repository.LockRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
@Slf4j
@Aspect
@Component
public class DistributedLockAop {
    private final LockRepository lockRepository;
    private final AopForTransaction aopForTransaction;

    public DistributedLockAop(LockRepository lockRepository, AopForTransaction aopForTransaction) {
        this.lockRepository = lockRepository;
        this.aopForTransaction = aopForTransaction;
    }

    @Around("@annotation(com.reservation.common.aop.DistributedLock) && args(performanceId,requestValue, ..)")
    public Object lock(final ProceedingJoinPoint joinPoint,
                       final Long performanceId,
                       final CreateReservationValue requestValue) throws Throwable {

        log.info("START LOCK AOP");

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DistributedLock distributedLock = method.getAnnotation(DistributedLock.class);

        String key = performanceId + requestValue.seatLocation() + requestValue.seatNumber();

        boolean lockAvailable =
                lockRepository.lock(key, "lock", distributedLock.leaseTime());
        log.info("lock");

        if (!lockAvailable) {
            throw new AlreadyReservedSeatException();
        }

        try {
            return aopForTransaction.proceed(joinPoint);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        } finally {
            try {
                lockRepository.unlock(key);
            } catch (IllegalMonitorStateException e) {
                log.error("Already unLock {} {}", method.getName(), key);
            }
            log.info("END LOCK AOP");
        }
    }
}
```







[Guide to Redis with Redisson](https://www.baeldung.com/redis-redisson)

