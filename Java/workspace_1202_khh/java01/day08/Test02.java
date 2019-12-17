package day08;

public class Test02 {

	public static void main(String[] args) {
		SingleTone s = SingleTone.getInstance();
		SingleTone s1 = SingleTone.getInstance();
		
		System.out.println(s);
		System.out.println(s1);
		
	}

}

class SingleTone{
	private static SingleTone s;
	private SingleTone(){
		System.out.println("SingleTone calss 생성");
	} //생성자 앞에 private 붙이면 다른 클래스에서 객체생성 불가
	
	public static SingleTone getInstance() {
		if(s == null) {
			s = new SingleTone();
		}
		return s;
	}
}