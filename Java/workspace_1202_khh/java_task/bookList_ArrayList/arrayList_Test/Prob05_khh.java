package arrayList_Test;

import java.util.Scanner;

public class Prob05_khh {

	public static void main(String[] args) {
		// ArrayList 이용한 북리스트
		BookMgr_1 mgr_1 = new BookMgr_1();
		Scanner scan = new Scanner(System.in);
		boolean loof = true;
		String menuNum;
		String end;

		mgr_1.addBook_1(new Book("Java Program", 12500));
		mgr_1.addBook_1(new Book("SQL Fundamentals", 1500));
		mgr_1.addBook_1(new Book("Java1 Program", 12500));

		while (loof) {

			System.out.println("원하는 메뉴 번호를 입력하세요..");
			System.out.println("1 출	력");
			System.out.println("2검	색");
			System.out.println("3삭	제");
			System.out.println("4추	가");
			System.out.println("9 종	료");

			menuNum = scan.nextLine().trim();

			switch (menuNum) {
			case "1":
				System.out.println("입력된 정보");
				mgr_1.getBook_1();
				break;
			case "2":
				System.out.println("검색 정보");
				mgr_1.search_1();
				break;
			case "3":
				System.out.println("삭제 ");
				mgr_1.remove_1();
				break;
			case "4":
				System.out.println("수정 ");
				mgr_1.update_1();
				break;
			case "9":
				System.out.println("종료 할거니?? 종료 할거면 'yes'눌러 ");
				end = scan.nextLine();
				if ((end.toUpperCase()).equals("yes".toUpperCase())) {
					loof = false;
					scan.close();
					scan = null;
					break;
				} else {
					System.out.println("다른거 눌렀어");
					break;
				}
			default:
				System.out.println("번호를 잘못 입력했어");
				continue;
			}
		}
//		System.out.println("=== 책 목록 ===");
//		mgr.printBookList();
//		System.out.println("=== 책 가격의 총합 ===");
//		mgr.printTotalPrice();	
//		
//		System.out.println("검색할 도서명 입력");
//		mgr.searchTitle();
//		BookMgr mgr1 = new BookMgr(8); //북리스트 배열 사이즈 병경 가능한 생성자 사용
//		
//		mgr1.addBook(new Book("1Java Program",12500));
//		mgr1.addBook(new Book("1JSP Program",1500));
//		mgr1.addBook(new Book("1SQL Fundamentals",1500));
//		mgr1.addBook(new Book("1JDBC Program",1500));
//		mgr1.addBook(new Book("1EJB Program",2500));
//		mgr1.addBook(new Book("1EJB Program",2500));
//		mgr1.addBook(new Book("1EJB Program",2500));
//		mgr1.addBook(new Book("1EJB Program",2500));
//		
//		System.out.println("=== 책 목록 ===");
//		mgr1.printBookList();
//		System.out.println("=== 책 가격의 총합 ===");
//		mgr1.printTotalPrice();	
	}

}
