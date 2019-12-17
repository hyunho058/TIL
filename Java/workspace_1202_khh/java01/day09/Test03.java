package day09;

import javax.swing.JOptionPane;

public class Test03 {

	public static void main(String[] args) {
		Controller cmd = new DeleteController();
		cmd.exec();

		Controller insert = new Controller() { // Android에서 이벤트처리 할떄 주로 사용함.
			@Override
			public void exec() {
				System.out.println("Insert 수행");
			}
		};
		Controller update = new Controller() {
			@Override
			public void exec() {
				System.out.println("update 수행");
			}
		};
		Controller delete = new Controller() {
			@Override
			public void exec() {
				System.out.println("delete 수행");
			}
		};

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

interface Controller {
	void exec();
}

class DeleteController implements Controller {
	@Override
	public void exec() {
		System.out.println("DeleteController 수행");
	}

}