package day07;

public class Test05 {

	public static void main(String[] args) {
		Dog[] d;
		Fish[] f;
		Animal[] a = {
				new Dog("진돗", "맥스"),
				new Fish("니모"),
				new Fish("가모"),
				new Dog("믹스", "멍멍이")
		};
		
		for(Animal data:a) {
			if(data instanceof Dog)
				((Dog)data).print();
			if(data instanceof Fish)
				((Fish)data).print();
			data.breath();
		}
	}

}
