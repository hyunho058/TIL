package day02;

public class Test05 {

	public static void main(String[] args) {
		int num = 127;
		System.out.println(num);
		
		char c = 'a';
		System.out.printf("숫자? %b %n" ,Character.isDigit(c));  //Character.isDigit(c) 숫자인지 판별하는 캐릭터 메소드
		
		System.out.println("abc" + "def"); //스트링 " " + " " 하면 머지작업으로 인해 기능저하 사용 자제요망
		System.out.println(1+1+1);
		System.out.println(1+1+ "1");
		System.out.println("1"+1+1);
		
		String r1 = "100" + 1;
		int r2 = Integer.parseInt("100") + 1;
		System.out.printf("r1 = %s, r2 = %d %n", r1,r2);
		
		double r3 = Double.parseDouble("100.5") + 1;
		System.out.println(r3);
		
		int r4 =(int) (Double.parseDouble("100.5")) + 1;
		System.out.println(r4);
		
		System.out.println(Math.PI);
	}

}
