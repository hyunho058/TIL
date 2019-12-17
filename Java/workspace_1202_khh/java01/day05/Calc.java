package day05;

public class Calc {
	
//	public static int add(int a, int b) {
//		return a+b;
//	}
	
	
	public static int add(char c, int ...a) { //가변인자 처리 '...a' =>내부적으로 배열로 처리됨
		int sum = 0;
		
		for(int i  = 0; i<a.length ; i++){
			sum +=a[i];
		}
		return sum;
	}
	
	//method 오버로딩 (파라미터의 개수나, type 이 달라야 할 수 있음
	public static double add(double a, double b) {
		return a+b;
	}
	
}
