package App;

import java.util.Scanner;

public class MyApp {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String menuNum = " ";
		boolean loof = true;
		String end = " ";
		
		while(loof) {
			
			System.out.println("원하는 메뉴 번호를 입력하세요..");
			
			System.out.println("1 입	력");
			System.out.println("2 수	정");
			System.out.println("3 삭	제");
			System.out.println("4 검	색");
			System.out.println("5 목록보기");
			System.out.println("9 종	료");

			menuNum = scan.nextLine().trim();
			
//			menuNum = scan.nextInt();
//			scan.nextLine();  // 엔터자리 초기화 안해주면 다음 String스캐너에서 엔터 값이 포함되어 원하는 값이 출력 안되기 때문에 해줘야함
			
			switch(menuNum) {
			case "1":
				System.out.println("정보 입력란");
				break;
			case "2":
				System.out.println("2수정 사항");
				break;
			case "3":
				System.out.println("3삭제 ");
				break;
			case "4":
				System.out.println("4검색 ");
				break;
			case "5":
				System.out.println("5목록 확인");
				break;
			case "9":
				System.out.println("종료 할거니?? 종료 할거면 'yes'눌러 ");
				//String end = " ";
				
				end = scan.nextLine();
//				scan.nextLine();
				
//				System.out.println(end=="yes");
//				System.out.println(end.equals("yes"));
				
				if(end.equalsIgnoreCase("yes")) {  //대, 소문자 구별안하고 조건  분석
					loof = false;
					scan.close();
					scan = null;
					return;
				}
				else
					System.out.println("다른거 눌렀어");
					break;
			default:
				System.out.println("번호를 잘못 입력했습니다.");
				continue;
			}
		}
	}

}
