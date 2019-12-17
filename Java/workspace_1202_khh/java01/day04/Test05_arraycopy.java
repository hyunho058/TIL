package day04;

import java.util.Arrays;

public class Test05_arraycopy {

	public static void main(String[] args) {
		
		int[] num = new int[10];
		int[] temp = new int[(num.length)*2];
		int randNum = 0;
		
		for(int i = 0; i<num.length; i++) {
			randNum = (int)(Math.random()*10)+1;
			//System.out.println(num[i]);
			if (i == 0) {
				num[i] = randNum;
			}
			else {
				for(int j = 0; j<num.length; j++) {
					if(num[j] == randNum) {
						i--;
						break;
					}
					if(j == (((int)num.length)-1) && num[j] != randNum){
						num[i] = randNum;
						break;
					}
				}
			}
		}
		
		System.out.println(Arrays.toString(num)); //array 내용 출력
		
		
		
		
		System.out.println("============================================");
		
		System.arraycopy(num, 0, temp, 0, num.length); //array 복사  API
		System.out.println(Arrays.toString(temp));
	}

}
