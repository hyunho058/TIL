# Generic

> 데이터 형식에 의존하지 않고, 하나의 값이 여러 다른 데이터 타입들을 가질 수 있도록 하는 방법
>
> 제네릭은 클래스 내부에서 지정하는 것이 아닌 외부에서 사용자에 의해 지정되는 것을 의미

* 장점
  * 제네릭을 사요하면 잘못된 타입이 들어올 수 있는 것을 컴파일 단계에서 방지할 수 있다.
  * 클래스 외부에서 타입을 지정해주기 때문에 따로 타입을 체크하고 변환해줄 필요가 없다
  * 비슷한 기능을 지원하는 경우 코드의 재사용성이 높아진다

* Generic사용법
|타입|설명|
|------|---|
|<T>|Type|
|<E>|Element|
|<K>|Key|
|<V>|Value|
|<B>|Number|

* 클래스 및 인터페이스 선언

```java
public class GenericClass <T> { ... }
public interface GenericInterface <T>{}

public class GenericClass <T, V> { ... }
public interface GenericInterface <T, V>{}


public class Main {
    public static void main(String[] args) {
        GenericClass<String, Integer> genericClass = new GenericClass<String, Integer>();
    }
}
```

```java
public class GenericClass<E>{
    private E element;// 제네릭 타입 변수

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }
}

public class Main {
    public static void main(String[] args) {
        GenericClass<String> genericClassA = new GenericClass<String>();
        GenericClass<Integer> genericClassB = new GenericClass<Integer>();

        genericClassA.setElement("10");
        genericClassB.setElement(5);

        System.out.println("genericClassA = " + genericClassA.getElement());
        //반환된 변수의 타입 출력
        System.out.println("genericClassA Type = " + genericClassA.getElement().getClass().getName());

        System.out.println("genericClassB = " + genericClassB.getElement());
        //반환된 변수의 타입 출력
        System.out.println("genericClassB Type = " + genericClassB.getElement().getClass().getName());
    }
}
```

> 결과
>
> genericClassA = 10
> genericClassA Type = java.lang.String
> genericClassB = 5
> genericClassB Type = java.lang.Integer

* Generic Method
  *  클래스에서 지정한 Generic유형과 별도로 메소드에서 독립적으로 generic유형을 선언하여 쓸 수 있다.
  * 정적 메소드로 선언할때 필요

```java
public class GenericClass<E>{
    private E element;// 제네릭 타입 변수

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public <T> T genericMMethod(T t){
        return t;
    }
}

public class Main {
    public static void main(String[] args) {
        GenericClass<String> genericClassA = new GenericClass<String>();
        GenericClass<Integer> genericClassB = new GenericClass<Integer>();

        genericClassA.setElement("10");
        genericClassB.setElement(5);

        System.out.println("genericClassA = " + genericClassA.getElement());
        //반환된 변수의 타입 출력
        System.out.println("genericClassA Type = " + genericClassA.getElement().getClass().getName());

        System.out.println("genericClassB = " + genericClassB.getElement());
        //반환된 변수의 타입 출력
        System.out.println("genericClassB Type = " + genericClassB.getElement().getClass().getName());

        System.out.println("genericClassA generic method = " + genericClassA.genericMMethod(11).getClass().getName());
        System.out.println("genericClassA generic method = " + genericClassA.genericMMethod(genericClassB).getClass().getName());
    }
}
```

> 결과
>
> genericClassA = 10
> genericClassA Type = java.lang.String
> genericClassB = 5
> genericClassB Type = java.lang.Integer
> genericClassA generic method = java.lang.Integer
> genericClassA generic method = generic.GenericClass