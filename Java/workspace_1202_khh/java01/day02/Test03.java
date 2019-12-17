package day02;

public class Test03 {

	public static void main(String[] args) {
		int pw = 0;
		System.out.println(pw);
		pw = "        hello1234".trim().length(); //trim() -> 블랭크 제거 메소드
		pw = "        hello1234".trim().toUpperCase().length(); //trim() -> 블랭크 제거 메소드
		System.out.println(pw);
		System.out.println(pw>8);
		pw = 0;
		
		System.out.println("----------------------");
		
		String msg = null;
		System.out.println(msg);
		msg = "hello java ~~~~~~~~";
		System.out.println(msg);
		String msg2 = msg;
	}
	
}
