package day08;

public class Test08 {

	public static void main(String[] args) {
		
		Drawable[] s = {
				new Circle(),
				new Rectangle()
		};
		for(Drawable data:s) {
			data.draw();
			((Moveable)data).move();
		}
		System.out.println("=====================");
		////////////인터페이서 데이터 통합을하면 캐스팅을 안해도 사용할수있다(데이터를 통합으로 관리할수있다)
		T[] t = {
				new Circle(),
				new Rectangle()
		};
		for(T data:t) {
			data.draw();
			data.move();
		}
		
	}
}

//이름 끝에 able로 끝나면 interface 이다
interface Drawable{
	void draw();
}
interface Moveable{
	void move();
}

interface T extends Drawable, Moveable{}


class Circle implements T{
	String name = "Circle";
	@Override
	public void move() {
		System.out.println(name+" 이동하기(move)");
	}
	@Override
	public void draw() {
		System.out.println(name+" 그리기(draw)");
	}
}

class Rectangle implements T{
	String name = "Rectangle";
	@Override
	public void move() {
		System.out.println(name+" 이동하기(move)");
	}
	@Override
	public void draw() {
		System.out.println(name+" 그리기(draw)");
	}
}