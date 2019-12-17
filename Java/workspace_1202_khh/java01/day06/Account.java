package day06;

public class Account {
	
	private String name;
	private String number;
	private int money;
	
	public Account() {
		//System.out.println("Account() 수행 => 기본 생성자");
		this("","",0); //생성자 함수에서 다른 생성자를 호출하는 문장인 this()는 첫번째 라인에만 작성해야함
		setName("");
		setNumber("");
		setMoney(0);
	}
	public Account(String name, String number, int money) {
		setName(name);
		setNumber(number);
		setMoney(money);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	/**
	 * @param money 입금 금액
	 */
	public void input(int money) {
		this.money += money;
	}
	/**
	 * @param money 출금 금액
	 * @return 춤금액
	 */
	public int output(int money) {
		if(this.money > money) {
			this.money -= money;
			return this.money;
		}
		else {
			return 0;
		}
	}
	/**
	 * 계좌 정보 출력
	 */
	public void print() {
		System.out.printf("고객명 - %s/ 계좌번호- %s/ 금액- %7d원 %n",name, number, money);
	}
	/**
	 * 
	 * @param from 출금 계좌
	 * @param to   입금 게좌
	 * @param money 계좌 이체금액
	 */
	
	public static void transfer(Account from, Account to, int money) {
		System.out.println("계좌이체");
		System.out.println(from.number + "->" + to.number);
		to.input(from.output(money));
	}
}
