package day06;

public class Test02 {

	public static void main(String[] args) {
		Time t1 = new Time();
//		t1.hour = 16;
//		t1.minute = 44;
//		t1.second = 21;
		t1.setHour(23);
		t1.setNinute(58);
		t1.setSecond(18);
		
		t1.print();
	}
}

class Time {
	private int hour = 0;
	private int minute = 0;
	private int second = 0;
	
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		if(hour >=0 && hour < 24)
		this.hour = hour;
	}
	
	public int getMinute() {
		return minute;
	}
	public void setNinute(int minute) {
		if(minute >=0 && minute < 60)
			this.minute = minute;
	}
	
	public int getSecond() {
		return second;
	}
	public void setSecond(int second) {
		if(second >=0 && second < 60)
			this.second = second;
	}

	public void print() {
		System.out.printf("%d시  %d분  %d초 %n", hour, minute, second);
	}
}
