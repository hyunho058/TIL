package prob07;

public class Rectangular extends Shape {
	double width;
	double hight;
	public Rectangular() {
		super();
	}
	public Rectangular(String name, double width, double hight) {
		super();
		super.setName(name);
		this.width = width;
		this.hight = hight;
	}
	@Override
	public void calculationArea() {
		System.out.print("직사각형 면적 -");
	}
	@Override
	public void print() {
		super.print();
		System.out.printf("%.1f %n",(width*hight));
	}
	
	
	
}
