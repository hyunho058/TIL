package day10;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JOptionPane;

public class Test06 {

	public static void main(String[] args) {
		Set<Book> bookList = new HashSet<Book>();
		
		//등록 C
		if(bookList.add(new Book("java", 300))) {
			System.out.println("등록 완료");
		}else {
			System.out.println("등록된 data가 있다.");
		}
		bookList.add(new Book("SQL", 1500));
		bookList.add(new Book("JSP", 5500));
		if(bookList.add(new Book("java", 300))) {
			System.out.println("등록 완료");
		}else {
			System.out.println("등록된 data가 있다.");
		}
		
		//출력
		Iterator<Book> it = bookList.iterator();
		while(it.hasNext()) {
			Book book = (Book) it.next();
			System.out.println(book);
		}
		
		System.out.println("=======================");
		//검색
		String search = JOptionPane.showInputDialog("책 이름 입력");
//		for(int i =0; i<bookList.size(); i++) {
//			Book book = (Book) it.next();
//			if(search.equals(book.title))
//				System.out.println(book);
//		}
		
		it = bookList.iterator();
		while(it.hasNext()) {
			Book book = (Book) it.next();
			
			if(search.toUpperCase().equals(book.title.toUpperCase())) {
				System.out.println(book);
			}
		}
		//삭제
		Scanner scanner = new Scanner(System.in);
		System.out.println("삭제할 도서 이름 입력");
		String msg = scanner.nextLine().trim();
		
		it = bookList.iterator();
		while(it.hasNext()) {
			Book book = (Book) it.next();
			
			if(book.title.contains(msg)) {
				System.out.println(book);
				System.out.println("y를 누르면 삭제 됩니다.");
				String yy = scanner.nextLine().trim();
				if(yy.equals("y")) {
					it.remove();
				}
				else if(yy.equals("n")) {
					break;
				}
			}
		}
		
		//출력
		it = bookList.iterator();
		while(it.hasNext()) {
			Book book = (Book) it.next();
			System.out.println(book);
		}
		//수정
		
		System.out.println("수정할 도서 이름 입력");
		msg = scanner.nextLine().trim();
		
		it = bookList.iterator();
		while(it.hasNext()) {
			Book book = (Book) it.next();
			
			if(book.title.contains(msg)) {
				book.title = book.title + "_";
			}
		}
		
		//출력
		it = bookList.iterator();
		while(it.hasNext()) {
			Book book = (Book) it.next();
			System.out.println(book);
		}
		
		scanner.close();
		scanner = null;
	}

}
class Book{
	String title;
	int price;
	
	public Book(String title, int price) {
		super();
		this.title = title;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + price;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (price != other.price)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
}
