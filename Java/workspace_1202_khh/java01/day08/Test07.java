package day08;

public class Test07 {

	public static void main(String[] args) {
		Circle_a a = new Circle_a();
		System.out.printf("%.2f %n",a.cArea(5));
		a.print();
		a.color= "green";
		System.out.println(a.color);
		
		Circle_i i = new Circle_i();
		System.out.printf("%.2f %n",i.cArea(5));
		System.out.println(i.color);
	}

}
abstract class Shape_a{
	String color;
	public abstract double cArea(double r);
	public void print() {
	}
}

class Circle_a extends Shape_a{
	@Override
	public double cArea(double r){
		return Math.PI*r*r;
	}
}

//interface는 heap 영역에 올라가지 않는다.
interface Shape_i{
	final static String color = "red";  //Interface 에서는 상수화된 변수만 사용 가능하다.
	double cArea(double r);  //public과 abstract 가 생략 되어있다.
}

class Circle_i extends Object implements Shape_i{ //상속조거닝 없을시 OBject가 항상있다.
	@Override
	public double cArea(double r) {
		return 0;
	}
}

