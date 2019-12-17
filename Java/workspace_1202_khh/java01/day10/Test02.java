package day10;

public class Test02 {

	public static void main(String[] args) {
//		Empoyee2<String, String> emp1 = new Empoyee2<String, String>("고등어", "20191213");
//		System.out.println(emp1);
		
		Empoyee2<String, Integer> emp2 = new Empoyee2<String, Integer>("고등어", 20191213);
		System.out.println(emp2.number/1000);
		
		Empoyee2<String, Double> emp3 = new Empoyee2<String,Double>("고등어", 20191213.0);
		System.out.println(emp3);
		
		Empoyee2 emp4 = new Empoyee2("고등어", 20191213);
		System.out.println(emp4);
		
	}

}

class Empoyee2<T,P extends Number>{   //제너릭(상속)을 통해 type에 제약 걸기
	T name;
	P number;
	public Empoyee2(T name, P number) {
		super();
		this.name = name;
		this.number = number;
	}
	@Override
	public String toString() {
		return "Empoyee2 [name=" + name + ", number=" + number + "]";
	}
	
	
	
}
