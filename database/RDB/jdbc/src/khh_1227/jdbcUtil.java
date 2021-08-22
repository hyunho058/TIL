package khh_1227;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class jdbcUtil {
	
	public static Connection getConnection() {
		
		String user="SCOTT";
		String pw="TIGER";
		String driver="oracle.jdbc.OracleDriver";  //드라이버 로딩 종류
		String url="jdbc:oracle:thin:@127.0.0.1:1521:xe";  //서버 경로
		
		Connection con = null;
		
		try {
			//1. Driver 클래스를 로딩
			Class.forName(driver);  //객체 생성 문 , (new를 쓰게되면 메모리에 많은 영역을 차지함) 메모리에 1개만 생성되서 관리 용이
			
			//2.로딩된 Driver클래스를 이용해서 Connection요청(url, user, pwd)
			con = DriverManager.getConnection(url, user, pw);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static void close(Connection con, Statement ps, ResultSet rs) {
		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		} catch (Exception e2) {
			System.out.println(e2);
		}
		
	}
}
