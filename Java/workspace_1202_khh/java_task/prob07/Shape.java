package prob07;

abstract class Shape {
	double area = 0.0;
	String name = "";
	
	public Shape() {
		super();
	}

	public Shape(String name) {
		super();
		this.name = name;
	}
	
	
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public abstract void calculationArea ();
	public void print() {
	}

}
