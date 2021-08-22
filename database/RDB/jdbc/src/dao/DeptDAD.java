package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.jdbcUtil;
import vo.DeptVO;

public class DeptDAD {
	
	public List<DeptVO> deptList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0;

		String sql = "select * from dept";
		
		List<DeptVO> list = new ArrayList<DeptVO>();

		try {
			con = jdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			// ? 값 세팅
			rs = ps.executeQuery(); // select 구문 처리 함수
			while(rs.next()) {
				//3의 데이터를 담으려면 class를 만들어야 한다(VO객체)
				DeptVO vo = new DeptVO(rs.getInt("deptno"),rs.getString("dname"),rs.getString("loc"));
				list.add(vo);
			}
			// 결과 값 처리
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//자원 반납
			jdbcUtil.close(con, ps, rs);
		}
		return list ;
	}
	
	public void deleteDept(int deptno) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0;

		String sql = "delete from dept where deptno = ?";

		try {
			con = jdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			//rs = ps.executeQuery(); 
			
			// ? 값 넣어주는 코드
			ps.setInt(1, deptno);
			
			row = ps.executeUpdate();  //dml 구문이기 때민에 low사용
			// 결과 값 처리
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(con, ps, rs);
		}

	}
	
	public void insertDept(String dname, String loc) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0;

		String sql = 
		"insert into dept(deptno,dname,loc) " +
		"values((select nvl(max(deptno),0)+1 from dept),?,?)";

		try {
			con = jdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			//rs = ps.executeQuery(); 
			ps.setString(1, dname);
			ps.setString(2, loc);
			
			row = ps.executeUpdate();  //dml 구문이기 때민에 low사용

			// 결과 값 처리
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(con, ps, rs);
		}

	}
}
