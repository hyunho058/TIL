package day08;

import day07.Animal;

public class Test01 {

	public static void main(String[] args) {
		Animal a = new Animal();
		System.out.println();
	}

}

class Dog2 extends Animal{
	
	public void print() {
		System.out.println(super.kind);
	}
}