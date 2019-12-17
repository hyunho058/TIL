package day09;

public class Test09 {

	public static void main(String[] args) {
		Account account = new Account("홍길동", "001", 1000);
		//user에게 메세지를 띄우기위해 Account class에서 트라이케치한게 아니라 메인에서 트라이케치함.
		try {
			account.output(50);
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
//내가 예외처리할 메세지를 출력하고 싶을때 사용하는 Exception class
class MoneyException extends Exception{  
	MoneyException(){
		super("계좌 잔고 부족");
	}
	MoneyException(String msg){
		super(msg);
	}
}

class Account extends Object{
	//맴버 변수
	String name;
	String number;
	int money;
	
	public Account(String name, String number, int money) {
		super();
		this.name = name;
		this.number = number;
		this.money = money;
	}
	public void input(int money) {
		this.money += money;
	}
	public void output(int money) throws MoneyException {
		if(this.money < money) {
			throw new MoneyException();
		}
			//this.money -= money;
			System.out.println(this.money -= money);
	}
	@Override
	public String toString() {
		return "Account [name=" + name + ", number=" + number + ", money=" + money + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + money;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (money != other.money)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}
}