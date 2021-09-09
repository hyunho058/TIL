# DI(Dependency Injection)

DI로 프로그램을 설계 했을 때, 다음과 같은 **이점**을 얻을 수 있다.

* Unit Test가 용이
* 코드의 재활용성을 높여준다
* 객체간의 의존성을 줄이거나 없엘수 있다
* 객체 간의 결합돌르 낯구면서 유연하 ㄴ코드르 ㄹ작성할 수 있다.

```java
public class Drive {
     private Car car = new Car();
     
     public Drive(){
         this.car = new Car();
     }
     
     public void drivingCar(){
         this.car.start();;
     }
}
```

위와 같이 `Drive` 클레스에서 `drivingCar` 함수가 호출되기 위해서는 `Car` 클래스를 필요로 한다. 이것을 Drive 클래스는 Car클래스의 의존성을 가진다라고 한다.

위와같이 설계하였을 때, 코드의 재활용성이 떨어지고, 위 `Car` 클래스가 수정되었을 때, `Drive` 클래스도 함께 수정해줘야하는 문제가 발생.(결함도가 높아진다.)

DI를 사용하지 않고 `Car` 클래스를 상속 받은 `CarA`, `CarB` 클래스를 사용해야 한다면 아래와같이 작업을 해야한다.

```java
public Drive(){
	this.car = new CarA();
  //또는
	this.car = new CarB();
}
```

의존성 주입(DI)을 이용하면 아래와 같이 할 수 있다

```java
public class Drive {
     private Car car;

     public Drive(Car car){
         this.car = car;
     }
     public void drivingCar(){
         this.car.start();;
     }
}
```

필요한 클래스를 직접 생성하는 것이 아닌, 생성자를 통해 주입해줌으로써 객체 간의 결합도를 줄이고 유연한 코드를 장성할 수있게 된다.