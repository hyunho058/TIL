package day09;

import javax.swing.JOptionPane;

public class Test01 {

	public static void main(String[] args) {
		Command cmd = null;
		
		String msg = JOptionPane.showInputDialog("명령을 입력. delate-1, insert-2, update-3");
		
		switch(msg) {
			case"1":
			case"delete":
				cmd = new DeleteCommand();
				break;
				
			case"2":
			case"insert":
				cmd = new InsertCommand();
				break;
				
			case"3":
			case"update":
				cmd = new UpdateCommand();
				break;
				
			default:
				System.out.println("명령 선택을 다시하세요");
				cmd = new ListCommand();
				break;
		}
		
		
		if(cmd != null) {
			cmd.exec();
			cmd.base();
		}
	}

}
