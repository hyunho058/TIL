package day08;

public abstract class Animal {
	protected String kind = "동물의 종류";
	
	public Animal() {}
	public Animal(String kind) {
		super();
		this.kind = kind;
	}
	public abstract void print();
	public abstract void breath();//미완성이기 때문에 객체생성 불가
}
