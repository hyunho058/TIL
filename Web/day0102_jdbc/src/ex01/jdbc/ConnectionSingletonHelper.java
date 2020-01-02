package ex01.jdbc;
import java.sql.*;
import java.util.*;
/*
	ConnectionHelper Ŭ������ ����
	�Ź� ����̹� �ε�,..
	Connection ����
	
	������ �ϳ��� ���μ������� �ϳ��� ����
	�����ϸ� ���ٵ�....
	
	�ذ��� > �����ڿ�(�̱���)
*/

public class ConnectionSingletonHelper {
	//�ϳ��� ���μ������� �������� ����� �� �ִ� �����ڿ�(static)
	
	private static Connection conn;
	private ConnectionSingletonHelper() { }
	
	public static Connection getConnection(String dsn) {
		//Connection conn = null;
		
		if( conn != null ) {
			return conn;
		}
		
		try {
			
			if( dsn.equals("mysql")) {
				
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/sampleDB",
						"kingsmile", "mysql");
				
			} else if( dsn.equals("oracle")) {
				
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521:xe",
						"kingsmile", "oracle");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return conn;
		}
	}
	
	public static void close() throws SQLException {
		
		if( conn != null ) {
			
			if( !conn.isClosed() ) {
				conn.close();
			}
			
		}
	}
}







