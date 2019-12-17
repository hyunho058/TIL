package hashSet;

import java.util.Scanner;

public class MainStudent {

	public static void main(String[] args) {
		StudentList stu = new StudentList();
		Scanner scan = new Scanner(System.in);
		int selecNum = 0;
		boolean loof = true;

		stu.stuAdd(new Student_1("김말순", 18, 5, stu.idCount));
		stu.stuAdd(new Student_1("박구라", 25, 5, stu.idCount));
		stu.stuAdd(new Student_1("이순박", 12, 5, stu.idCount));
		stu.stuAdd(new Student_1("고도리", 14, 5, stu.idCount));
		stu.stuAdd(new Student_1("고도리", 14, 5, 3));


		while (loof) {
			System.out.println("목록 - 1, 검색 - 2, 삭제 - 3, 종료 - 9;");
			selecNum = scan.nextInt();
			scan.nextLine();
			
			switch (selecNum) {
			case 1:
				stu.print();
				break;
			case 2:
				stu.search();
				break;
			case 3:
				stu.del();
				break;
			case 9:
				System.out.println("진짜 종료할거냐?? yes - y, no - n");
				String yes = scan.nextLine().trim();
				if (yes.equals("y")) {
					loof = false;
					scan.close();
					scan = null;
					break;
				} else {
					continue;
				}
			}

		}

	}

}
