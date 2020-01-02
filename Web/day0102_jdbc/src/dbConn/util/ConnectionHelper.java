package dbConn.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//DB 연결 정보 반복적으로 코딩 해결, 다른 클래스에서 아래 코드 구현을 하지 않도록 설계

public class ConnectionHelper {
	
	public static void main(String[] args) {
//		Connection conn =ConnectionHelper.getConnection("oracle"); 
	}
	
	public static Connection getConnection(String dsn) {
		Connection conn = null;
		
		try {
			if(dsn.equals("mysql")) {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampleDB","doublekim","mysql");
			}
			else if(dsn.equals("oracle")) {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","doublekim","oracle");
				System.out.println("success");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return conn;
		}
	}
	
	public static Connection getConnection(String dsn, String user, String pwd) {
		Connection conn = null;
		
		try {
			if(dsn.equals("mysql")) {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampleDB",user,pwd);
			}
			else if(dsn.equals("oracle")) {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",user,pwd);
				System.out.println("success");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return conn;
		}
	}
	
	
	
}

//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			System.out.println("load success");
//			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","doublekim","oracle");
//			System.out.println("SQL connection success");
//
//			
//		} catch (ClassNotFoundException e) {
//			System.out.println("드라이브 로드 실패");
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//		} catch (SQLException e) {
//			System.out.println("SQL 연결 실패");
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//		}

//		} catch (Exception e) {
//			System.out.println();
//			e.printStackTrace();
//		}