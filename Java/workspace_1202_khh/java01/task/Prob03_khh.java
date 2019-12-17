//Prob3. Prob3 클래스의 main() 에서
// 주어진 문자열 배열을 생성하여 
// 배열의 내용을 역순으로 출력하할 수 있도록 
// main 메서드를 작성하세요. 
// 
//-	문자열 배열의 내용을 역순으로 출력하되 각 문자열 또한 역순으로 출력한다.
//-	입력으로 주어진 문자열 배열의 예 :
//   { "Java Programming" , "JDBC", "Oracle10g", "JSP/Servlet" }
//
//처리 결과의 예 : 아래 참고.
//gnimmargorP avaJ
//CBDJ
//g01elcarO
//telvreS/PSJ
//
//public class Prob3 {
//	public static void main(String[] args) {
//		String[] strData  = { "Java Programming" , "JDBC", "Oracle10g", "JSP/Servlet" };
//		
//	}
//}

package task;

import java.util.Scanner;

public class Prob03_khh {

	public static void main(String[] args) {
		String[] strDate = {"Java Programming" , "JDBC", "Oracle10g", "JSP/Servlet"};

		for(int i = 0; i<strDate.length; i++) {
			for(int j = strDate[i].length()-1 ; j >= 0 ; j--) {
				System.out.print(strDate[i].charAt(j));
			}
			System.out.println();
		}
		System.out.println("======================================");
		
		//예제 )문자열 참조변수  msg 가 "yes" 일때 true 인  조건식 
		/*
		String msg = null;
		
		Scanner scan = new Scanner(System.in);
		
		msg = scan.nextLine().trim();
		if(msg.equals("yes")) {
			System.out.println(msg.equals("yes"));
		}
		else
			System.out.println(!msg.equals("yes"));
		*/
		System.out.println("======================================");

//		예제) 1+(-2)+3+(-4)+... 과 같은 식으로 계속 더해나갔을 때 몇까지 더해야 총합이  100  이상이 되는지 구하시오 .
		
		boolean loof = true;
		int valueNum = 0;
		int num1 = 1;
		while(loof) {
			//num1++;
			if (num1 % 2 != 0) {
				valueNum = valueNum+num1;
				//System.out.println(valueNum);
			}
			else if (num1 % 2 == 0) {
				valueNum = valueNum+(num1*-1);
				//System.out.println(valueNum);
			}
			if (valueNum > 100) {
				System.out.printf("%d 번째 일때 100이상인 %d 이다 %n",num1, valueNum);
				loof = false;
			}
			num1++;	
		}
		System.out.println("======================================");
//		예제)arr 배열 에 담긴 모든 값을 더하는 프로그램을 완성하시오  .
		
		int[] arr = {10, 20, 30, 40, 50}; 
		int sum3 = 0;
		for (int i = 0; i <arr.length; i++) {
			sum3 += arr[i]; 
		}
		System.out.println("sum= "+sum3);
	}

}


