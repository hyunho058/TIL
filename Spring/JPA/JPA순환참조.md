# JPA순환참조 발생

> * 엔티티를 JSON으로 변환하는 도중에 에러가 발생
>   *  양방향 연관관계 설정시 지연로딩으로 설정된 엔티티를 API에서 내려줄 때,  Jackson이 데이터를 변환하다가 알 수 없는 타입이라는 에러가 발생.

* 엔티티

  ![스크린샷 2021-05-31 오전 7.29.48](JPA순환참조.assets/스크린샷 2021-05-31 오전 7.29.48.png)



## API구조

* Book

```java
@Entity
@Getter
@NoArgsConstructor
public class Book {
    @Id
    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "BOOK_NAME")
    private String bookName;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "PUBLISHER")
    private String publisher;

    @Column(name = "KDC")
    private String kdc;

    @Column(name = "CATEGORYy")
    private String category;

    @Column(name = "KEYWORD")
    private String keyword;

    @Column(name = "BOOK_IMAGE")
    private String img;

//    @JsonIgnore //bookReviewList 를 호회하지 않는다..... 쓰면 좋지 않다..... 쓰지마라
    @OneToMany(mappedBy = "book")
    private List<BookReview> bookReviewList = new ArrayList<>();
}
```

*  BookReview

```java

@Getter
@Entity
@NoArgsConstructor
public class BookReview {
    @Id
    @GeneratedValue
    private long reviewNo;

    private int rating;
    private String reviewContents;
    private LocalDateTime createDate;
    private int declaration;

    //NORMAl, BLIND
    @Enumerated(EnumType.STRING)
    private ReviewStatus reviewStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private UserInfo userInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "isbn")
    private Book book;
}
```

* Repository

```java
@Repository
public interface BookReviewRepository extends JpaRepository<BookReview,String> {
    List<BookReview> findAll();
}
```

* Service

```java
public BookReviewResponse readReviewAll() {
  log.info("showReview");
  String hMessage = null;
  Object data = null;
  StatusEnum hCode = null;
  try {
    List<BookReview> bookReviewList = bookReviewRepository.findAll();
    hCode = StatusEnum.hd1004;
    hMessage = "가져오기";
    data = bookReviewList;
  } catch (Exception e) {
    log.error("createReview err :: error msg : {}", e);
    hCode = "11111";
    hMessage = "readReview 에러";
    data = null;
  }
  return BookReviewResponse.builder()
    .data(data)
    .hCode(hCode)
    .hMessage(hMessage)
    .build();
}
```



## 순환 참조로 인한 에러 발생 상황

* 위 코드를 실행해서 API를 호출 할 경우  아래와 같은 오류가 발생한다. (Could not write JSON: Infinite recursion (StackOverflowError); )
  * Book.bookReviewList -> BookReview.book 가 서로 순환 참조이기 때문에 발생.

~~~bash
Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is org.springframework.http.converter.HttpMessageNotWritableException: Could not write JSON: Infinite recursion (StackOverflowError);
~~~



## 해결 방법

* **DTO를 별도로 만들어 사용하는 것을 권장**

* 방법은 총 3가지로 정리한다.

  * @JsonIgnore - API호출시 해당 어노테이션이 있으면 호출 하지 않는다.
    * 에플리케이션이 정말 단순하면 사용 가능, 하지만 이것은 **화면이나 API로직이 엔티티에 침범하는 모양이기 때문에 조금만 애플리케이션이 복잡해져도 권장하지 않음**.

  ~~~java
  @JsonIgnore //bookReviewList 를 호회하지 않는다
  @OneToMany(mappedBy = "book")
  private List<BookReview> bookReviewList = new ArrayList<>();
  ~~~

  * Hibernate5Module Been이용

    * Gradle

    ```bash
    implementation 'com.fasterxml.jackson.datatype:jackson-datatype-hibernate5'
    ```

    

    * Application

    ```java
    @Bean
    Hibernate5Module hibernate5Module(){
      Hibernate5Module hibernate5Module = new Hibernate5Module();
      return hibernate5Module;
    }
    ```

    * 지연로딩으로 설정된 엔티티를 API에서 내려줄 때,  Jackson이 데이터를 변환하다가 알 수 없는 타입이라는 에러가 발생. 이 때, `jackson-datatype-hibernate5` 모듈을 추가하고 Bean 등록을 하게 되면, 지연로딩으로 설정된 프록시 엔티티를 null 값으로 설정한다.

  * **DTO 활용**

    * **API당 별도의 DTO 클래스를 만들어서 중간에 엔티티를 DTO로 변환고, 변환한 DTO를 JSON으로 바꾼다.**
    * DTO클래스 생성
      * 주의점 - DTO를 생성후 DTO내에서 Book 엔티티를 조회하게 되면 순환참조가 발생하게 된다. 그렇기 때문에 Book객체가 아닌 "isbn = bookReview.getBook().getIsbn()" 해당 코드처럼 값을 가져와야함.
      * **아래 코드에서 주석으로 처리된 "book = bookReview.getBook();" 코드 때문에 순환참조가 발생해  고생을 하였다...**

    ~~~java
    @Data
    public class BookReviewDto {
        private int rating;
        private String reviewContents;
        private LocalDateTime createDate;
        private int declaration;
        private String isbn;
        private String userName;
    //    private UserInfo userInfo;
    //	  private Book book;
    
        public BookReviewDto(BookReview bookReview) {
            rating = bookReview.getRating();
            reviewContents = bookReview.getReviewContents();
            createDate = bookReview.getCreateDate();
            declaration = bookReview.getDeclaration();
            isbn = bookReview.getBook().getIsbn();
            userName = bookReview.getUserInfo().getUserName();
    //        book = bookReview.getBook();
    //        userInfo = bookReview.getUserInfo();
        }
    }
    ~~~

    *  Service APi 수정 코드

    ```java
    List<BookReview> bookReviewList = bookReviewRepository.findAll();
    List<BookReviewDto> result = bookReviewList.stream()
      .map(r -> new BookReviewDto(r))
      .collect(Collectors.toList());
    log.info("bookReviewList : {}", result.toString());
    
    hCode = StatusEnum.hd1004;
    hMessage = "가져오기";
    data = result;
    ```

    

    

    