# @Transactioal

* 트랜잭션, 영속성 컨택스트
* 데이터베이스 드라이버가 지원하면 DB에서 성능 향상
* **transaction begin, commit**을자동 수행해준다
  * .JPA의 객체 변경감지는 transaction 이 commit될떄 , 작동된다
  * Spring은 **@transactional 어노테이션을 선언한 메서드가 실행되기전, transaction begin 코드를 삽입** 하며 **메서드가 실행된 후, transaction commit코드를 삽입하여, 객체 변경감지를 수행**하게 유도한다.
* **예외를 발생시키면, rollback처리를 자동 수행해준다**
* JPA의 모든 데이터변경이나 로직는 Transaction안에서 실행해햐한다
  * Lazy로딩 가능
* 일기 전용에서는 -  @Transactional(readOnly = true) 
  * 데이터가 변경되지 않음
  * readOnly=true 
    * default- false
    * **데이터의 변경이 없는 일기 전용 메서드에 사용**, 영석성 컨택스트를 플러시 하지 않으므로 약간의 성능 향상(읽기 전용에는 다 적용한다)
    * 더티체킹을 하지 않음
* 데이터 변경하는 로직에서는 - @Transactional

```java
@Service
@Transactional(readOnly = true) //일기 전용에서는 readOnly = true 를 해주면 트렌젝션이 최적화 해서 조회를 한다(성능 향상)
@RequiredArgsConstructor //final 이 있는 필드를 가지고 생성자를 만들어줌
public class MemberService {
    
    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     *
     * @param member
     * @return
     */
    @Transactional // default => readOnly = false 일기/쓰기 에서 true 를 하면 쓰기가 안된다.
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증 memberRepository.save(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName()); //member.getName() 를 유니크 제약조건을 달아야 한다 (방어코드)
        if (!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원이빈다.");
        }
        //조회된 맴버 수 가 0보다 크면 중복 아이디가 있음을 확인하는 로직이 더 최적화된 로직이다.
        //TODO : 조회된 맴버 수가 0 이상이면 예외 발생


        //해당 메서드는 두명의 유저가 동시에 "A" 라는 아이디로 회워가입을 하게되면 문제가 발생한다(A라는 회원이 두명이 된다)
    }

    /**
     * 회원 전체 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

    @Transactional  //Transaction 이 끝날때 commit 발생
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);   //영속상태
        member.setName(name);   //변경 감지
    }
}
```





* javax.persistence.TransactionRequiredException: No EntityManager with actual transaction available for current thread - cannot reliably process 'remove' call - 해당 err발생

```
2021-05-27 08:52:45.081 ERROR 3135 --- [nio-9112-exec-8] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is org.springframework.dao.InvalidDataAccessApiUsageException: No EntityManager with actual transaction available for current thread - cannot reliably process 'remove' call; nested exception is javax.persistence.TransactionRequiredException: No EntityManager with actual transaction available for current thread - cannot reliably process 'remove' call] with root cause
javax.persistence.TransactionRequiredException: No EntityManager with actual transaction available for current thread - cannot reliably process 'remove' call
```



## Reference

[https://mommoo.tistory.com/92](https://mommoo.tistory.com/92)

