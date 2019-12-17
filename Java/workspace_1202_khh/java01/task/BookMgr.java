package task;

import javax.swing.JOptionPane;

public class BookMgr {

	Book[] booklist;
	int i = 0;
//			new Book("Java Program", 5000),
//			new Book("JSP Program", 4000),
//			new Book("SQL Fundamentals", 5500),
//			new Book("JDBC Program", 10000),
//			new Book("EJB Program", 7000),
	//기본 생성자
	public BookMgr() {
		booklist = new Book[5];
	}
	//생정자 . 북리스트에 크기를 변경하고 싶을때 출력
	public BookMgr(int size) {
		booklist = new Book[size];
	}
	//책 리스트 등록
	public void addBook(Book book) {
		if(i == booklist.length) {
			Book[] temp = new Book[booklist.length*2];
			System.arraycopy(booklist, 0, temp, 0, booklist.length);
			booklist = temp;
		}	
		booklist[i] = book;
		i++;
	}
	//책 목록 출력
	public void printBookList() { 
		for(int i = 0; i<booklist.length; i++) {
			//System.out.println(booklist[i].getTitle());
			booklist[i].print();
		}
	}
	//책 가격 합 출력
	public void printTotalPrice() {
		int sumPrice = 0;
		for(int i = 0 ; i <this.i; i++) {
			sumPrice +=booklist[i].getPrice();
		}
		
		System.out.println(sumPrice);
	}
	
	public void searchTitle() {
		String name = JOptionPane.showInputDialog("책 이름 검색");
		for(int i = 0; i<booklist.length; i++) {
			if((booklist[i].getTitle()).toUpperCase().equals((name.trim()).toUpperCase())){
				//System.out.println(booklist[i].getTitle());
				System.out.println("검색 결과");
				booklist[i].print();
				break;
			}
		}
	}
}
//1. 책(Book) 객체 여러 개를 저장할 수 있는 책 목록(booklist)이라는 배열을 멤버변수로 가져야 한다. 
//2. 책 목록(booklist)이라는 배열 변수를 초기화하는 생성자 메서드를 작성해야 한다. 
//4. Book 객체를 booklist에 등록하는 addBook(Book book) 메서드를 작성하세요
//5. 책 목록을 화면에 출력하는 printBookList() 메서드가 있어야 한다. 
//6. 모든 책 가격의 합을 출력하는 printTotalPrice() 메서드가 있어야 한다. 
//Java Program
//JSP Program
//SQL Fundamentals
//JDBC Program
//EJB Program
