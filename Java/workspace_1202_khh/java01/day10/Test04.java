package day10;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

public class Test04 {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
//		List<String> list = new Vector<String>(); //java synchronized
		System.out.println(list.size());
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("d"); //ArrayList 에서는 중복 데이터 허용 된다.
		System.out.println(list.size());
		//for문 기반 반복
		for(int i = 0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println("고갈비 ? "+list.contains("고갈비"));
		
		System.out.println("=================================");
		//Iterator 기반 반복
		Iterator<String> it = list.iterator();
		while(it.hasNext()) {
			String data = it.next();
			System.out.println(data.charAt(0)+"**");
			System.out.println(data);
		}
		//Iterator 이용한 데이터 삭제
	//	String rname = JOptionPane.showInputDialog("삿제할 이름 입력");
		it = list.iterator();
		
		while(it.hasNext()) {
			String data = it.next();
	//		if(rname.equals(data)) {
	//			it.remove();
	//		}
		}
		
		System.out.println(list);
		list.add(2, "qqq"); //list에 중간 삽입 역할을 알아서 해준다.
		System.out.println(list);
		
	}

}
