package util;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MyUtil {
	
	public static int[] sort (int[] a) {///////////////// sort
		
		int[] num = a.clone();
		
		for(int i = 0 ; i<num.length-1; i++) {
			int min = i;
			for(int j = i+1; j<num.length; j++) {
				if(num[min] > num[j] ) {
					min = j;
				}
			}
			if(i != min) {
//				int temp = num[i];
//				num[i] = num[min];
//				num[min] = temp;
				swap(num,i,min);
			}
		}
		
		return num;
	}
	
	public static void swap (int [] num, int i, int min) {
		int temp = num[i];
		num[i] = num[min];
		num[min] = temp;
	}
	
	public static int max (int num1, int num2) {////////////// maxNum
		return num1>=num2?num1:num2;
	}
	
	public static int min (int num1, int num2) {//////////////  minNum
		return num1<num2?num1:num2;
	}
	
	public static void iteratorPrint(Set<String> list) {
		Iterator<String> it = list.iterator();
		while(it.hasNext()) {
			String data = it.next();
			System.out.println(data+" ");
		}
	}

}
