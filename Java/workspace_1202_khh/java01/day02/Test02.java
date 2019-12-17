package day02;

import java.util.Date;

public class Test02 {

	public static void main(String[] args) {
		
		String msg = "hello JAVA~~~"; //String 는 객체생성을 하지 않아도 쓸수있다 (java.lang.Strng msg = )
		System.out.println(msg);
		int size = msg.length();
		System.out.println(size);
		System.out.println(msg.toUpperCase());
		//java.util.Date date = new java.util.Date();
		Date date = new Date();
		System.out.println(date);
		System.out.println(date.toLocaleString());
		
		java.sql.Date date2;
		
	}

}
