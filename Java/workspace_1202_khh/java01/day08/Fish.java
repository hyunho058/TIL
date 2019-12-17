package day08;

public class Fish extends Animal{
	String name;
	
	public Fish() {
		super("Fish");
	}

	public Fish(String name) {
		super("Fish");
		this.name = name;
	}
	
	@Override
	public void print() {
		System.out.printf("%6s %6s %6s %n",this.kind, super.kind, this.name);
	}
	
	@Override
	public void breath() {
		System.out.println("아가미 숨쉬기...");
	}

}
