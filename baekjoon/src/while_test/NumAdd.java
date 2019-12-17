package while_test;

public class NumAdd {

	public static void main(String[] args) {
		int num = 26;
		int swap = num;
		int n = 0;
		int count = 1;
		boolean loof = true;
		
		while(loof) {
			if(num == swap) {
				loof = false;
				break;
			}
			else {
				int a = swap%10;
				int b = swap/10;
				n = a+b;
				swap = n+(a*10);
				count ++;
				System.out.println(swap);
			}
		}
	}

}
