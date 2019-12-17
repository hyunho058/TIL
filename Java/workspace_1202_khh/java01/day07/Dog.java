package day07;

public class Dog extends Animal{
	String kind = "강아지 종류";
	String name;
	
	public Dog() {
		super("강아지");
	}

	public Dog(String kind, String name) {
		super("강아지");
		//super.kind = "강아지";
		this.kind = kind;
		this.name = name;
	}
	
	public String getSuperKind() {
		return super.kind;
	}

	public void print() {
		System.out.printf("%6s, %6s, %6s %n",super.kind, this.kind, this.name);
	}
}
