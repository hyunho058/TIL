package prob06;

public class Student extends Person{

	private int id;

	public Student() {
		super();
	}

	public Student(String name,int age, int id) {
		super();
		//super(name, age); 아래 super.setname 랑 같은 동작이다
		super.setName(name);
		super.setAge(age);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public void print() {
		super.print();
		System.out.printf("학     번:%-5d %n", id);
	}
	

}
