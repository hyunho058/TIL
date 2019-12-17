package day06;

public class AccountTest2 {

	public static void main(String[] args) {
		
		Account a1 = new Account();
		a1.setName("홍길동");
		a1.setNumber("2019-12-09-001");
		a1.setMoney(5000);
		//a1.input(2000);
		a1.print();
		
		Account a2 = new Account();
		a2.setName("홍미홍");
		a2.setNumber("2019-12-09-002");
		a2.setMoney(10000);
		//a2.output(3000);
		a2.print();
		

	}

}
