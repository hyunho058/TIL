package task;

public class Prob04_khh {

	public static void main(String[] args) {

		System.out.println(Prob04_khh.leftPad("SDS", 6, '#'));
		System.out.println(Prob04_khh.leftPad("SDS", 5, '$'));
		System.out.println(Prob04_khh.leftPad("SDS", 2, '&'));
		System.out.println(Prob04_khh.leftPad("SDS", 80, '&'));
	}

	public static String leftPad(String str, int size, char padChar) {

//		String result = " ";
//		
//		if(str.length() < size) {
//			String result1 = "";
//			for(int i = 0; i<=size-4; i++) {
//				result1 += padChar;
//			}
//			result = result1 +str;
//		}
//		else {
//			result = str;
//		}
//		
//	return result;

		StringBuilder result = new StringBuilder();
		// String result = " ";

		int count = size - str.length();

		for (int i = 0; i < count; i++) {
			result.append(padChar);
		}
		result.append(str);
		return result.toString();
	}


	public static String leftPad1(String str, int size, char padChar) {

		String result = " ";

		if (str.length() < size) {
			String result1 = "";
			for (int i = 0; i <= size - 4; i++) {
				result1 += padChar;
			}
			result = result1 + str;
		} else {
			result = str;
		}

		return result;

	}

}

//문제1. Prob5내에 leftPad 메쏘드를 작성하십시오. 
//       leftPad 메쏘드의 속성은 다음과 같습니다.
//
//1.	메소드 시그니처 : 
//        public static String leftPad(String str, int size, char padChar)
//
//2.	입력 문자열(str)의 길이가 입력받은 수(size)가 될 때까지 
//        문자열의 좌측을 지정된 문자(padChar)로 채운 후 리턴한다.
//
//3.	단, 입력 문자열(str)의 길이가 입력받은 수(size) 보다 
//             큰 경우에는 원본 문자열(str)을 그대로 리턴한다.
//
//실행 예) 
//leftPad(“SDS”, 6, ‘#’)    ###SDS 
//leftPad(“SDS”, 5, ‘$’)    $$SDS
//leftPad(“SDS”, 2, ‘&’)    SDS