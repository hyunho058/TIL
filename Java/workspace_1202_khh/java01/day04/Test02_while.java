package day04;

public class Test02_while {

	public static void main(String[] args) {
		
		boolean flag = true;
		
		for(int i = 0; i < 3; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.printf("%d,%d / ",i ,j);
			}
			System.out.println();
		}
		
		System.out.println("----------------------------");
		
		int i = 0;
		
		do {
			System.out.println("~~~~~");
			i++;
		}while(i>10);
		
		while(flag) {
			System.out.println("^&^");
			int randNum = (int)(Math.random()*10);
			
			System.out.printf("%d",randNum);
			if(randNum % 3 == 0) {
				flag = false; 
			}
		}
		
	}

}
