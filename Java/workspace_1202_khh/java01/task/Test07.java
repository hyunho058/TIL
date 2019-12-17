package task;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Test07 {

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("java01", "1234");
		map.put("java02", "2345");
		map.put("java03", "3456");
		map.put("java04", "1234");
		map.put("java05", "1234");
		map.put("java06", "1234");
		map.put("java07", "1234");
		map.put("java08", "1234");
		map.put("java09", "1234");
		map.put("java10", "1234");
		
		System.out.println(map.get("java01"));
		//map 구조 순회
		
		Set<String>keys = map.keySet();
		Iterator<String> it = keys.iterator();
		while(it.hasNext()) {
			String id = (String) it.next();
			System.out.println(id+" =" +map.get(id));
		}
		
		//login
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			System.out.println("ID 입력");
			String id = scan.nextLine().trim();
			
			System.out.println("PW 입력");
			String pw = scan.nextLine().trim();
			
			if(!map.containsKey(id)) {
				System.out.println("없는 id 입니다.");
				continue;
			}
			else {
				if(map.get(id).equals(pw)) {
					System.out.println("login 되었습니다.");
					break;
				}else {
					System.out.println("비밀번호 오류");
				}
			}
			
		}
		
		scan.close();
		scan = null;
		
		System.out.println("END");

	}

}
