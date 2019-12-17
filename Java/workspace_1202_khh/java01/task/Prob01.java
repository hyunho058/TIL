package task;

public class Prob01 {

	public static void main(String[] args) {
		int num = 0;
		System.out.println(num < 0 ?"음수":(num > 0) ?"양수":"0" );
		int num1 = 10;
		System.out.println(num1 < 0 ?"음수":(num1 > 0) ?"양수":"0" );
		int num2 = -99;
		System.out.println(num2 < 0 ?"음수":(num2 > 0) ?"양수":"0" );
		
		System.out.println("-------------------------------------------");
		
		char ch = 'A';
		char lowerCase = (ch >= 'A' && ch <='Z') ? (char)(ch+32) : ch ;
		System.out.printf("ch : %c " ,ch);
		System.out.println(" to lowerCase : "+lowerCase);
	}

}




//==== 여기서 부터는 제출하세요 ======
//
//public class Prob1 {
//	public static void main(String[] args) {
///*
// 아래는 변수 num의 값에따라  양수 음수  0을 출력하는  코드이다.
//삼항 연산자를 이용해서 에 알맞은 코드를 완성하세요. 
//힌트:   삼항 연산자를 두번 이용   
//*/
//	int num = -90;
//	System.out.println(num > 0? "양수":(_______________________));
//
//
//	/*
//        다음은 대문자를 소문자로 변경하는 코드입니다.
//        변수 ch에 저장된 문자가 대문자 인 경우에만 
//	소문자로 변경하는 코드를 완성 합니다.
//  	*/
//	char ch = 'P';
//	char lowerCase = ________삼항 연산 자로 처리________;
//	System.out.print("ch:"+ch);
//	System.out.println(" to lowerCase :"+lowerCase);
//	}
//}     