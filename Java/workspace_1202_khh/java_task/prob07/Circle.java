package prob07;

public class Circle extends Shape{
	double radius = Math.PI;
	
	public Circle() {
		super();
	}
	public Circle(String name, double r) {
		super();
		super.setName(name);
		super.setArea(r);
	}

	@Override
	public void calculationArea() {
		System.out.print("원의 면적은 - ");
	}
	@Override
	public void print() {
		super.print();
		System.out.printf("%f %n", (super.getArea()*getArea()*radius));
	}


	
}
