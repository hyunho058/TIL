package day06;

import javax.swing.JOptionPane;

/**
 * @author 김현호
 * @since 2019.12.09
 */
public class AccountTest3 {

	public static void main(String[] args) {
		
		Account[] accounts = {
				new Account("김길동", "2019-12-09-001", 5000),
				new Account("이길동", "2019-12-09-002", 1000),
				new Account("박길동", "2019-12-09-003", 3000),
				new Account("고길동", "2019-12-09-004", 7000),
				new Account("허길동", "2019-12-09-005", 9000),
				new Account("강길동", "2019-12-09-006", 500),
		};
		
		System.out.println(accounts[1].getName());
		System.out.println("Account List");
		for(int i = 0; i<accounts.length; i++) {
			accounts[i].print();
		}
		System.out.println("Account search");
		String name = JOptionPane.showInputDialog("검색이름을 입력하세요");
		for(int i = 0; i<accounts.length; i++) {
			if(accounts[i].getName().equals(name.trim())) {
				accounts[i].print();
			}
			else {
				break;
			}
		}
		
	}

}
