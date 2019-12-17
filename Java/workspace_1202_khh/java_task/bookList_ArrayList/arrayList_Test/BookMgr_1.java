package arrayList_Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class BookMgr_1 {
	List<Book> book = new ArrayList<>(); //arrayList =>class 배역
	Iterator<Book> it = book.iterator(); //
	Scanner scan = new Scanner(System.in);
	
	public BookMgr_1() {
	}
	
	//입력
	public void addBook_1(Book book) {
		this.book.add(book);
	}
	
	//출력
	public void getBook_1() {
		it = book.iterator();
		while(it.hasNext()) {
			Book data = (Book) it.next();
			System.out.println(data);
		}
	}
	
	//검색
	public void search_1() {
		System.out.println("도서명 검색하세요");
		//String search = JOptionPane.showInputDialog("책 이름 입력");
		String search = scan.nextLine().trim(); 
		it = book.iterator();
		while(it.hasNext()) {
			Book data = (Book) it.next();
			if(search.toUpperCase().contains(data.getTitle().toUpperCase())) {
				System.out.println(data);
				System.out.println("asdfasdf");
			}
			else
				System.out.println("오잉");
		}
	}
	
	//삭제
	public void remove_1() {
		System.out.println("삭제할 도서명 입력");
		String bookName = scan.nextLine().trim();
		it = book.iterator();
		while(it.hasNext()) {
			Book data = (Book) it.next();
			if(data.getTitle().toUpperCase().equals(bookName.toUpperCase())) {
				System.out.println(data);
				System.out.println("y를 누르면 삭제됩니다.");
				String yy = scan.nextLine().trim();
				if(yy.equals("y")) {
					it.remove();
					break;
				}
				else {
					break;
				}
			}
		}
		scan.close();
		scan = null;
	}
	
	//추가
	public void update_1() {
		System.out.println("수정할 도서 이름 입력");
		String bookName = scan.nextLine().trim();
		it = book.iterator();
		while(it.hasNext()) {
			Book data = (Book) it.next();
			
			if(data.getTitle().contains(bookName)) {
				
			}
		}
	}
	
	//종료
	
	public boolean end_1() {
		boolean b = true;
		System.out.println("종료 하려면 'yes'를 눌러");
		String end = scan.nextLine();
		if(end.equals("yes")) {
			b = false;
		}else {
			System.out.println();
		}
		return  b;
	}
}
