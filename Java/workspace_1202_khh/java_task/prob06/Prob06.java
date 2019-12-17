package prob06;

public class Prob06 {

	public static void main(String[] args) {
		
//		Student st = new Student("길동", 18, 4452);
//		
//		st.print();
		
		Person[] perInfor = {
				new Student("홍길동",20,200201),
				new Teacher("이순신",30,"JAVA"),
				new Employee("유관순",40,"교무과")
		};
		
		for(Person data:perInfor) {
			data.print(); //java 폴리몰퓨즘 다형성(오버라이딩) =>부모타입으로 출력할때만 가능
			
//			if(data instanceof Student)
//				((Student)data).print();
//			if(data instanceof Teacher)
//				((Teacher)data).print();
//			if(data instanceof Employee)
//				((Employee)data).print();
		}
	}

}
