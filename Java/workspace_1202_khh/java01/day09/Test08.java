package day09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Test08 {

	public static void main(String[] args) {
		System.out.println("======start======");
		
		//변형된 try 구문 자원 반환을 따로 finally 로 안해줘도 됨.
		try (Scanner scanner = new Scanner(new File("bookdata.txt"));){
			StringTokenizer st = null;
			while(st.hasMoreTokens())  {
				st = new StringTokenizer(scanner.nextLine());
				String title = st.nextToken();
				int price =Integer.parseInt(st.nextToken());
				task.Book book = new task.Book(title, price);
				System.out.println(book);
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("bookdata.txt 파일 확인 해 주세요");
		}
		
		System.out.println("======end======");
	}

}
