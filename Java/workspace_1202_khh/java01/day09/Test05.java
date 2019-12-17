package day09;

import javax.swing.JOptionPane;

public class Test05 {

	public static void main(String[] args) {
		System.out.println("start");
		
		//String msg1 = JOptionPane.showInputDialog("숫자 입력");
		String msg = "10";
		int num = 0;
		int res = 0;
		
		try {
			num = Integer.parseInt(msg);
			res = 100/num;
			int [] numver = new int[num];
		}
		catch(NumberFormatException e) {
			System.out.println("문제 발생 -"+e);
			System.out.println("숫자로 입력된 문자열로 입력 필요함");
		}
		catch(ArithmeticException e) {
			System.out.println("문제 발생 -"+e);
			System.out.println("0을 제외한 숫자를 입력해줘 ");
		}
		catch(Exception e) { //모든 예외를 처리 할 수 있다.
			System.out.println("문제 발생 -"+e);
			//System.out.println(e.getMessage());
			e.printStackTrace();
			System.out.println("정보가 잘못 입력되었다.");
		}
		finally {
			System.out.println("자원반납 코드가 항상 수행");
			
		}
		
		System.out.println(num);
		System.out.println(res);
		System.out.println("end");
	}

}
