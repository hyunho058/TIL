# @Transactioal

* 트랜잭션, 영속성 컨택스트

* readOnly=true 

  * 데이터의 변경이 없는 일기 전용 메서드에 사용, 영석성 컨택스트를 플러시 하지 않으므로 약간의 성능 향상(읽기 전용에는 다 적용한다)

* 데이터베이스 드라이버가 지원하면 DB에서 성능 향상

* **transaction begin, commit**을자동 수행해준다

* **예외를 발생시키면, rollback처리를 자동 수행해준다**

* .JPA의 객체 변경감지는 transaction 이 commit될떄 , 작동된다

* Spring은 **@transactional 어노테이션을 선언한 메서드가 실행되기전, transaction begin 코드를 삽입** 하며 **메서드가 실행된 후, transaction commit코드를 삽입하여, 객체 변경감지를 수행**하게 유도한다.

  



* javax.persistence.TransactionRequiredException: No EntityManager with actual transaction available for current thread - cannot reliably process 'remove' call - 해당 err발생

```
2021-05-27 08:52:45.081 ERROR 3135 --- [nio-9112-exec-8] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is org.springframework.dao.InvalidDataAccessApiUsageException: No EntityManager with actual transaction available for current thread - cannot reliably process 'remove' call; nested exception is javax.persistence.TransactionRequiredException: No EntityManager with actual transaction available for current thread - cannot reliably process 'remove' call] with root cause
javax.persistence.TransactionRequiredException: No EntityManager with actual transaction available for current thread - cannot reliably process 'remove' call
```



## Reference

[https://mommoo.tistory.com/92](https://mommoo.tistory.com/92)

