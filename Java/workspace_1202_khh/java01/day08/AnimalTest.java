package day08;

public class AnimalTest {

	public static void main(String[] args) {
		Animal[] animal = {
			new Dog("풍산","멍멍이"),
			new Fish("니모")
		};
		
		
		for(Animal data:animal) {
			data.print();
			data.breath();
		}
	}

}
