package hashSet;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class StudentList {
	Set<Student_1> studentList = new HashSet<>();
	Iterator<Student_1> it = studentList.iterator();
	Scanner scan = new Scanner(System.in);
	int idCount=0;
	
	//등록
	public void stuAdd(Student_1 stu) {
		this.studentList.add(stu);
		idCount++;
	}
	
	//출력
	public void print() {
		System.out.println("학생 목록을 출력 합니다.");
		it = studentList.iterator();
		while(it.hasNext()) {
			Student_1 stu = (Student_1)it.next();
			System.out.println(stu);
		}
	}
	
	public void search() {
		System.out.println("검색할 이름을 입력해봐");
		String seach = scan.nextLine().trim();
		it=studentList.iterator();
		while(it.hasNext()) {
			Student_1 data = (Student_1)it.next();
			if(seach.toUpperCase().contains(data.getName().toUpperCase())) {
				System.out.println(data);
			}
			else {
				System.out.println("검색 결과 없다.");
			}
		}
	}
	
	public void del() {
		System.out.println("삭제할 이름을 검색해");
		String seach = scan.nextLine().trim();
		it=studentList.iterator();
		while(it.hasNext()) {
			Student_1 data = (Student_1)it.next();
			if(seach.toUpperCase().equals(data.getName().toUpperCase())) {
				System.out.println(data);
				System.out.println("삭제하려면 'y'를 눌러");
				String yes = scan.nextLine().trim();
				if(yes.equals("y")){
					it.remove();
				}
			}
			
		}
	}
	
}
