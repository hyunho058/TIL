package day05;

import java.util.Arrays;

public class Test03 {

	public static void main(String[] args) {
//		System.out.println(args.length);
//		System.out.println(Arrays.deepToString(args));
//		
//		if(args.length == 0) {
//			System.out.println("파라미터를 넘겨주세요 ~");
//			return;
//		}
//		System.out.println(args[0].charAt(0)+"**");
		
		int[] num = new int[] {9,7,1,2,3};
		int[] num2 = num;
		int[] num3 = num.clone();
		
		num2[2] = 99;
		
		Arrays.sort(num3);
		
		System.out.println(Arrays.toString(num));
		System.out.println(Arrays.toString(num2));
		System.out.println(Arrays.toString(num3));
		
		String ur1 = "http://mayewb/admin/login.do";
		System.out.println(ur1.indexOf("/"));
		
		String r = ur1.substring(ur1.lastIndexOf("/"));
		System.out.println(r);
		
		String ur = ur1.substring(0,3);
		System.out.println(ur1);
		System.out.println(ur);
	}

}
