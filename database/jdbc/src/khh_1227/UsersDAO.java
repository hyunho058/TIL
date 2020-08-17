package khh_1227;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class UsersDAO {
	
	public UsersVO login(String id,String password) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql ="select * " + 
				"from users " + 
				"where id=? and password=? ";
		
		UsersVO users = new UsersVO();
		
		try {
			con = jdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while(rs.next()) {
				users = new UsersVO(rs.getString("id"), rs.getString("password"),rs.getString("name"),rs.getString("role"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbcUtil.close(con, ps, rs);
		}
		return users;
	}

}
