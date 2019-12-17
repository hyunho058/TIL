//		[1] int형 변수 year가 400으로 나눠떨어지거나 
//	    또는 4로 나눠떨어지고  
//	    100 나눠떨어지지 않을 때 인 조건식을 만들어 보세요(윤년공식).
//
//
//
//	[2] 1부터 20까지의 정수 중에서 
//	    2또는 3의 배수가 아닌 수의 총합을 구해 보세요  .
//
//	public class Prob2 {
//		public static void main(String[] args) { 
//			int sum = 0; 
//			//ToDo
//			System.out.println("sum="+sum);  
//		}
//	}
//
//
//	[3]
//	1+(1+2)+(1+2+3)+(1+2+3+4)+...+(1+2+3+...+10) 의 결과를 구해 보세요
package task;

public class Prob02_1 {

	public static void main(String[] args) {

		int year1 = 2000;
		if((year1 % 400 == 0) || ((year1 % 4 == 0) && (year1 % 100 != 0)))
			System.out.println("윤년");
		else
			System.out.println("34234");
		
		int num = 0;
		for(int i = 1; i < 21; i++) {
			
			if(i % 2 == 0 || i % 3 == 0) {
				num += i;
				System.out.println(i);
			}
		}
		
	}

}
