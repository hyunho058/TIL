package day08;

public class Test06 {

	public static void main(String[] args) {
		//Animal a = new Animal;  //type 선언 (Animal이 추상클래스라 불가)
		Animal[] a = new Animal[10];  //array 선언
	}

}

class Product{
	String number;
	int price;
}

class TV extends Product{
	int a;
}

class Computer extends Product{
	
}

class Buter{
	void buy(Product p) {
		
	}
}