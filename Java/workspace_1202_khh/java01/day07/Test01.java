package day07;

public class Test01 {

	public static void main(String[] args) {
		Dog d = new Dog();
		d.print();
		
		System.out.println(d.kind); //Dog 클래스의 카인드
		System.out.println(d.getSuperKind()); //메인메소드에서는 dog 부모의 kind를 직접 호출 불가하여 dog클래스에서 kind를 리턴해주는 메소드 필요
		
		//is a 관계
		// 모든 객체의 데이터 타입은 부모가 될수 있따.
	}
}

