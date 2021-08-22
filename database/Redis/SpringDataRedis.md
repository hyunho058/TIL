# Spring Data Redis + Redis 연동

## 프로젝트 설정

* Gradle

```groovy
dependencies {
	compile('org.springframework.boot:spring-boot-starter-data-redis')  
}
```

* application.yml (or application.properties)

```yaml
spring:
	redis:
		lettuce:
			pool:
				max-active: 10
				max-idle: 10
				min-idle: 2
		port: 0000
		host: 000.0.0.0
```

## RedisRepository

> Sprind Data Redis에서 **Redis Repository를 이용하면 쉽게 Domain Objects를 Redis Hash자료구조로 변환, secondary indexes를 적용, 그리고 TTL 등을 적용시 킬수 있다.**

* 이번 글에서는 크롤링한 도서 데이터를 Redis에 저장하는것을 할것이다.

* Redis Repository로 이용하기 위해서는 `@Redishash` 어노테이션을 이용해 key를 설정해줘야한다.  **최종적으로 Redis에 들어가는 key는 @RedisHash의 value + @Id가 붙어있는 맴버변수이다**

```java
@Getter
@RedisHash("RECOM_BEST_SELLER")
public class RecomBestSeller {
    @Id
    private String rank;
    private String isbn;
    private String bookName;
    private String author;
    private String publisher;
    private String kdc;
    private String category;
    private String keyword;
    private String img;

    @Builder
    public RecomBestSeller(String rank, String isbn, String bookName, String author, String publisher,
                           String kdc, String category, String keyword, String img){
        this.rank = rank;
        this.isbn = isbn;
        this.bookName = bookName;
        this.publisher = publisher;
        this.author = author;
        this.kdc = kdc;
        this.category = category;
        this.keyword = keyword;
        this.img = img;
    }
}
```

* Repository 생성
  * JPA Repository와 크게 다른점이 없으며, 아래와 같이 선앤해주면 된다.
  * interface 생성해주고, `CrudRepository<Model, Id>` 를 상속

```java
@Repository
public interface RecomBestSellerRepository extends CrudRepository<RecomBestSeller, String> {
}
```



* Service

```java
public class KoboDataService {
	String elementResult = element.attr("href");
                    log.info(element.attr("href"));
                    Document detailDocument = Jsoup.connect(elementResult).get();

                    //ISBN//
                    Element elem = Jsoup.parse(String.valueOf(detailDocument.select("#proForm_barcode"))).getElementById("proForm_barcode");
                    String isbn = elem.attr("value");
                    //TITLE CATEGORY//
                    String titleCategory = String.valueOf(detailDocument.select("#container > div.path_bar.clear > div:nth-child(3) > p > span > a").text()).trim();
                    //DETAIL CATEGORY//
                    String detailCategory = String.valueOf(detailDocument.select("#container > div.path_bar.clear > div:nth-child(5) > p > span > a").text());
                    if (detailCategory == null || detailCategory.equals("")) {
                        detailCategory = String.valueOf(detailDocument.select("#container > div.path_bar.clear > div:nth-child(4) > p > span > a").text());
                    }
                    //TITLE//
                    String title = String.valueOf(detailDocument.select("#container > div:nth-child(4) > form > div.box_detail_point > h1 > strong").text());
                    //AUTHOR//
                    String author = String.valueOf(detailDocument.select("#container > div:nth-child(4) > form > div.box_detail_point > div.author > span:nth-child(1) > a").text());
                    //Publisher//
                    String[] resultList = String.valueOf(detailDocument.select("#container > div:nth-child(4) > form > div.box_detail_point > div.author > span > a").text()).trim().split(" ");
                    String publisher = resultList[resultList.length - 1];
                    //IMAGE//
                    String imgPath = String.valueOf(detailDocument.select("#container > div:nth-child(4) > form > div.box_detail_info > div.box_detail_cover > div > a > img").attr("src"));
                    String category =  CATEGORY_TYPE_MAP.get(s);
                    int idx = categoryIndex.get(category);
                    recomBestSellerRepository.save(RecomBestSeller.builder()
                            .rank(category + "_" +idx )
                            .isbn(isbn)
                            .bookName(title)
                            .author(author)
                            .publisher(publisher)
                            .kdc("")
                            .category(category)
                            .keyword(detailCategory)
                            .img(imgPath)
                            .build());
                    categoryIndex.put(category,idx+1);
}

```

* 테스트를 진행해 확인.

```java
@Autowired
RecomBestSellerRepository bestSellerRepository;Q 

@Test
@DisplayName("SAVE_BOOK")
void SAVE_BOOK(){
  //give
  RecomBestSeller recomBestSeller = RecomBestSeller.builder()
    .rank("10000")
    .isbn("99999999")
    .bookName("SEMO")
    .author("SEMO")
    .publisher("SEMO")
    .kdc("")
    .category("SEMO")
    .keyword("SEMO")
    .build();
  //when
  RecomBestSeller savedData = bestSellerRepository.save(recomBestSeller);

  //then
  Optional<RecomBestSeller> result = bestSellerRepository.findById(savedData.getRank());
  assertThat(result.get().getBookName(), is(recomBestSeller.getBookName()));
}
```

