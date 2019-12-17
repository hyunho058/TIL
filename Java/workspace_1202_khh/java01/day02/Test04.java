package day02;

public class Test04 {

	public static void main(String[] args) {
		int num = 100;
		num = num + 20;
		
		int i = 99, j = 70;
		
		System.out.printf("i = %d, j = %d %n", i,j);
		int swap = i;
		i = j;
		j = swap;
		System.out.printf("i = %d, j = %d %n", i,j);
		
		System.out.println("===================");
		
		char c1 = 'B', c2 = 'W';
		
		System.out.printf("c1 = %c, c2 = %c %n", c1, c2);
		char swap1 = c1;
		c1 = c2;
		c2 = swap1;
		System.out.printf("c1 = %c, c2 = %c %n", c1, c2);
		
		System.out.println("===================");
		
		String s1 = "hello", s2 = "java";
		
		System.out.printf("s1 = %s, s2 = %s %n", s1, s2);
		String swap2 = s1;
		s1 = s2;
		s2 = swap2;
		System.out.printf("s1 = %s, s2 = %s %n", s1, s2);
		
		
	}

}
