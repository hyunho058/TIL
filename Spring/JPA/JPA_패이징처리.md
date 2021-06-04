# JPA 패이징 처리

* toOne 관계는 모두 join fetch로 가져온다.



* Page

```java
Page<Book> findAll(Pageable pageable);
```

```java
public BookResponse findAll(int pageNum) {
  PageRequest pageRequest = PageRequest.of(pageNum, 5);
  Page<Book> page = bookRepository.findAll(pageRequest);
  List<BookListDto> result = page.getContent().stream()
    .map(b -> new BookListDto(b))
    .collect(Collectors.toList());
  return result;
}

```

* Slice







* Count Query

  * countQuery 적용 X

  ```bash
  @Query(value = "select b from Book b left join b.bookReviewList br")
  Page<Book> findAll(Pageable pageable);
  
  
  //Query 결과
  
  2021-06-04 09:31:06.395 DEBUG 26536 --- [nio-8080-exec-3] org.hibernate.SQL                        : 
      select
          book0_.isbn as isbn1_0_,
          book0_.author as author2_0_,
          book0_.book_name as book_nam3_0_,
          book0_.category as category4_0_,
          book0_.book_image as book_ima5_0_,
          book0_.kdc as kdc6_0_,
          book0_.keyword as keyword7_0_,
          book0_.publisher as publishe8_0_ 
      from
          book book0_ 
      left outer join
          book_review bookreview1_ 
              on book0_.isbn=bookreview1_.isbn limit ?
  2021-06-04 09:31:06.424 DEBUG 26536 --- [nio-8080-exec-3] org.hibernate.SQL                        : 
      select
          count(book0_.isbn) as col_0_0_ 
      from
          book book0_ 
      left outer join
          book_review bookreview1_ 
              on book0_.isbn=bookreview1_.isbn
  
  ```

  * countQuery 적용
    * book을 가져오는 query는 복잡해도 CountQuery는 단순해진다.
    * 조인이 없기때문에 데이터가 많아도 DB에서 쉽게 가져올수 있다.

  ```bash
  @Query(value = "select b from Book b left join b.bookReviewList br", countQuery = "select count(b.bookName) from Book b")
  Page<Book> findAll(Pageable pageable);
  
  
  //결과
  
  2021-06-04 09:33:56.593 DEBUG 26603 --- [nio-8080-exec-3] org.hibernate.SQL                        : 
      select
          book0_.isbn as isbn1_0_,
          book0_.author as author2_0_,
          book0_.book_name as book_nam3_0_,
          book0_.category as category4_0_,
          book0_.book_image as book_ima5_0_,
          book0_.kdc as kdc6_0_,
          book0_.keyword as keyword7_0_,
          book0_.publisher as publishe8_0_ 
      from
          book book0_ 
      left outer join
          book_review bookreview1_ 
              on book0_.isbn=bookreview1_.isbn limit ?
  2021-06-04 09:33:56.624 DEBUG 26603 --- [nio-8080-exec-3] org.hibernate.SQL                        : 
      select
          count(book0_.book_name) as col_0_0_ 
      from
          book book0_
  ```

  



