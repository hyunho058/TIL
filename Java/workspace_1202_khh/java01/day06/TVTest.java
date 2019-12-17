package day06;

import java.util.ArrayList;

public class TVTest {

	public static void main(String[] args) {
		TV tv1 = new TV();
		tv1.size = 19;
		tv1.model = "SAMSUNG";
		tv1.setChannel(7);
		tv1.powerOn();
		tv1.channelDown();
		tv1.print();
		
		TV tv2 = new TV();
		tv2.size = 19;
		tv2.model = "SAMSUNG";
		tv2.setChannel(5);
		tv2.powerOff();
		tv2.print();
		
//		tv2 = tv1;       //tv2 는 가비지의 대상이 된다.
//		tv2.print();
//		tv2 = null;
//		tv1 = null;
		
		ArrayList<Integer> tvList =new ArrayList<>(); 
		new TV().print(); // 템프러리 객체 주소를 지정하지 않아 바로 가비지의 대상이 된다.
		
		System.out.println("========================");
		
		TV[] tvs = new TV[3];
		tvs[0] = tv1;
		tvs[1] = tv1;
		
		for(int i = 0; i<tvs.length; i++) {
			
			if(tvs[i] != null) {
				tvs[i].powerOn();
				tvs[i].print();
			}
			if(tvs[i] == null) {
				break;
			}
		}
	}

}

class TV{
	int size = 0;
	String model = " ";
	int channel = 0;
	boolean power = false;
	
	public void print() {
		System.out.printf("TV %s/ %d/ %b/ %d/ %n",model, size, power, channel );
	}
	public void powerOn() {
		power = true;
	}
	public void powerOff() {
		power = false;
	}
	public void channelUp() {
		channel++;
	}
	public void channelDown() {
		channel--;
	}
	public void setChannel(int c) {
		channel = c;
	}
}
