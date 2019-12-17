package day09;

public class Test02 {

	public static void main(String[] args) {
			B b = new B(new A("강마루"));
			b.print();
	}

}

class A{
	String name;

	public A(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

class B{
	A a;
	
	public B() {
		super();
	}
	public B(A a) {
		super();
		this.a = a;
	}
	
	public A getA() {
		return a;
	}
	public void setA(A a) {
		this.a = a;
	}

	void print() {
		System.out.println(a.name);
	}
}