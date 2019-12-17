package day05;

public class Test04 {

	public static void main(String[] args) {
		
		int[] num = {1,2,3,4,5,6};
		char[] c = "ABCD".toCharArray();
		String[] s = {"hello", "java", "test"};
		
		for(int data : num) {
			if(data % 2 != 0)
				System.out.print(data +" ");
		}
		for(char data : c) {
			System.out.print(data +" ");
		}
		for(String data : s) {
			if(data.charAt(0) == 't')
				System.out.println(data);
		}
		
	}

}
