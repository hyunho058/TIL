package day02;

public class Test06 {

	public static void main(String[] args) {
		int num = 99;
		num += 1; // num = num + 1
		num = num + 1;
		System.out.println(num);
		System.out.println(num % 10);
		System.out.println(num / 10);
		System.out.println(num++);
		System.out.println(++num);
		
		int jumsu = 102;
		
		boolean flag = jumsu >= 80;
		System.out.println("우수?" + flag);
		
		boolean flag1 = (jumsu >=0)&&(jumsu <101);
		System.out.println(flag1);
		
		System.out.println("----------------");
		System.out.printf("%c => %d %n", '0', (int)'0');
		System.out.printf("%c => %d %n", '1', (int)'1');
		System.out.printf("%c => %d %n", 'A', (int)'A');
		System.out.printf("%c => %d %n", 'a', (int)'a');
		
		char c = 'a';
		System.out.printf("숫자니 %b %n", Character.isDigit(c));
		flag = c >='0' && c <='9';
		System.out.printf("숫자냐 %b %n", flag);
		
		flag = c >='a' && c <='z';
		System.out.printf("소문자냐 %b %n", flag);
		System.out.printf("%c => %c %n",c ,(char)(c-32));
		
		//삼항 연산자
		int score = 100;
		String result = score >= 70 ?"합격":"재시험";
		System.out.println(score + " : " + result);
		System.out.println("----------------------");
		
		char ch = 'A';
		char r = (ch >= 'a' && ch <= 'z') ? (char)(ch-32):((ch >= 'A' && ch <= 'B') ? ch:ch);
		System.out.println(ch + " -> " + r);
	}

}
