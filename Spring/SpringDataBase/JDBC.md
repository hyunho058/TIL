# JDBC

**JDBC 표준 인터페이스** 
java에서 데이터베이스에 접속할 수 있도록 하는 java api 이며, JDBC표준 인터페이스는 아래 3가지 기능을 표준 인터페이스로 정의해서 제공
`Connection` - 연결
`Statement`- SQL을 담은 내용
`ResultSet`- SQL요청 응답

**데이터베이스 연결 (h2)**
JDBCrㅏ 제공하는 `DriverManager`는 라이브러리에 등록된 DB드라이버들을 관리하고, Connection을 획득하는 기능을 제공.
DriverManager 는 라이브러리에 등록된 드라이버 목록을 자동으로 인식하며, 이드라이버들에게 순서대로 다음 정보를 넘겨서 connection을 획득 할 수 있는지 확인 후 찾은 connection구현체가 client에 반환된다.

```java
@Slf4j
public class DBConnectionUtil {

    public static Connection getConnection(){
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            log.info("get connection={}, class={}",connection, connection.getClass());
            return connection;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
```

테스트코드

```java
@Test
void connection() {
    Connection connection = DBConnectionUtil.getConnection();
    Assertions.assertThat(connection).isNotNull();
}
```

결과

```bash
get connection=conn0: url=jdbc:h2:tcp://localhost/~/cicd user=SA, class=class org.h2.jdbc.JdbcConnection
```



**JDBC를 이용한 개발**

테스트 DB생성

```java
drop table member if exists cascade;
create table member(
  member_id varchar(10),
  money integer not null default 0,
  primary key (member_id)
); 
```





