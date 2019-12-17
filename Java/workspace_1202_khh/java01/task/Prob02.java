package task;

public class Prob02 {

	public static void main(String[] args) {
		
		
		String sourceString = "everyday we have is one more than we deserve";
		String encodedString = "";
		// 참고 : 문자 'a'의 정수값은 97이며, 'z'는 122입니다. 
		
		StringBuilder sb = new StringBuilder(); //StringBuffer의 보완 단계

		for (int i = 0 ; i < sourceString.length() ; i++) {
			
			char w = ' ';
			if(sourceString.charAt(i) >= 'a' && sourceString.charAt(i) <='w') {
				//System.out.print((char)(sourceString.charAt(i)+3));
				w = (char)(sourceString.charAt(i)+3);
			}
			
			else if (sourceString.charAt(i) >= 'x' && sourceString.charAt(i) <='z') {
				//System.out.print((char)(sourceString.charAt(i)-22));
				w = (char)(sourceString.charAt(i)-23);
			}
			else if (sourceString.charAt(i) == ' ') {
				//System.out.print(" ");
				w = ' ';
			}
			encodedString = encodedString + w;
			sb.append(w);
			encodedString = sb.toString(); //sb를 String Type로 변수 일치 시킴
		}
		System.out.println("암호화할 문자열 : " + sourceString);
		System.out.println("암호화된 문자열 : " + encodedString);
		System.out.println("암호화된 문자열 : " + sb);
	}
}
