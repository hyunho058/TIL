package day06;

import java.util.Arrays;

import day05.Calc;
import util.MyUtil;

public class Test01 {

	public static void main(String[] args) {
		int i = Calc.add('+',1,2,3,4,5,6);
		
		System.out.println(i);

		int [] d = {1,5,7,4,2,6,3};
		int [] r = MyUtil.sort(d);
		
		System.out.println(Arrays.toString(d));
		System.out.println(Arrays.toString(r));
		
		System.out.println(MyUtil.max(5, 6));
		System.out.println(MyUtil.min(5, 6));
	}

}
