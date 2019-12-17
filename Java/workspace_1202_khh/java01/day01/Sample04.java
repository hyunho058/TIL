package day01;

public class Sample04 {

	public static void main(String[] args) {
		
		int num = 99;
		System.out.println(num);
		
		double f = 99.9;
		System.out.println(f);
		
		char c = 'A';
		System.out.println(c);
		System.out.println((int)c);  //문자형을 아스키코드 값으로 출력
		
		boolean flag = 5 > 10;
		System.out.println(flag);
		
		String msg = "hello fava~~";
		System.out.println(msg.length());	//msg 문자 길이
		System.out.println(msg.toUpperCase());   //toUpperCase -> 대문자로 변환
	}
}
