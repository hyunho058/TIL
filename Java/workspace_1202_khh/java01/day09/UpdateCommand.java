package day09;

public class UpdateCommand implements Command{

	@Override
	public void exec() {
		System.out.println("UpdateCommand 수행");
	}
	
	public void base() {
		System.out.println("Command base() 기능");
	}
	
}
