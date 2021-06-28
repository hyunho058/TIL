# Exists



## Spring Data Jpa와 JPQL을 이용한 방법

* Spring Data Jpa에서 데이터가 존재하는지 확인 하기 위해 `exists` 쿼리를 사용한다.
* 간단한 쿼리의 경우엔 아래와 같이 메소드로 쿼리를 만들어서 사용.
  * 복잡하게 되면 메소드명으로만 쿼리를 표현하기 어려운 단점이 있다.
  * JpaRepository의 메소드 쿼리에선 내부적으로 `limit 1`를 사용하고 있어서 성능상 이슈가 없다.

```java
//reviewNo로 존재 여부 확인
boolean existsByReviewNo(long reviewNo);
```



* @Query를 이용한 exists
  * 단점은 JPQL에서 **select의 exists 를 지원하지 않는다.**. (`select exists` 문법)
  * **count** 쿼리를 사용하여 exists 조회

```java
@Query("select count (br.reviewNo) > 0 " +
            "from BookReview br " +
            "where br.userInfo.userNo = :userNo and br.book.isbn = :isbn")
boolean exists(@Param(value = "userNo") long userNo,@Param(value = "isbn") String isbn);
```

```bash
select
	count(bookreview0_.review_no)>0 as col_0_0_ 
from
	book_review bookreview0_ 
where
	bookreview0_.user_no=? 
	and bookreview0_.isbn=?
2021-06-27 13:53:20.752  INFO 8901 --- [nio-0000-exec-1] c.s.b.service.BookReviewService          : bookReviewRating:: review is not existence
```



## QueryDSL을 이용한 방법

* Querydsl로 구현

```java
@Override
public boolean exists(long userNo, String isbn) {
  Integer fetchOne = queryFactory
    .selectOne()
    .from(bookReview)
    .where(bookReview.userInfo.userNo.eq(userNo).and(book.isbn.eq(isbn)))
    .fetchFirst();
  return fetchOne != null;
}
```

```bash
select
	1 as col_0_0_ 
from
	book_review bookreview0_ 
where
	bookreview0_.user_no=? 
	and bookreview0_.isbn=? limit ?
2021-06-28 23:37:52.337  INFO 16284 --- [nio-0000-exec-1] c.s.b.r.BookReviewRepositoryImpl         : exists():: fetchOne is 1
2021-06-28 23:37:52.341  INFO 16284 --- [nio-0000-exec-1] c.s.b.service.BookReviewService          : createReview:: review is existence
```



## 정리

* `count`는 `exists`에 비해 성능 이슈가 존재한다.
  * count`의 경우엔 결국 **총 몇건인지 확인하기 위해** 전체를 확인해야한다.
  * `exists`는 **첫번째 결과에서 바로** `true` 를 리턴



[reference](https://jojoldu.tistory.com/372)
