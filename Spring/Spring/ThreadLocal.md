# ThreadLocal

Spring에서 Bean을 Singleton으로 관리하기때문에 여러 thread가 bean객체에 접근을 하게 되면 동시성 문제가 발생하며, 이 문제점을 해결하기 위해 ThreadLocal을 사용 한다.

ThreadLocal은 해당 thread만 접근할 수 있는 저장소를 말한다. `ThreadLocal`을 사용하면 각 쓰레드마다 별도의 내부 저장소를 제공하여 같은 인스턴스의 `ThreadLocal`에 접근해도 문제가 발생하지 않는다.

## ThreadLocal 사용법

```java
private ThreadLocal<Type> threadLocal = new ThreadLocal<>();
```

값 저장 - threadLocal.set(value);
값 조회 - threadLocal.get();
값 제거 - threadLocal.remove();

Thread가 ThreadLocal을 모두 사용하고 나면 `threadLocal.remove();`를호출해서 ThreadLocal에 저장된 값을 제거해주어야한다 

## 예제

* ThreadLocal적용 X

```java
@Slf4j
public class FieldService {
    private  String nameStore;

    public String logic(String name){
        log.info("저장 name = {} -> nameStore={}", name, nameStore);
        nameStore = name;
        sleep(1000);
        log.info("조회 nameStore={}", nameStore);
        return nameStore;
    }
    private void sleep(int millis) {
        try{
            Thread.sleep(millis);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
```

```java
@Slf4j
public class FieldServiceTest {

    private FieldService fieldService = new FieldService();

    @Test
    void field() {
        log.info("main start");
        Runnable userA = () -> {
            fieldService.logic("userA");
        };
        Runnable userB = () -> {
            fieldService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        sleep(100);//동시성 문제 발생
        threadB.start();
        //countdown letgi
        sleep(3000);//메인 쓰레드 종료 대기
        log.info("main exit");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

<img width="1149" alt="스크린샷 2021-11-17 오후 7 44 17" src="https://user-images.githubusercontent.com/58923731/142186142-14666b9f-0ad2-4f28-96ea-d86cfe708642.png">

위 코드는 하나의 필드인`FieldService`의 `nameStore` 에 두개의 쓰레드가 접근하여 `ThreadA`가 `nameStore` 값을 출력하기전에 `ThreadB`가 `nameStore` 값을 변경하여 위와같은 문제가 발생

* ThreadLocal적용

```java
@Slf4j
public class ThreadLocalFieldService {

    private ThreadLocal<String> nameStore = new ThreadLocal<>();

    public String logic(String name) {
        log.info("저장 name = {} -> nameStore={}", name, nameStore.get());
        nameStore.set(name);
        sleep(1000);
        log.info("조회 nameStore={}", nameStore.get());
        return nameStore.get();
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

```java
@Slf4j
public class ThreadFieldServiceTest {

    private ThreadLocalFieldService service = new ThreadLocalFieldService();

    @Test
    void field() {
        log.info("main start");
        Runnable userA = () -> {
            service.logic("userA");
        };
        Runnable userB = () -> {
            service.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        sleep(100);
        threadB.start();
        //countdown letgi
        sleep(3000);//메인 쓰레드 종료 대기
        log.info("main exit");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

<img width="1253" alt="스크린샷 2021-11-17 오후 7 41 19" src="https://user-images.githubusercontent.com/58923731/142185756-fb246611-0dc0-452e-b47a-5a33fd4a7124.png">

