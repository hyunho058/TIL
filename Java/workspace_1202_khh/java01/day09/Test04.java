package day09;

import javax.swing.JOptionPane;

public class Test04 {

	public static void main(String[] args) {
		Controller cmd = null;
		//람다 함수
		Controller insert  =()-> System.out.println("Insert 수행");
		Controller update = ()->  System.out.println("update 수행");
		Controller delete = ()-> System.out.println("delete 수행");
		

		insert.exec();
		update.exec();
		delete.exec();

		String msg = JOptionPane.showInputDialog("명령을 입력. delate-1, insert-2, update-3");

		switch (msg) {
			case "1":
			case "delete":
				cmd =delete;
				break;

			case "2":
			case "insert":
				cmd = insert;
				break;

			case "3":
			case "update":
				cmd = update;
				break;
			
			default:
				System.out.println("명령 선택을 다시하세요");
//				cmd = new ListCommand();
				break;
		}
		if(cmd != null) {
			cmd.exec();
		}

	}
}
