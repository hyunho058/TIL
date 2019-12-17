package day07;

public class Test04 {

	public static void main(String[] args) {
		Animal f = new Fish("니모");
		((Fish)f).print();
		
		System.out.println(f.kind);
		System.out.println(((Fish)f).kind);
		
		f.breath();
		((Fish)f).breath();
	}

}
