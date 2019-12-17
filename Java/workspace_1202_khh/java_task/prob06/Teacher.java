package prob06;

public class Teacher extends Person{
	
	private String subject;
	
	public Teacher() {
		super();
	}

	public Teacher(String name,int age,String subject) {
		super();
		super.setName(name);
		super.setAge(age);
		this.subject = subject;
	}

	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public void print() {
		super.print();
		System.out.printf("담당과목:%-5s %n",subject);
	}
	
}
