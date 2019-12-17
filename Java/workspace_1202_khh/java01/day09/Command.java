package day09;

public interface Command {
	void exec(); //추상매소드 pubic abstract가 생략 되어있음
	
	default public void base() {
		System.out.println("Command base() 기능");
	}//선택적 Override 할수 있게 해주는 기능 
}
