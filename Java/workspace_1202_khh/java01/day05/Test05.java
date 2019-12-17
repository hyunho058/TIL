package day05;

public class Test05 {

	public static void main(String[] args) {
		Employee emp1 = new Employee();
		Employee emp2 = new Employee();
		Employee emp3 = emp1;
		emp1.name = "홍길동";
		emp1.dept = "기술부";
		emp1.age = 31;
		
		emp2.name = "고길동";
		emp2.dept = "영업부";
		emp2.age = 30;
		
		emp1.print();
		emp2.print();
		emp3.print();
		
		Employee[]empList = new Employee[3];
		
	}
}

class Employee{
	String name = " ";
	String dept = " ";
	int age = 0;
	
	public void print() {
		System.out.printf("이름 = %s, 부서 = %s, 나이 = %d %n",name, dept, age);
		return;
	}
	
}