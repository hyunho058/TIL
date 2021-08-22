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
		
		//emp table 내용 호출
		//Oracle DB 연동 ->driver setting
		System.out.println("JDBC TEST");
		
		
		String sql = "select * from emp where deptno = ?";  // '?'는 PreparedStatement 가 처리해준다(Statement는 불가능)
		
		Connection con = null;
		PreparedStatement ps = null;  //Statement 보다 성능이 더 좋다.
		ResultSet rs = null;  //select 를 처리하면 ResultSet type 으로 반환 ,insert , delete, uppdate 는 정수형으로 반환
		
		try {
			
			con = jdbcUtil.getConnection();
			System.out.println(con);
			
			//3.생성된Connection으로부터 Statememt생성
			ps = con.prepareStatement(sql);
			
			// ? 값 setting
			String deptno = JOptionPane.showInputDialog("debtno");
			ps.setInt(1, Integer.parseInt(deptno));
			
			
			//4.생성된 Statement를 이용해서 sql수행(execute, executeUpdate, executeQuery)
			rs = ps.executeQuery();
			
			//5.결과 처리(ResultSet, int)
			// rs.next()=> 다음줄로 내려가서 데이터있으면 true 없음녀 false
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
			System.out.println(e);
		}finally {
			jdbcUtil.close(con, ps, rs);
		}
		System.out.println("JDBC TEST END");
	}
}
