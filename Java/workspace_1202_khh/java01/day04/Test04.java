package day04;

public class Test04 {

	public static void main(String[] args) {
		int [] eng = new int[5];
		String [] names = new String[5];
		
		eng[0]= 99;
		eng[1]= 1;
		eng[2]= 100;
		eng[3]= 81;
		eng[4]= 67;
		
		names[0]= "김길동";
		names[1]= "고갈비";
		names[2]= "강아지";
		names[3]= "박통길";
		names[4]= "이나라";
		
		double sum = 0;
		double avg = 0.0;
		
		for(int i = 0; i<eng.length; i++) {
			System.out.print(eng[i] + " ");
			
			sum += eng[i];
		}
		System.out.println();
		
		avg = sum/eng.length;
		System.out.printf("합 = %d, 평 = %.2f %n",(int)sum, avg);
		
		System.out.println("=========================");
		
		for (int i = 0; i < names.length ; i++) {
			
			if(names[i] != null && names[i].length() > 0) {
				System.out.print(names[i].charAt(0)+"** ");
				System.out.printf("점수 = %d ",eng[i]);
				System.out.println();
			}
		}
		
	}

}
