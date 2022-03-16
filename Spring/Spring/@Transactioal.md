# @Transactioal

**Transaction**
데이터베이스의 상태를 변경하는 작업 또는 수행되는 연산들을 의미하며, begin, commit을자동 수행하고 예외가 발생하면 rollback처리를 자동으로 수행

스프링에서는 어노테이션 방식으로 @Transactional을 클레스, 메소드에 선언하여 사용하며, 선언된 곳에는 트렌잭션 프록시 객체가 생성되어 commit, rollback을 해준다.

```java
@Transactional(readOnly = true) 
public class MemberService {
  @Transactional // default => readOnly = false 일기/쓰기 에서 true 를 하면 쓰기가 안된다.
  public void join(Member member) {
    memberRepository.save(member);
  }
}
```

@Transactional(readOnly = true) 를 선언하면 읽기 전용으로 데이터가 변경되어도 commit되지 않는다. 데이터의 변경이 없는 읽기 전용 메서드에사용되며, 영속성 컨텍스트를 플러시 하지 않으므로 약간의 성능 향상을 가질 수 있다.

JPA의 변경감지는 transaction이 commit될때 장동되며, Spring은 **@transactional 어노테이션을 선언한 메서드가 실행되기전, transaction begin 코드를 삽입** 하며 **메서드가 실행된 후, transaction commit코드를 삽입하여, 객체 변경감지를 수행**하게 유도한다

