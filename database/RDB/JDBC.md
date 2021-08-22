# JDBC



> build path - configure build path - library - Add external -ojdbc6 추가

![image-20191227102851595](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20191227102851595.png)

```java
package test;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JOptionPane;

import util.jdbcUtil;

public class Test2 {

	public static void main(String[] args) {
		
		jdbcUtiltil u1;

		//emp table 내용 호출
		//Oracle DB 연동 ->driver setting
		System.out.println("JDBC TEST");
		
		
		//별도 서버에서 관리될 환경설정들이다.
		String user="SCOTT";
		String pw="TIGER";
		String driver="oracle.jdbc.OracleDriver";  //드라이버 로딩 종류
		String url="jdbc:oracle:thin:@127.0.0.1:1521:xe";  //서버 경로
		
		String sql = "select * from emp where deptno = ?";  // '?'는 PreparedStatement 가 처리해준다(Statement는 불가능)
		
		Connection con = null;
		PreparedStatement ps = null;  //Statement 보다 성능이 더 좋다.
		ResultSet rs = null;  //select 를 처리하면 ResultSet type 으로 반환 ,insert , delete, uppdate 는 정수형으로 반환
		
		try {
			//1. Driver 클래스를 로딩
			Class.forName(driver);  //객체 생성 문 , (new를 쓰게되면 메모리에 많은 영역을 차지함) 메모리에 1개만 생성되서 관리 용이
			
			//2.로딩된 Driver클래스를 이용해서 Connection요청(url, user, pwd)
			con = DriverManager.getConnection(url, user, pw);
			System.out.println(con);
			
			//3.생성된Connection으로부터 Statememt생성
			ps = con.prepareStatement(sql);
			
			// ? 값 setting
			String deptno = JOptionPane.showInputDialog("debtno");
			ps.setInt(1, Integer.parseInt(deptno));
			
			
			//4.생성된 Statement를 이용해서 sql수행(execute, executeUpdate, executeQuery)
			rs = ps.executeQuery();
			
			//5.결과 처리(ResultSet, int)
			while(rs.next()) {
				//colurm 출력부
				System.out.print(rs.getString("deptno") + " ");
				System.out.print(rs.getString("ename") + " ");
				System.out.print(rs.getString("sal") + " ");
				System.out.print(rs.getDate("hiredate") + " ");
				System.out.print(rs.getInt("deptno") + " ");
				System.out.println("\n==================================");
			}
			
			System.out.println(con);
		} catch (Exception e) {
			//6.SQLException 처리(try, catch, finally)
			System.out.println(e);
		}finally {
			//7. 자원 반납 자원정리(connection, statement, resultset)
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
		System.out.println("JDBC TEST END");
	}
}
```

### dao

> DB연동 담당 Class

```java

```



### vo(DTO)

* DTO

```java

```





# day2

## Oracle 상태확인

> 아래 3개 상태가 실행 중이여야 DB사용 가능

![image-20200106092706086](image/image-20200106092706086.png)

![image-20200106092450666](image/image-20200106092450666.png)

![image-20200106092717596](image/image-20200106092717596.png)



### get/set

setXXX(0,0)

getXXX(0)

> xxx 에는 자료형 (0,0) ->
>
> (0)에는 index or fieldName ->fiealdName 를 권장으로 한다 (순서가 바뀌더라도 이름은 바뀌지않음.)



## Memory 구조

### RAM

> 휘발성

| const(static) | heap       | stack |
| ------------- | ---------- | ----- |
| 상수          | 동적Memory | 변수  |

#### stack

> 컴퓨터가 On 이 되면 system 역역이 생성된다

* 변수

  {    } 영역 -> 중괄호 영역이 끝나면 소멸

#### Heap

* 동적Memory

  C/C++ =>할당 - method(), 해제 - delete()

  JAVA => 할당 - new, 해제 - gc(garbage collector)

#### static

* 상수

  응용 프로그램을 종료하기 전까지 데이터가 살아있음.

### ROM

> 비휘발성

