package day03;

public class Test06 {

	public static void main(String[] args) {
		
		int sum = 0;
		for(int i = 0; i < 3; i++) {
			System.out.println("gello java" + i);
		}
		System.out.println("-------------------------");
		
		for(int i = 1 ; i <11 ; i++) {
			if(i % 2 == 0) {
				System.out.print(i + " ");
				sum +=i;
			}
		}
		System.out.println();
		System.out.println(sum);
		System.out.println("----------------------");
		
		String name = "홍길동";
		System.out.println(name.charAt(0) + "**");
		String msg = "hello java";
		System.out.println(msg.charAt(2)); // String type msg 의 문자열 중 2번째 index 값 출력
		
		for(int i = 0 ; i < msg.length() ; i++) {
			System.out.print(msg.charAt(i));
		}
		
	}
}
