package day07;

public class Test02 {

	public static void main(String[] args) {
		
		Object a = new Animal();
		((Animal)a).breath();  //Object type a를 Animal로 형 변환(다운 캐스팅)
		
		
		Animal a1 = new Animal();
		a1.breath();
		Object obj = a1;
//		Animal temp = obj; 부모 자식 관계가 바뀌어서 성립하지 않음
		Animal temp = (Animal)obj; //다운 케스팅
		temp.breath();
		
		Dog d = new Dog();
		System.out.println(d.kind);
		Object obj2 = d;
		Animal a3 = (Animal)obj2;
		Dog d3 = (Dog)obj2;
		System.out.println(a3.kind);
		System.out.println(d3.kind);
		
		System.out.println("==============================");
		
		String msg ="hello Java";
		Object obj7 = a;
		obj7 = msg;
		obj7 = d;
		
		if(obj7 instanceof Dog) {
			System.out.println(((Dog)obj7).kind);
			System.out.println("1");
		}
		if(obj7 instanceof Animal) {
			System.out.println(((Animal)obj7).kind);
			System.out.println("2");
		}
		
		
		
		if(obj7 instanceof String) {
			System.out.println(((String)obj7).toUpperCase());
		}
		
		System.out.println("==============================");
		Fish f = new Fish();
		
		f.print();
	}

}
