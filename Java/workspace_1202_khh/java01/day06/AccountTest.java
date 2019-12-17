package day06;
/**
 * @author 김현호
 * @since 2019.12.09
 */
public class AccountTest {

	public static void main(String[] args) {
	//	Account[] a = new Account[5];
		
		Account a1 = new Account("홍길동", "2019-12-09-001", 5000);
		a1.print();
		
		Account a2 = new Account("강아지", "2019-12-09-002", 5000);
		a2.print();
		
		Account a3 = new Account();
		a3.print();
	}

}
