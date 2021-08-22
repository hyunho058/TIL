package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.jdbcUtil;

public class Jdbc_template {

	public static void main(String[] args) {
		
	}
	//JDBC Template (반복적으로 사용하는 코드)
	public void temp() {
		Connection con = null;
		PreparedStatement ps= null;
		ResultSet rs = null;
		int row = 0;
		
		 String sql="select ";
		 
		 try {
			 con = jdbcUtil.getConnection();
			 ps = con.prepareStatement(sql);
			 //? 값 세팅
			 rs = ps.executeQuery(); //select 구문 처리 함수
			 row = ps.executeUpdate(); //dml(insert,delete,update) 구문 처리함수
			 
			//결과 값 처리
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbcUtil.close(con, ps, rs);
		}
		
	}

}
