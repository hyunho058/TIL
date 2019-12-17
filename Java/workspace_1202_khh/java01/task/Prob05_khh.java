package task;

public class Prob05_khh {

	public static void main(String[] args) {
		BookMgr mgr = new BookMgr();  //기본생성자 사용
		
		mgr.addBook(new Book("Java Program",12500));
		mgr.addBook(new Book("JSP Program",1500));
		mgr.addBook(new Book("SQL Fundamentals",1500));
		mgr.addBook(new Book("JDBC Program",1500));
		mgr.addBook(new Book("EJB Program",2500));
	
		System.out.println("=== 책 목록 ===");
		mgr.printBookList();
		System.out.println("=== 책 가격의 총합 ===");
		mgr.printTotalPrice();	
		
		System.out.println("검색할 도서명 입력");
		mgr.searchTitle();
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
