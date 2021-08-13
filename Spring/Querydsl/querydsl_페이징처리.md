# Querydsl_페이징_처리

# Querydsl을 이용한 페이징 처리

> - 스프링 데이터의 Page, Pageable을 활용하여 조회
> - 전체 카운트를 한번에 조회하는 방법과 데이터 내용과 전체 카운트를 별도로 조회 하는 방법을 작성

- Repository

```java
Page<BookReview> findAllByUserInfo_userNo(long userNo, Pageable pageable);
```

- RepositoryCustom

```java
public interface BookReviewRepositoryCustom {
    Page<BookReview> findAllByUserInfo_userNo(long userNo, Pageable pageable);
}
```

- RepositoryImpl
  - 조회 쿼리와 카운트 커리 한번에 조회하는 방법(querydsl이 토탈카운트 쿼리를 생성) - 데이터 수가 적을때 주로 사용
  - 직접 카운트 쿼리 작성(카운트쿼리를 최적화할 수 있음)

```java
public class BookReviewRepositoryImpl implements BookReviewRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    //조회 쿼리와 카운트 커리 한번에 조회하는 방법
    @Override
    public Page<BookReview> findAllByUserInfo_userNo(long userNo, Pageable pageable) {
        QueryResults<BookReview> results = queryFactory
                .select(bookReview)
                .from(bookReview)
                .leftJoin(bookReview.userInfo).fetchJoin()
                .leftJoin(bookReview.book).fetchJoin()
                .where(bookReview.userInfo.userNo.eq(userNo))
                .offset(pageable.getOffset())   //N 번부터 시작
                .limit(pageable.getPageSize()) //조회 갯수
                .fetchResults();

        List<BookReview> content = results.getResults();
        long total = results.getTotal();
       
    }
}
public class BookReviewRepositoryImpl implements BookReviewRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    //직접 카운트 쿼리 작성
    @Override
    public Page<BookReview> findAllByUserInfo_userNo(long userNo, Pageable pageable) {
        QueryResults<BookReview> results = queryFactory
                .select(bookReview)
                .from(bookReview)
                .leftJoin(bookReview.userInfo).fetchJoin()
                .leftJoin(bookReview.book).fetchJoin()
                .where(bookReview.userInfo.userNo.eq(userNo))
                .offset(pageable.getOffset())   //N 번부터 시작
                .limit(pageable.getPageSize()) //조회 개수
                .fetchResults();

        List<BookReview> content = results.getResults();
        long total = results.getTotal();
       
    }
}
```