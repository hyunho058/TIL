package mytest;

public class Bookmgs_1 {
	Book_1[] booklist ;
	int i = 0;
	
	public Bookmgs_1() {
		booklist = new Book_1[5];
	}
	
	public void addBook(Book_1 book) {
		booklist[i] = book;
		i++;
	}
	
	public void printBookList() {
		for(int i = 0; i < booklist.length; i++ ) {
			System.out.println(booklist[i].getTitle());
		}
	}
	
	public void  printTotalPrice() {
		int sum = 0;
		for(int i = 0; i < booklist.length; i++ ) {
			sum += booklist[i].getPrice();
		}
		System.out.println(sum);
	}
}


//BookMgr 클래스의 제약조건 
//1. 책(Book) 객체 여러 개를 저장할 수 있는 책 목록(booklist)이라는 배열을 멤버변수로 가져야 한다. 
//2. 책 목록(booklist)이라는 배열 변수를 초기화하는 생성자 메서드를 작성해야 한다. 
//4. Book 객체를 booklist에 등록하는 addBook(Book book) 메서드를 작성하세요
//5. 책 목록을 화면에 출력하는 printBookList() 메서드가 있어야 한다. 
//6. 모든 책 가격의 합을 출력하는 printTotalPrice() 메서드가 있어야 한다. 
