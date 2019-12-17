package day03;

import java.util.Scanner;

public class Test03 {

	public static void main(String[] args) {
		
		String name = null;
		int kor = 0;
		int math = 0;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("이름 입력 = ");
		name = scan.nextLine();
		System.out.println("국어점수 입력 = ");
		kor =Integer.parseInt(scan.nextLine());
		System.out.println("수학점수 입력 = ");
		math = scan.nextInt();
		int sum = kor+math;
		double avg = sum/2.;
		scan.nextLine(); // cmd창에서 엔터 표시 소진
		
		System.out.printf("이름 = %s , 국어 점수 = %d, 수학 점수 = %d, 합 = %d, 평균 = %.2f %n" ,name, kor, math, sum, avg);
		System.out.printf("%.2f :  %s",avg, (avg>=80 ?"합격":"불합격"));
		
		scan.close(); // 자원 반납 
		scan = null; // 스켄을 쓸일이 없어서 null처리 (바비지 컬렉션에서 삭제처리 돕기위해(가비지 대상임을 알려주기위해)) =메모리 해제
		
		return;
	}
}
