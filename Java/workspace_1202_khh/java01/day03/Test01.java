package day03;

public class Test01 {

	public static void main(String[] args) {
		// == dhk equals() 메소드이 구분 사용
		//기본형의 값을 비교시 ==
		//객체를 비교시 equals()
		
		int i = 90, j = 90, k = 99;
		System.out.printf("%b, %b %n", i==j, i==k, i!=k);
		
		System.out.println("-------------------------");
		
		String s1 = new String("java");
		String s2 = new String("java");
		
		
		System.out.printf(" s1 = %s %n s2 = %s %n", s1, s2);
		System.out.printf(" s1==s2  %b %n", s1 == s2);
		System.out.printf(" s1==s2  %b %n", s1.equals(s2));
		
		String s3 = "java";
		String s4 = "java";
		
		System.out.println("-------------------------");
		
		System.out.printf(" s1 = %s %n s2 = %s %n", s3, s4);
		System.out.printf(" s1==s2  %b %n", s3 == s4);
		System.out.printf(" s1==s2  %b %n", s3.equals(s4));
		
		System.out.println("-------------------------");
		
		System.out.printf(" s1 = %s %n s4 = %s %n", s1, s4);
		System.out.printf(" s1==s4  %b %n", s1 == s4);
		System.out.printf(" s1==s4  %b %n", s1.equals(s4));
		
		System.out.println(Integer.MIN_VALUE);
		System.out.println(Integer.MAX_VALUE);
	}

}
