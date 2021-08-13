# 다이나믹 쿼리 Where절 파라미터 사용

### where 파라미터 적용전

```java
@Override
public boolean exists(long userNo, String isbn) {
    Integer fetchOne = queryFactory
            .selectOne()
            .from(bookReview)
            .where(
                    bookReview.userInfo.userNo.eq(userN)
                    .and(book.isbn.eq(isbn)))
            .fetchFirst();
    return fetchOne != null;
}
```

### where 파라미터 적용

- BooleanExpression
  - BooleanExpression은 where에서 사용할 수 있는 값인데, 이 값은 ,를 and조건으로 사용한다.
  - 아래 코드에서 BooleanExpression을 리턴하는데, 각 메소드에서 조건이 맞지 않으면 null을 리턴.
  - null을 리턴하니 where에서는 상황에 따라 조건문을 생성하게 된다.
    - 파라미터가 비어있다면, 조건절에서 생략

```java
@Override
public boolean exists(long userNo, String isbn) {
    Integer fetchOne = queryFactory
            .selectOne()
            .from(bookReview)
            .where(
                    userNoEq(userNo),
                    isbnEq(isbn))
            .fetchFirst();
    return fetchOne != null;
}

private BooleanExpression isbnEq(String isbn) {
    return StringUtils.hasText(isbn) ? book.isbn.eq(isbn) : null;
}

private BooleanExpression userNoEq(Long userNo) {
    return userNo != null ? userInfo.userNo.goe(userNo) : null;
}
```

- 쿼리결과

```bash
    select
        bookreview0_.review_no as review_n1_3_0_,
        userinfo1_.user_no as user_no1_7_1_,
        book2_.isbn as isbn1_2_2_,
        bookreview0_.isbn as isbn7_3_0_,
        bookreview0_.create_date as create_d2_3_0_,
        bookreview0_.declaration as declarat3_3_0_,
        bookreview0_.rating as rating4_3_0_,
        bookreview0_.review_contents as review_c5_3_0_,
        bookreview0_.review_status as review_s6_3_0_,
        bookreview0_.user_no as user_no8_3_0_,
        userinfo1_.del_date as del_date2_7_1_,
        userinfo1_.delete_reason as delete_r3_7_1_,
        userinfo1_.last_connection as last_con4_7_1_,
        userinfo1_.user_birth as user_bir5_7_1_,
        userinfo1_.user_gender as user_gen6_7_1_,
        userinfo1_.user_id as user_id7_7_1_,
        userinfo1_.user_name as user_nam8_7_1_,
        userinfo1_.user_priority as user_pri9_7_1_,
        userinfo1_.user_pw as user_pw10_7_1_,
        userinfo1_.user_status as user_st11_7_1_,
        book2_.author as author2_2_2_,
        book2_.book_name as book_nam3_2_2_,
        book2_.category as category4_2_2_,
        book2_.contents as contents5_2_2_,
        book2_.book_image as book_ima6_2_2_,
        book2_.kdc as kdc7_2_2_,
        book2_.keyword as keyword8_2_2_,
        book2_.publisher as publishe9_2_2_ 
    from
        book_review bookreview0_ 
    inner join
        user_info userinfo1_ 
            on bookreview0_.user_no=userinfo1_.user_no 
    inner join
        book book2_ 
            on bookreview0_.isbn=book2_.isbn 
    where
        userinfo1_.user_no>=? 
        and (
            bookreview0_.create_date between ? and ?
        ) 
        and (
            bookreview0_.review_contents is not null
        )
```

[참고](https://jojoldu.tistory.com/394)