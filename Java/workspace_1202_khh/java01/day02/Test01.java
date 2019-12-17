package day02;

public class Test01 {
	// 클래스의 구성요서
	//Date(변수) + 기능요소(함수(method))
	//멤버 베리어블
	public static void main(String[] args) {
		//Date (지역(Local) 베리어블), 초기화 해야 샤용 가능
		
		//기본형
		int i = 900;
		long i2 = 99999999999999900l; //long type 선언뒤에는 'l'이 붙어야 한다
		
		double f = 99.9;
		//double f를 float f2를 강제 형변환(casting)
		float f2 = (float)f;
		
		char c = 'A';
		//char c을 int c2로 형변환(promotion)
		int c2 = c;
		
		int c3 = 97;
		//int c3 을 char c4로 강제 형변환(casting)
		char c4 = (char)c3; //c3을 char type 으로 형변환 (2byte 가 넘으면 인식하지 못해 형변환을 해줘야함)
		
		System.out.println(c);
		System.out.println(c2);
		System.out.println(c4);
		
		System.out.println("----------------------");
		System.out.printf("1/2 = %d %n" ,1/2);
		System.out.printf("1/2 = %.2f %n" ,1/2.); //1/2. 실수형임을 나타냄
		
		boolean b = true;
		
		System.out.printf("%d, %.1f, %c, %b %n" , i,f,c,b );
		System.out.printf("%s, %s, %s, %s %n" , i,f,c,b );
		//System.out.printf("i = %d, f = %.2f, c = %c, b = %b %n" ,i,f,c,b );
		System.out.println(Integer.SIZE);
		
		//참조형
		String name = "홍길동";
		System.out.println(name);
	}

}
