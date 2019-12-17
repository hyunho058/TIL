package day03;

import java.io.FileInputStream;
import java.util.Scanner;

public class Test05 {

	public static void main(String[] args) throws Exception{
		System.out.println("App 시작........");
		int kor = 0, eng = 0, math = 0;
		String name = null;
		double sum = 0;
		double avg = 0;
		//boolean tF = true;
		
		Scanner scan = new Scanner(new FileInputStream("C://lib/score.txt")); //text 파일을 입력 박을거다
		
		
		//System.setIn(new FileInputStream("score.txt"));
		System.out.println("이름,국,영,수 입력 하세요");
		System.out.println("ex)홍길동, 90, 90, 18");
//		System.out.println("성적 처리를 종료하려면 'quit' 를 입력하세요");
//		String msg = scan.nextLine();
//		
//		if(msg.toLowerCase().equals("quit".toLowerCase())) // quit 의 입력값을 모두 소문자료 변환하여 조건 검사
//		{
//			if(scan != null) 
//			{
//				scan.close();
//				scan = null;
//			}
//			System.out.println("종료");
//			return;
//		}
		
		name = scan.next();
		kor = scan.nextInt();
		eng = scan.nextInt();
		math = scan.nextInt();
		scan.nextLine(); // 엔터 표시 없애주기위해
		
		sum = kor+eng+math;
		avg = sum/3.;
		
		System.out.printf("이름= %s /",name);
		System.out.printf(" 국 =%d / 영 =%d / 수 = %d %n",kor, eng, math);
		System.out.printf("합 = %.2f 평균 = %.2f %n",sum, avg);
		//학점처리 A B C D F
		
		char grade = 'F';
		if(avg >= 90) {
			grade = 'A';
		}
		else if(avg >=80) {
			grade = 'B';
		}
		else if(avg >=70) {
			grade = 'C';
		}
		else if(avg >=60) {
			grade = 'D';
		}
		else {
			grade = 'F';
		}
		
		System.out.printf("grade = %c %n",grade);
		
		
		if(avg > 79)
			System.out.println("합격");
		else
			System.out.println("불합격");
		
		//scan.close();
		scan = null;
		
		return;
	}

}
