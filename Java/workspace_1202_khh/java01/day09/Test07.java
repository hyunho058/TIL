package day09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test07 {

	public static void main(String[] args) {
		System.out.println("======start======");
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("bookdata.txt"));
			while(scanner.hasNextLine()) {
				String[] data = scanner.nextLine().split(" ");
				task.Book book = new task.Book(data[0],Integer.parseInt(data[1]));
				System.out.println(book);
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("bookdata.txt 파일 확인 해 주세요");
		}
		finally {
			if(scanner != null) //null이게 되면 nullPointException 발생 하게된다 
				scanner.close();
		}
		
		System.out.println("======end======");
	}

}
