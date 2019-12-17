package day10;

public class Test01 {

	public static void main(String[] args) {
		Employee<String> emp1 = new Employee("고갈비", "20191213");
		System.out.println(emp1);
		System.out.println(emp1.number.charAt(4));
		
		Employee<Integer> emp2 = new Employee("등갈비", 20191213);  //<> 안에는 Object type만 올수있다.
		System.out.println(emp2);
		System.out.println(emp2.number.getClass());
		
		Employee emp3 = new Employee("소갈비", 20191213); //<>type 선언하지 않으면 Object이다.
		System.out.println(emp3);
		System.out.println(emp3.number.getClass());
	}

}

class Employee<T>{
	String name;
	T number; //generic 마킹
	
	public Employee(String name, T number) {
		super();
		this.name = name;
		this.number = number;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public T getNumber() {
		return number;
	}
	public void setNumber(T number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", number=" + number + "]";
	}
	
	
	
}
