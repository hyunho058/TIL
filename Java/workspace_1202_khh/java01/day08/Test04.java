package day08;

public class Test04 {

	public static void main(String[] args) {
		String msg1 = "hello";
		String msg2 = "hello";
		System.out.println(msg1+" "+msg2);
		System.out.println(msg1.equals(msg2));
		System.out.println(msg1 == (msg2));
		
		Person person1 = new Person("홍말숙",18);
		Person person2 = new Person("홍말숙",18);
		Person person3 = null;
		System.out.println(person1+" "+person2);
		System.out.println(person1.equals(person2));
		System.out.println(person1.equals(null));
		System.out.println(person1.equals(person3));
		System.out.println(person1.equals(msg1));
		
		System.out.println(msg1.toString()); //Override된 String class의 toString
		System.out.println(msg2);
		System.out.println(person1.toString());  //Object의 toString
		System.out.println(person2);
		
		System.out.println("=================================");
		
		//Override 다운케스팅 
		
		Object obj = person1;
		System.out.println(obj);
		System.out.println(obj.toString()); //Override로 인해 캐스팅 안해도됨 자식의 메소드 출력
		System.out.println(((Person)obj).name);
		
		obj = msg1;
		System.out.println(obj);
		System.out.println(((String)obj).toUpperCase());
		
	}

}

class Person{
	String name;
	int age;
	
	public Person() {
		super();
	}
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean f = false;
		
		if(obj instanceof Person) {
			Person p = (Person)obj;
			if(this.name.equals(p.name) && this.age == p.age) {
				f = true;
			}
		}
		return f;
	}
	
	@Override
	public String toString() {
		return "Person["+name+":"+age+"]"; //StringBuild로 바꿔야함
	}//Object의 toString를 Override
	
}
