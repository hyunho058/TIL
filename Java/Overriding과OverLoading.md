# Overriding과 Overloading

## 오버라이딩(Overriding)

* 상위 클래스 혹은 인터페이스에 존재하는 메소드를 하위 클래스에서 필요에 맞게 재정의하는 것을 의미.
* Java의 경우는 오버라이딩 시 동적바인딩된다.
* 상위 클래스의 참조변수는 하위 클래스의 인스턴스를 참조할 수 있다.
* 오버라이딩된 상위 클래스의 메소드는 오버라이딩을 한 하위 클래스의 메소드에 의해 가려진다. 즉, 외부에서는 참조변수를 통해서 오버라이딩된 상위 클래스의 메소드를 호출할 수 없다. (내부에서는 super키워드로 호출가능)

```java
public class Test {
    public static void main(String[] args) {
        CarA carA = new CarA("Red", 10000);
        System.out.println(carA.toString());
    }
}

public class Car {
    private String color;
    private int price;

    public Car(String color, int price) {
        this.color = color;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" + "color='" + color + '\'' + ", price=" + price + '}';
    }
}

public class CarA  extends Car{

    public CarA(String color, int price) {
        super(color, price);
    }

    @Override
    public String toString() {
        return super.toString()+" //CarA//";
    }
}
```

```bash
결과 : Car{color='Red', price=10000} //CarA//
```



## 오버로딩(Overloading)

* 메소드의 이름과 return 타입은 동일하지만, 매개변수만 다른 메소드를 만드는것
* 자바의경우 오버로딩은 다른 시그니쳐를 만드는 것으로, ㅏ예 다른함수를 만드는것
  * 정적바인딩으로 처리 가능
* 메소드 오버로딩

```java
public String showState(String str) {
    return str;
}
public String showState(String str, String test1) {
    return str;
}
```

