package day07;

public class Animal {
	protected String kind = "동물의 종류";
	
	public Animal() {
		super(); //부모의 기본 생성자를 다녀오겠다
	}

	public Animal(String kind) {
		super();
		this.kind = kind;
	}
	public void breath() {
		System.out.println("폐로 숨쉬기...");
	}
}
