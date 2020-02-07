mybatis

> spring dao == jdbc ==>maybatis
>
> sql은 xml frile 로 따로 관리안하 (java와 sql 분리 관리)

>**1>**자바 객체 생성-관리=bean의 라이프 사이클 관리한다
>
>**2> bean**의 의존성을 관리한다.(ioc , di)
>
>**3> AOP** 기능 제공한다.
>
>**4> MVC** 기능 제공한다.
>
>**5> dao, vo** 등 기존 non-spring 환경에서도 사용하던 객체 그대로 사용 가능하다(스프링 라이브러리를 상속받거나 하지 않아도)
>
>**= pojo bean** 사용 가능하다
>
>**(plain old java object)**
>
>**6> spring dao** 기능으로 db 연동 기능 제공한다.
>
>**7>** 각종 다른 프레임워크 연동 기능 제공한다.
>
>**8> mybatis** 프레임워크 연동 가능하다
>
>​	= spring dao 기능 대신 사용하는 형태로 현재 개발 추세
>
>**9> spring** 은 db 연결 기능으로
>
>spring + jdbc
>
>spring + spring dao
>
>spring + mybatis
>
>

1. jdbc driver
2. jdbc url
3. account&password



## 기존 jdbc

```java
try{
1. class.forNma("")
2. Connection c = DriverManager.getConnection(jdbc url, account, password)
3. sql - 결과
4. c.close()
}catch(Exception e){
}
```

> jdbc 단점 : mybatis 대체
>
> 1. 코드 반복이 많다 : db-config.xml
> 2. 자바 언어 내부에 spl언어 포함이 많다 : sql-mapping.xml
> 3. DB 연결 복잡한 정보 매번 작성 해야 한다.: db-config.xml



DB 연결 파일 은 1개 mapping 파일은 여러개

1. db-config.xml  - DB연결 파일
   1. board-mapping.xml  - 개시글과 관련 -mapping 파일
   2. memver-mapping.xml - 맴버 관리 -mapping 파일

## mabatis

1. xml 설정 읽기

2. DB 연결 객체 가져오기

3. sql 정보 가져와서 실행 요청 - 결과(Board VO, String, ArrayLIst) Type 설정 해야함.

4. 출력

   => 1, 2(설정파일) 번은 한 번만 설정 하면 되고 3,4(sql 정의) 번을 활용한다

### 환경 설정

* mybatis.jar => pom.xml 파일에  <dependency>  적용

![image-20200207094625048](image/image-20200207094625048.png)

![image-20200207094838830](image/image-20200207094838830.png)

* ojdbc6.jar : 오라클 jdbc driver 파일 압축파일 등록

  ​	

![image-20200207095018002](image/image-20200207095018002.png)

spring project file -> properties -> java Build Path -> Add External -> ojdbc6.jar 파일 경로 찾아서 추가

![image-20200207095159819](image/image-20200207095159819.png)

* xml file 탬플릿

![image-20200207095837970](image/image-20200207095837970.png)





* 설정파일(DB연결)

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-config.dtd">
  
<configuration>
	<typeAliases>
		<typeAlias type="mybatis.EmpVO" alias="emp"/>  <!-- mybatis.EmpVO 가 필요할떄 'emp' 로 불러올수 있다 -->  
	</typeAliases>
	
	<environments default="mydb">
		<environment id="mydb">
			<transactionManager type="JDBC"></transactionManager>
			<dataSource type="POOLED">
				<property name="driver" value="oracle.jdbc.driver.OracleDriver"/>
				<property name="url" value="jdbc:oracle:thin:@localhost:1521:xe"/>
				<property name="username" value="hr"/>
				<property name="password" value="hr"/>
			</dataSource> <!-- <environment id="mydb"> 지금은 hr계정인데 추가하여서 다른 계정도 등록 가능  -->
		</environment>
	</environments>
	
	<mappers>
		<mapper resource="mybatis/emp-mapping.xml"/>
		<!-- <mapper resource="mybatis/board-mapping.xml"/> -->
	</mappers>
</configuration>
```

* maping file

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="emp">
	<!-- crud -> insert, select, update, delete, sql  -->
	 <!-- 1개 레코드 type는 = EmpVO 자바 객체 타입으로 mapping -->
	<select id="allemp" resultType="mybatis.EmpVO"> <!--resultType="mybatis.EmpVO 는 리턴 type이 EmpVO이다  -->
		select * from employees
	</select>
	
	<!-- <select id="oneemp" resultType="mybatis.EmpVO">
		select * from employees where employee_id = 100
	</select> -->
	<select id="oneemp" resultType="mybatis.EmpVO" parameterType="int">
		select * from employees where employee_id = #{a}
	</select>
	
	<select id="nameemp" resultType="mybatis.EmpVO" parameterType="String">
		select * from employees where first_name = #{name}
	</select>
		<!-- 
		session.selectList("nameemp", "P%"); -->
	<select id="namelikeemp" resultType="mybatis.EmpVO" parameterType="String">
		select * from employees where first_name LIKE 'P%' = #{name}
	</select>
	
	<!-- <select id="idemp" resultType="mybatis.EmpVO" parameterType="int">
		select * from employees where employee_id > 200
	</select> -->
	
	<select id="idemp" resultType="mybatis.EmpVO" >
		<![CDATA[select * from employees where employee_id < 150]]> <!-- 부등호 < 는 테그 처리로 인식해서 해당 줄처럼 해줘야 태그로 인식 안함 -->
	</select>
	
</mapper>
```

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="emp">
	<!-- crud -> insert, select, update, delete, sql  -->
	 <!-- 1개 레코드 type는 = EmpVO 자바 객체 타입으로 mapping -->
	<select id="allemp" resultType="mybatis.EmpVO"> <!--resultType="mybatis.EmpVO 는 리턴 type이 EmpVO이다  -->
		select * from employees
	</select>
	
	<!-- <select id="oneemp" resultType="mybatis.EmpVO">
		select * from employees where employee_id = 100
	</select> -->
	<select id="oneemp" resultType="emp" parameterType="int">  <!-- emp는 mybatis-config 에서 저으이되어있다 -->
		select * from employees where employee_id = #{a}
	</select>
	
	<select id="nameemp" resultType="emp" parameterType="String">
		select * from employees where first_name = #{name}
	</select>
		<!-- 
		session.selectList("nameemp", "P%"); -->
	<select id="namelikeemp" resultType="emp" parameterType="String">
		select * from employees where first_name LIKE 'P%' = #{name}
	</select>
	
	<!-- <select id="idemp" resultType="mybatis.EmpVO" parameterType="int">
		select * from employees where employee_id > 200
	</select> -->
	
	<select id="idemp" resultType="emp" >
		<![CDATA[select * from employees where employee_id < 150]]> <!-- 부등호 < 는 테그 처리로 인식해서 해당 줄처럼 해줘야 태그로 인식 안함 -->
	</select>
	
