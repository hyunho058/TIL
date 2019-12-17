package myTest;

public class Test1212 {

	public static void main(String[] args) {
		int a = 0;
		int [] numList = new int[2] ;
		int a1 = 10;
		int [] numList1 = new int[a1] ;
		
		numList[0] = 2;
		
		System.out.println(numList[0]);
		
		a = numList[0];
		System.out.println(a);
		
		for(int i = 0; i < numList1.length; i++) {
			numList1[i] = i;
			System.out.println(numList1[i]);
		}
		
		System.out.println(numList1.length-a1);
	}

}
