# Primitive Type과 Wrapper Class

> Java에 변수 타입에는 기본형(Privitive Type)과 참조형(Reference Type)이 있다.
>
> 기본형 - `short, int, long, float, double, byte, char, boolean`
>
> 참조형 - `Short, Integer, Long, Float, Double, Byte, Character, Boolean`

## 기본형과 참조형의 차이

* 기본형 변수는 값을 그대로 저장
  * 변수의 메모리 공간에 그대로 값을 할당
* 참조형 변수는 객체의 레퍼런스를 저장.
  * 객체가 할당 - 사이즈가 유동적
  * 바로 값을 할당할 수 없고 힙(Heep)영역에서 별도로 메모리공간을 차지하여 그곳에 할당한뒤 변수에는 해당 힙영역의 주소값을 가리키게 한다

```java
int primitive = 1; //기본형
int reference[] = new int[]{1,2};	//참조형
```

## Wrapper Class

* 래퍼 클래스는 기본형을 객체로 한번 감싼 class
  * 기본형을 객체로 다를 수 있게 해준다.
* Integer라는 래퍼클래스를 사용하면 List에 정수형값을 담을 수 있다. `List<Integer> a`
*  **기본형은 null값을 가질 수 없지만 래퍼 클래스 변수는 null값을 가질 수 있다**. `Integer a = null`

![스크린샷 2021-09-04 오후 3.34.37](PrimitiveType과WrapperClass.assets/스크린샷 2021-09-04 오후 3.34.37.png)

* primiteve data를 wrapper class인스턴스로 변환하는 작업을 `Boxing`
* wrapper class인스턴스를 primiteve data로 변환하는 작업을 `Unboxing`
* JDK 1.5버전 부터는 Java Compiler가 박싱과 언박싱을 자동으로 처리해주는 Auto Boxing, Auto Unboxing이 지원





## Reference

[http://tcpschool.com/java/java_api_wrapper](http://tcpschool.com/java/java_api_wrapper)

