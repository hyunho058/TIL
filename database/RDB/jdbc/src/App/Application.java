package App;

import javax.swing.JOptionPane;

import dao.DeptDAD;
import khh_1227.UsersDAO;
import vo.DeptVO;

public class Application {

	public static void main(String[] args) {
		DeptDAD dao = new DeptDAD(); //db연동 담당
		UsersDAO users = new UsersDAO();
		
		String id = JOptionPane.showInputDialog("id");
		String password = JOptionPane.showInputDialog("password");
		
		System.out.println(users.login(id, password));
		
		
//		for(DeptVO data:dao.deptList()) {
//			System.out.printf("%d  |  %-12s  |  %s %n",data.getDeptno(),data.getDname(), data.getLoc());
//		}
		
		//dao.insertDept("Edu2", "Chejudo");
		//dao.deleteDept(51);
		
		System.out.println("END");
	}

}
