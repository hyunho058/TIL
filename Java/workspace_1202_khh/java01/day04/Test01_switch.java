package day04;

import java.io.FileInputStream;
import java.util.Scanner;

public class Test01_switch {

	public static void main(String[] args) throws Exception{
		
		System.out.println("App 시작........");
		int kor = 0, eng = 0, math = 0;
		String name = null;
		
		Scanner scan = new Scanner(new FileInputStream("C://lib/score.txt"),"UTF-8"); //text 파일을 입력 박을거다
		
		name = scan.next();
		kor = scan.nextInt();
		eng = scan.nextInt();
		math = scan.nextInt();
		scan.nextLine(); // 엔터 표시 없애주기위해
		
		double sum = kor+eng+math;
		double avg = sum/3.;
		
		System.out.printf("이름= %s /",name);
		System.out.printf(" 국 =%d / 영 =%d / 수 = %d %n",kor, eng, math);
		System.out.printf("합 = %.2f 평균 = %.2f %n",sum, avg);
		//학점처리 A B C D F
		
		char grade = 'F';
		
		switch((int)avg/10) {
			case 10:
			case 9:
				grade = 'A';
				break;
			case 8:
				grade = 'B';
				break;
			case 7:
				grade = 'C';
				break;
			case 6:
				grade = 'D';
				break;
			default:
				grade = 'F';
				break;
		}
		
//		if(avg >= 90) {
//			grade = 'A';
//		}
//		else if(avg >=80) {
//			grade = 'B';
//		}
//		else if(avg >=70) {
//			grade = 'C';
//		}
//		else if(avg >=60) {
//			grade = 'D';
//		}
//		else {
//			grade = 'F';
//		}
		
		System.out.printf("grade = %c %n",grade);
		
//		if(avg > 79)
//			System.out.println("합격");
//		else
//			System.out.println("불합격");
		
		scan.close();
		scan = null;
		
		switch(grade) {
			case 'A':
			System.out.println("만점입니다");
				break;
			case 'B':
				System.out.println("조금더 노력하세요");
				break;
			case 'C':
				System.out.println("많이 노력하세요");
				break;
			case 'D':
				System.out.println("위험합니다");
				break;
			default:
				System.out.println("......");
				break;
		}
		
		return;
	}
}
