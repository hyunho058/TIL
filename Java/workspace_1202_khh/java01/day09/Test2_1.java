package day09;

public class Test2_1 {

	public static void main(String[] args) {
		A_1 a = new A_1("나무라");
		a.print();
		A_1.B_1 b1 = a.new B_1(); //이너 클래스 설정 방법
		b1.print();
		
		A_1.B_1 b = new A_1().new B_1(); //이너 클래스 설정 방법
		b.print();
	}

}

class A_1{
	String name = "홍말동";
	
	public A_1() {}

	public A_1(String name) {
		this.name = name;
	}
	
	void print() {
		System.out.println(name.charAt(0)+"**");
	}
	
	class B_1{ //이너클래스는 아우터 클래스의 자원을 가져다 쓸 수있다.
		void print() {
			System.out.println(name);
		}
	
	}

}

	
