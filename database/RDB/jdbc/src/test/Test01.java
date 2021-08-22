package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test01 {

	public static void main(String[] args) {
		//emp table 내용 호출
		//Oracle DB 연동 ->driver setting
		System.out.println("JDBC TEST");
		
		
		//별도 서버에서 관리될 환경설정들이다.
		String user="SCOTT";
		String pw="TIGER";
		String driver="oracle.jdbc.OracleDriver";  //드라이버 로딩 종류
		String url="jdbc:oracle:thin:@127.0.0.1:1521:xe";  //서버 경로
		
		String sql = "select * from dept";
		
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
			//4.생성된 Statement를 이용해서 sql수행(execute, executeUpdate, executeQuery)
			rs = ps.executeQuery();
			//5.결과 처리(ResultSet, int)
			while(rs.next()) {
				System.out.print(rs.getString("deptno") + " ");
				System.out.print(rs.getString("dname") + " ");
				System.out.print(rs.getString("loc") + " ");
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
