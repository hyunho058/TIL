package day04;

public class Test03 {

	public static void main(String[] args) {
		

	A:	for(int i = 1; i<10; i++) {
			for(int j = 2; j<10; j++) {
				if(j == 5) 
					break A;
				System.out.printf("%2d *%2d %2d |",j,i,(j*i));
			}
			System.out.println();
		}
	
	}
	
}
