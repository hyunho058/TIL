package day03;

import java.util.Scanner;

public class Test04 {

	public static void main(String[] args) {
		System.out.println("App 시작........");
		int kor = 0, eng = 0, math = 0;
		String name = null;
		double sum = 0;
		double evg = 0;
		boolean tF = true;
		
		Scanner scan = new Scanner(System.in);
		
		while(tF) 
		{
		System.out.println("이름,국,영,수 입력 하세요");
		System.out.println("ex)홍길동, 90, 90, 18");
		System.out.println("성적 처리를 종료하려면 'quit' 를 입력하세요");
		String msg = scan.nextLine();
		
		if(msg.toLowerCase().equals("quit".toLowerCase())) // quit 의 입력값을 모두 소문자료 변환하여 조건 검사
		{
			if(scan != null) 
			{
				scan.close();
				scan = null;
			}
			System.out.println("종료");
			tF = false;
		}
		
		name = scan.next();
		kor = scan.nextInt();
		eng = scan.nextInt();
		math = scan.nextInt();
		scan.nextLine(); // 엔터 표시 없애주기위해
		
		sum = kor+eng+math;
		evg = sum/3.;
		
		System.out.printf("이름= %s /",name);
		System.out.printf(" 국 =%d / 영 =%d / 수 = %d %n",kor, eng, math);
		System.out.printf("합 = %.2f 평균 = %.2f %n",sum, evg);
		
		if(evg > 79)
			System.out.println("합격");
		else
			System.out.println("불합격");
		}
		

		scan.close();
		scan = null;
		
		return;
	}

}
