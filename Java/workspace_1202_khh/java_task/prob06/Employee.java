package prob06;

public class Employee extends Person{

	private String dept;

	public Employee() {
		super();
	}

	public Employee(String name,int age, String dept) {
		super();
		super.setName(name);
		super.setAge(age);
		this.dept = dept;
	}

	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}

	@Override
	public void print() {
		super.print();
		System.out.printf("부     서:%-5s %n", dept);
	}
}
