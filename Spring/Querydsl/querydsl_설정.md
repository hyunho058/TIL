# Querydsl 설정

## 설정

- gradle 추가

```groovy
plugins {
	  //querydsl
    id "com.ewerk.gradle.plugins.querydsl" version "1.0.10" 
}

dependencies {
    //querydsl
    implementation 'com.querydsl:querydsl-jpa'
}

//-----------querydsl-----------//
def querydslDir = "$buildDir/generated/querydsl"
querydsl {
    jpa = true
    querydslSourcesDir = querydslDir
}
sourceSets {
    main.java.srcDir querydslDir
}
configurations {
    querydsl.extendsFrom compileClasspath
}
compileQuerydsl {
    options.annotationProcessorPath = configurations.querydsl
}
//--------------------------------//
```

## 기본 사용법

> Spring Data Jpa Custom Repository 적용
>
> [Spring Data 공식 문서](https://docs.spring.io/spring-data/jpa/docs/2.1.3.RELEASE/reference/html/#repositories.custom-implementations)

- Spring Data Jpa에서는 **Custom Repository를 JpaRepository 상속 클래스에서 사용**할 수 있도록 기능을 지원.
- Custom이 붙은 인터페이스를 상속한 Impl 클래스의 코드는 Custom 인터페이스를 상속한 JpaRepository에서도 사용할 수 있다.

### 1.Entity 생성

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
    @JoinColumn(name = "isbn" )
    private Book book;


    @Builder
    public BookReview(int rating, String reviewContents, LocalDateTime createDate, int declaration, Book book, UserInfo userInfo) {
        this.rating = rating;
        this.reviewContents = reviewContents;
        this.createDate = createDate;
        this.declaration = declaration;
        this.book = book;
        this.userInfo = userInfo;
    }

    public void changeBookReview(int rating, String reviewContents) {
        this.rating = rating;
        this.reviewContents = reviewContents;
    }
}
```

### 2.Repository 생성

- 데이터를 넣고, 검증

```java
@Repository
public interface BookReviewRepository extends CrudRepository<BookReview, String>, BookReviewRepositoryCustom {
  boolean existsByReviewNo(long reviewNo);    //querydsl
}
```

### 3.RepositoryCustom 생성

```java
public interface BookReviewRepositoryCustom {
    boolean existsByReviewNo(long reviewNo);
}
```

### 4.RepositoryImpl 생성

```java
public class BookReviewRepositoryImpl implements BookReviewRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    public BookReviewRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
    @Override
    public boolean existsByReviewNo(long reviewNo) {
        Integer fetchOne = queryFactory
                .selectOne()
                .from(bookReview)
                .where(bookReview.reviewNo.eq(reviewNo))
                .fetchFirst();
        return fetchOne != null;
    }
}
```



