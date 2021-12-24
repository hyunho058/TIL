#  Querydsl을 이용한 Projection
Projection : Entity의 속성들중 일부 데이터만 가져오는 방법

* 순수 JPA에서   DTO를 조회 할때는 아래 3가지 단점이 있다.
	* JPA에서 DTO를 조회할 때는 new명령어를 사용
	* DTO의 package 이름을 다 적어줘야함
	* 생성자 방식만 지원

* Querydsl에서는 아래와 같이 3가지 방법이 있다.
	* 프로퍼티 접근(Settet 이용)
	* 필드 직접 접근
	* **생성자 사용**

이번에 정리할 내용은 생성자를 이용한 DTO 조회이다.
* Entity
```
@Entity
@NoArgsConstructor
@Getter
public class UserInfo {

    @Id
    @GeneratedValue
    private long userNo;

    private String userId;

    private String userPw;

    private String userName;

    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL)
    private List<BookReview> bookReviews = new ArrayList<>();
	
	  ...
}
```java

* DTO 클래스
```
@Data
public class UserInfoDto {
    private long userNo;
    private String userId;
    private UserStatus userStatus;
    private String userName;
    private String userGender;
    private String userBirth;
    private LocalDateTime lastConnection;
    private String userPriority;

    public UserInfoDto(long userNo, String userId, UserStatus userStatus, String userName, String userGender, String userBirth, LocalDateTime lastConnection, String userPriority) {
    this.userNo = userNo;
    this.userId = userId;
    this.userStatus = userStatus;
    this.userName = userName;
    this.userGender = userGender;
    this.userBirth = userBirth;
    this.lastConnection = lastConnection;
    this.userPriority = userPriority;
	  }

}
```java
* querydsl
```
@Override
public Page<UserInfoDto> findAllDtoByProjection(Pageable pageable) {
    List<UserInfoDto> results = queryFactory
            .select(Projections.constructor(UserInfoDto.class,
                    userInfo.userNo,
                    userInfo.userId,
                    userInfo.userStatus,
                    userInfo.userName,
                    userInfo.userGender,
                    userInfo.userBirth,
                    userInfo.lastConnection,
                    userInfo.userPriority))
            .from(userInfo)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

    Long totalCount = queryFactory
            .selectFrom(userInfo)
            .fetchCount();
    return new PageImpl<>(results, pageable, totalCount);
}
```java
* Test code
```
@Test
@DisplayName("FIND_ALL_DTO_BY_PROJECTION")
void FIND_ALL_DTO_BY_PROJECTION(){
    //given
    UserInfo userA = UserInfo.builder()
            .userId("userA@semo.com")
            .userPw("semo1234")
            .userName("userA")
            .userGender("M")
            .userBirth("19920519")
            .build();
    UserInfo userB = UserInfo.builder()
            .userId("userB@semo.com")
            .userPw("semo1234")
            .userName("userB")
            .userGender("M")
            .userBirth("19920519")
            .build();
    UserInfo userC = UserInfo.builder()
            .userId("userC@semo.com")
            .userPw("semo1234")
            .userName("userC")
            .userGender("M")
            .userBirth("19920519")
            .build();
    userRepository.save(userA);
    userRepository.save(userB);
    userRepository.save(userC);
    //when
    Page<UserInfoDto> results = userRepository.findAllDtoByProjection(PageRequest.of(0, 2));

    assertThat(results.getContent().size()).isEqualTo(2);
    assertThat(results.getContent().get(0).getUserName()).isEqualTo("userA");
    assertThat(results.getTotalElements()).isEqualTo(3L);
    assertThat(results.getTotalPages()).isEqualTo(2);
}
```java


## @QueryProjection
```
@QueryProjection
public UserInfoDto(String userId, String userName) {
    this.userId = userId;
    this.userName = userName;
}
```java

위와같이 생성자에 `@QueryProjection`을 달아주고 `compileQuerydsl`을 실행 시켜준뒤 querydsl을 작성해준다
<img width="282" alt="스크린샷 2021-12-24 오후 4 55 56" src="https://user-images.githubusercontent.com/58923731/147331875-ac5e9292-9ba0-4425-a34f-721fbfaa5ff5.png">

```
@Override
public Page<UserInfoDto> findAllDtoByQueryProjection(Pageable pageable) {
    List<UserInfoDto> results = queryFactory
            .select(new QUserInfoDto(userInfo.userId, userInfo.userName))
            .from(userInfo)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

    Long totalCount = queryFactory
            .selectFrom(userInfo)
            .fetchCount();
    return new PageImpl<>(results, pageable, totalCount);
}
```java

* 단점
	* `@QueryProjection`를 DTO클래스 생성자에 작성해줘야 한다.
	* DTO클래스가 `querydsl`에 의존성을 가지게 된다.
