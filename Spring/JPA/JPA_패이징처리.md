# JPA 패이징 처리

* toOne 관계는 모두 join fetch로 가져온다.



## Page

> * 추가 count 쿼리 결과를 포함하는 페이징

* Repository

```java
Page<Book> findAll(Pageable pageable);
```

* Service
  * 파라미터로 받은 Pageble은 인터페이스다. 따라서 실제 사용할 때는 해당 인터페이스를 구현한 **org.springframework.data.domain.PageRequest** 객체를 사용
  * PageRequest 생성자의 첫번째 파라미터에는 현재 페이지를, 두번째 파라미터에는 조회할 데이터 수를 입력.
    * 추가로 정렬 정보도 파라미터로 사용할 수 있다.

```java
public BookResponse findAll(int pageNum) {
  PageRequest pageRequest = PageRequest.of(pageNum, 5);
  Page<Book> page = bookRepository.findAll(pageRequest);
  
  List<Book> content = page.getContent(); //패이지로 가져온
  long totalElements = page.getTotalElements(); //total count
  int pageNumber = page.getNumber();  //page number
  int totalPage = page.getTotalPages();   //total page
  boolean firstPage = page.isFirst(); //first page
  boolean nextPageState = page.hasNext(); //다음 페이지 존재 여부

  log.info("page count = "+content.size());
  log.info("total count = "+totalElements);
  log.info("page number = "+pageNumber);
  log.info("total page = "+totalPage);
  log.info("first page state = "+firstPage);
  log.info("next page state = "+nextPageState);
  
  List<BookListDto> result = page.getContent().stream()
    .map(b -> new BookListDto(b))
    .collect(Collectors.toList());
  return result;
}
```



* countQuery 적용 X

  ```java
  @Query(value = "select b from Book b left join b.bookReviewList br")
  Page<Book> findAll(Pageable pageable);
  ```

```bash
  //Query 결과
  
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

```java
@Query(value = "select b from Book b left join b.bookReviewList br", 
       countQuery = "select count(b.bookName) from Book b")
Page<Book> findAll(Pageable pageable);
```

  ```bash
  //Query 결과
  
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

  

## Slice

> *  내부적으로 limit +1 

* Repository

```java
@Query(value = "select b from Book b left join fetch b.bookReviewList br")
    Slice<Book> findAll(Pageable pageable);
```

* Service

```jade
PageRequest pageRequest = PageRequest.of(pageNum, 5);
Slice<Book> page = bookRepository.findAll(pageRequest);
```

* slice 결과 쿼리

```bash
select
    book0_.isbn as isbn1_0_0_,
    bookreview1_.review_no as review_n1_1_1_,
    book0_.author as author2_0_0_,
    book0_.book_name as book_nam3_0_0_,
    book0_.category as category4_0_0_,
    book0_.book_image as book_ima5_0_0_,
    book0_.kdc as kdc6_0_0_,
    book0_.keyword as keyword7_0_0_,
    book0_.publisher as publishe8_0_0_,
    bookreview1_.isbn as isbn7_1_1_,
    bookreview1_.create_date as create_d2_1_1_,
    bookreview1_.declaration as declarat3_1_1_,
    bookreview1_.rating as rating4_1_1_,
    bookreview1_.review_contents as review_c5_1_1_,
    bookreview1_.review_status as review_s6_1_1_,
    bookreview1_.user_no as user_no8_1_1_,
    bookreview1_.isbn as isbn7_1_0__,
    bookreview1_.review_no as review_n1_1_0__ 
from
    book book0_ 
left outer join
    book_review bookreview1_ 
	    on book0_.isbn=bookreview1_.isbn 
order by
		book0_.book_name desc
```



