# 더티 체킹(Dirty Checking)

> Dirty란 상태의 변화가 생긴 정도
>
> Dirty Checking은 상태 변경 검사

* JPA에서는 트랜잭션이 끝나는 시점에 변화가 있는 모든 엔티티 객체를 데이터베이스에 자동으로 반영
* JPA에서는 엔티티를 조회하면 해당 엔티티의 조회 상태를 스냅샷을 만든다.
* 트랜잭션이 끝나는 시점에 이 스냅샷과 비교해서 변경이 있으면 Update query를발생
* Dirty Checking대상은 **영속성 컨텍스트가 관리하는 엔티티에만 적용**
* 준영속, 비영속 상태의 엔티티는 Dirty Checking대상에 포함되지 않는다.
  * Detech된 엔티티 - 준영속
  * DB에 반영되기 전 처음 생성된 엔티티 - 비영속
  * **값을 병경해도 데이터베이스에 반영되지 않는다.**



## Spring Data Jpa 으로 Dirty Checking 확인

* Service

```java
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {  
    @Transactional
    public void updateBookContents(String isbn, String contents){
        Book book = bookRepository.findByIsbn(isbn);
        book.updateContents(contents);
    }
}
```

* Test code

```java
@Test
@DisplayName("도서_내용_수정")
void 도서_내용_수정(){
    //give
    String isbn = "11111111";
    Book book = bookRepository.save(Book.builder()
            .isbn(isbn)
            .bookName("SEMO")
            .author("SEMO")
            .publisher("hDream")
            .contents("semosemo")
            .kdc("800")
            .category("800")
            .keyword("800")
            .img("http://image.kyobobook.co.kr/images/book/large/924/l9788901214924.jpg")
            .build());
    //when
    String updateContents = "ABC";
    bookService.updateBookContents(isbn, updateContents);
    //then
    Book updateBookData = bookRepository.findByIsbn(isbn);
    assertThat(updateBookData.getContents(),is(updateContents));
}
```

* update 쿼리 확인

<img src="더티체킹(DirtyChecking).assets/스크린샷 2021-08-20 오전 11.25.58.png" alt="스크린샷 2021-08-20 오전 11.25.58" style="zoom:50%;" /> 

## 변경된 부분만 Update

> Dirty Checking으로 생성되는 update뭐리는 기본적으로 모든 필드를 update

* JPA는 전체 필드를 업데이트하는 방식을 기본값으로 한다.

  * 생성된 쿼리가 같아 부트 실행시점에 미리 만들어 재사용 가능
  * 데이터베이스 입장에서 쿼리 재사용 가능
    * 동일한 쿼리를 받으면 이전에 파싱된 쿼리를 재사용
  * 필드가 많아질 경우 전체필드 Update쿼리가 부담이 된다.
    * `@DynamicUpdate`로 변경 필드만 변경 가능.

  ```java
  @Entity
  @Getter
  @NoArgsConstructor
  @DynamicUpdate //변경된 필드만 적용
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
  
      @Column(name = "contents")
      private String contents;
  
      @Column(name = "KDC")
      private String kdc;
  
      @Column(name = "CATEGORY")
      private String category;
  
      @Column(name = "KEYWORD")
      private String keyword;
  
      @Column(name = "BOOK_IMAGE")
      private String img;
  
  //    @JsonIgnore //bookReviewList 를 호회하지 않는다..... 쓰면 좋지 않다..... 쓰지마라
      @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
      private List<BookReview> bookReviewList = new ArrayList<>();
  
      @Builder
      public Book(String isbn, String bookName, String author, String publisher,
                  String kdc, String category, String keyword, String img, String contents) {
          this.isbn = isbn;
          this.bookName = bookName;
          this.author = author;
          this.publisher = publisher;
          this.kdc = kdc;
          this.category = category;
          this.keyword = keyword;
          this.img = img;
          this.contents = contents;
      }
  
      /**
       * udpate contetns
       *
       * @author hyunho
       * @since 2021/08/20
      **/
      public void updateContents(String contents){
          this.contents = contents;
      }
  }
  ```

  <img src="더티체킹(DirtyChecking).assets/스크린샷 2021-08-20 오전 11.41.20.png" alt="스크린샷 2021-08-20 오전 11.41.20" style="zoom:67%;" /> 