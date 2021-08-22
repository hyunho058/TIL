package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.jdbcUtil;
import vo.DeptVO;

public class Test03_dept {

	public static void main(String[] args) {
		
		for(DeptVO data:deptList()) {
			System.out.printf("%d  |  %-12s  |  %s %n",data.getDeptno(),data.getDname(), data.getLoc());
		}
		
		
	}
	// JDBC Template (반복적으로 사용하는 코드)
	public static List<DeptVO> deptList() {
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
}
