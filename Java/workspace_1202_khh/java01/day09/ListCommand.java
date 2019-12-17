package day09;

public class ListCommand implements Command{
	@Override
	public void exec() {
		System.out.println("ListCommand 수행");
	}
}