</mapper>
```

```java
package mybatis;

import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class EmpMain {

	public static void main(String[] args) throws Exception{
		//1.
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		//2.SqlSessionFactory = 연결 설정 파일 읽어라
		//sql mapping 파일명 
		SqlSessionFactory factory = builder.build(Resources.getResourceAsReader("mybatis/mybatis-config.xml"));
		//3. SqlSession = jdbc Connection과 유사한 역할 ==>연결 생성
		SqlSession session = factory.openSession();
		
		System.out.println(session);
		
		//test1 여러개 레코드 리턴 조회
//		List<EmpVO> list = session.selectList("allemp"); //여러개가 리턴 되기때문에 List 형태로 받아와야함 ,하나하나가 EmpVO 타입이라서 empVO적용
//		for(EmpVO vo:list) {
//			System.out.println(vo.getEmployee_id()+":"+vo.getFirst_name());
//		}
		
//		EmpVO vo = session.selectOne("oneemp");
//		System.out.println(vo.getEmployee_id()+":"+vo.getFirst_name());
		//test2 1개 레코드 리턴 조회 : 100 사번
//		EmpVO vo = session.selectOne("oneemp", 100);
//		System.out.println(vo.getEmployee_id()+":"+vo.getFirst_name());
		
		//test3 1개 레코드 리턴 조회 : 이름이 Steven 사원
//		List<EmpVO> list = session.selectList("nameemp", "Steven");
//		for(EmpVO vo:list) {
//			System.out.println(vo.getEmployee_id()+":"+vo.getFirst_name());
//		}
//		//test4 cdata섹션 실행
		List<EmpVO> list1 = session.selectList("idemp");
		for(EmpVO vo:list1) {
			System.out.println(vo.getEmployee_id()+":"+vo.getFirst_name()+":"+vo.getHire_date());
		}
	}
```



* spring mvc

form acition="/mvc/test" 

< input name="a" ==>요청 파라미터 변수

@RequestMapping("/test")

public String test(EmpVO vo)

EmpVO : 맴버변수 a: setA( 요청 파라미터 변수'a' 값 )



* mybatis

  <select id="" resultType="test.A"

  select * from employees

  

DDL DATA DEFINITION LANG(데이터 정의언어)

CREATE TABLE / ALTER / DROP   (자동 반영)

DML : DATA MANIPULATION LANG(데이터 조작 언어)

테이블 데이터 저장/ 수정/ 삭제 (insert, update delete) (자동 반영X)



DCL

TCL:COMIT / ROLLBACK



COMMIT

```java
SqlSession session = factory.openSession();  // insert 자동 commit처리 X 
SqlSession session = factory.openSession(true);  //true이면 insert 자동 commit처리 O
```

```java
SqlSession session = factory.openSession(); // true가 없으면 session.commit(); 명령을 해줘야한다

EmpVO vo = new EmpVO();
		vo.setEmployee_id(1000);
		vo.setFirst_name("길");
		vo.setLast_name("홍");
		vo.setEmail("gil@multi.com");
		vo.setJob_id("IT_PROG"); //SQL 키워드는 대소문자 구분 X (단. 값은 대소문자 구분은 함)
		dao.insertEmp(vo);  //insert sql 수행
		
		session.commit(); //commit 수행
```







select first_name, hire_date from employees order by hire_date desc;

## ROWNUM (sql)

> '>', '>=' 연산 못한다 (단. 1 제외)

```
SQL> select rownum, first_name, hire_date from employees where rownum >=1 and rownum <= 10;

    ROWNUM FIRST_NAME                               HIRE_DAT
---------- ---------------------------------------- --------
         1 발                                       20/02/07
         2 Steven                                   03/06/17
         3 Neena                                    05/09/21
         4 Lex                                      01/01/13
         5 Alexander                                06/01/03
         6 Bruce                                    07/05/21
         7 David                                    05/06/25
         8 Valli                                    06/02/05
         9 Diana                                    07/02/07
        10 Nancy                                    02/08/17

```



* employees 테이블에서 11번째 부터 20번 사이 값 가져오기

  > 영역별로 보여줄 수 있따.

```
select r, first_name, hire_date from(select rownum r, first_name, hire_date from(select * from employees order by hire_date desc)) where r>=11 and r<=20;
```

int page = 2;

int statr = 

order by hire_date desc



dao 는 mapping file 호출





![image-20200207104412541](image/image-20200207104412541.png)



