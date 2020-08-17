# JAVA

* JVM을 이용한 플랫폼이여서 독립적이다
* 자바에서 코드를 컴파일하면 바이트코드(.class)형태로 출력된다
* 바이트코드는 JVM에 런타임에 완벽한 기계코드로 변경되어 실행된다.
* 하나의 바이트코드를 가지고 서로다른 기계마다 해당  JVM만 설치되어있으면 다시 컴파일 할 필요 없이 나머지는 JVM에서 해당 기계에 맞도록 실행해준다
* 다양한 기본 클래스와 멀티쓰레드, 네트워킹 API를 지원한다



## DataType

* 기본 자료형
  * byte
  * short
  * int
  * long
  * float
  * double
  * char
  * boolean
* 참조 자료형
  * class
  * interface
  * arrray
  * enum



## Object Oriented Programming

![image-20200528235754561](JAVA.assets/image-20200528235754561.png) 

* 이미 작성한 코드에 대한 재사용성이 높다
* 설계 원칙
  1. SRP(Single Responsibility Principle) - 단일 책임
     * 클래스는 단 하나의 책임을 가져야 하며 클래스를 변경하는 이유는 단하나의 이유여야 한다
  2. OCP(Opne - Closed Principle) - 계방 - 페쇄
     * 확장에는 열려 있어야 하고 변경에는 닫혀 있어야 한다
  3. LSP(Liskov Substitution Principle) - 리스코프 치환
     * 상위 타입의 객체를 하위 타입의 객체로 치환해도 상위 타입을 사용하는 프로그램은 정상적으로 동작해야 한다
  4. ISP(Interface Segregation Principle) - 인터페이스 분리
     * 인터페이스는 그 인터페이스를 사용하는 클라이언트를 기준으로 분리해야 한다
  5. DIP(Dependency Inversion Principle) - 의존 역전
     * 고수준 모듈은 저수준 모듈의 구현에 의존해서는 안된다
*  특징
  * 자료 추상화(캡슐화)
    *  불필요한 정보는 숨기고 중요한 정보만을 표현함으로써 프로그램을 간단히 만드는 것
    * 추상 자료형은 자료형의 자료 표현과 자료형의 연산을캡슐화한 것으로 접근 제어를 통해서 자료형의 정보를 은닉할 수 있다.
    * 클래스 - 추상 자료형
    * 인스턴스 - 객체
    * 메소드 - 추상자료형에서 정의된 연산
    * 생성자 - 메소드의 호출
  * 상속
    * 새로운 클래스가 기존의 클래스의 자료와 연산을 이용할 수 있게 하는 기능
    * 상속을 통해서 기존의 클래스를 상속받은 하우 ㅣ클래스를 이용해 프로그램의 요구에 맞추어 클래스를 수정할 수 있고 클래스 간의 종속 관계를 형성함으로써 객체를 조직화할 수 있다.
  * 다중 상속
    * 클래스가 2개 이상의 클래스로부터 상속받을 수 있게 하는 기능.
    * 클래스들의 기능이 동시에 필요할 때 용이하나 클래스의 상속 관계에 혼란을 줄 수 있다
    * JAVA는 지원하지 않는다
  * 다형성
    * 하나의 메소드나 클래스가 다양한 방법으로 동작하게하는 요소
    * 오버라이딩(overriding)
      * 같은 이름의 메소드가 여러 클레스에서 다른 기능을 하는것
    * 오버로딩
      * 같은 이름의 메소드가 인자의 개수나 자료형에 따라서 다른 기능을 하는것

## 접근제어자(access modifier)

* public
  * 접근제한을 하지 않음
* default
  * 같은 패키지 내에서만 접근 가능함
* protected
  * 같은 패키지 내 혹은 다른 패키지의 자식 클래스에서 접근 가능
* private
  * 같은 클래스 내에서만 접근이 가능

## Java 메모리 구조

![img](JAVA.assets/자바동작과정.PNG)

1. Excution Engine

   * Runtime Data Area에 배치된 바이트 코드를 명령어 단위로 실행한다.

2. Class Loader

   * Runtime 시 바이트코드를 로딩한다.

3.  Garbage Collector

   * heap을 사용 가능한 만큼 자유롭게 사용하고, 더 이상 사용되지 않는 object들은 GC 프로세스가 메모리에서 제거한다.
   *  Heap영역의 object 중 stack에서 도달불가능한(unreachable) object는 garbage collection의 대상이 된다.

4. Method Area(Static Area = Class Area = Code Area)

   * java compiler가 일차적으로 컴파일한 바이트코드를 class Loader를 통해 읽어 저장하는 영역
   * 클래스/인터페이스에 대한 상수 pool,  맴버변수, static 변수, 생성자와 메소드를 저장

5. Stack Area

   * 기본 타입(primitive type)변수가 저장되는 영역
   * Heap Area에 생성된 객체의 주소가 참조 타입 변수에 할당된다
   * 각 Thread는 자신만의 Static Area를 가진다
   * CPU에서 LIFO 형식으로 관리된다
   * 함수가 종료되면 모든 변수가 스텍에서 POP-OUT된다

6. Heep Area

   * JVM이 관리하는 프로그램에서 데이터를 저장하기 위하여 동적으로 할당하여 사용하는 메모리 영역
   * 모든 Object type은 Heep에 저장되며, 하나의 heep만 존재한다
   * Garbage Collector를 통해 메모리가 반환

   ![img](JAVA.assets/힙메모리구조.PNG)

   *  Permanent : 생성된 객체들의 주소 값이 저장된 공간
   * New Area
     * Eden : 객체들이 최초로 생성되는 공간
     * Survivor : Eden에서 참조되는 객체들이 저장되는 공간
   * Old Area : New Area에서 일정시간이상 참조되고 있는 객체들이 저장되는 공간

## 추상 클래스와 인터페이스

* 추상클래스(abstract class)
  * abstract 키워드를 이용하여 미완성 메소드 즉, 형태만 정의해 놓고 몸체는 없는 상태를 포함하고 있는 클래스를 의미
  * 상속에 큰 의미를 두고 있다
  * 클래스이기 때문에 extends 를 이용하여 상속을 진행하며 이런 추상 클래스를 상속받은 자식 클래스에서는 **반드시 미완성 메소드를 재정의 해야한다**
* 인터페이스(interface)
  * 추상클래스보다 추상화 정도가 높은 상태를 정의할때 사용
  * 기능의 재정이의 큰 의미를 두고 있다
  * 맴버 변수와 일반 메소드를 가질 수 없으며 오직 상수와 추상 메소드만을 선언할 수 있다
  * implements를 이용하여 상속을 진행하며 **자식 클래스에셔 반드시 메소드를 재정의 해야한다**
  * **래스에서 지원하지 않는 다중상속이 가능하다**

## final 과 static

* final
  * final 키워들 사용하면 해당 변수를 상수화 할 수 있다
  * 초기화만 가능하며 이후 **새로운 값으로 변경이나 재할당이 불가능**하다
  * class에 final을 사용하면 해당 **클래스를 상속할 수 없다**
    * abstract와 기능적인면에서 충돌이 있기 때문에 함꼐 사용할 수 없음
  * 메소드에 final을 사용하면 해당 메소드를 **오버라이딩 할 수 없다**
* static
  * 클래스 내부의 메소드나 맴버 변수에 사용하면 하나의 인스턴스에 속하지 않고 해당 클래스로부터 생성되 ㄴ모든 인스턴스가 공통으로 공유하는 메소드와변수로 변경된다

## Serialization

* 객체에 저장되어있는 데이터를 스트림(파일로 저장& 네트워크를 이요하여 전송)에 바로 쓰기 위해 연속적인 데이터로 변환하는 것을 말한다
* Seriallizable 인터페이스를 상속 받으면 직렬화가 가능한 클래스로 변경할 수 있다
* 직렬화를 시키고자하는 클래스에 직렬화가 안되는 객체가 포함되어있을 경우 **transient를 이용해 해당 객체를 직렬화 대상에서 제외시킬수 있다**



## [Thread](https://github.com/hyunho058/TIL/blob/master/Network/Network_Program.md)

* 독림적 실행 흐름
* 구현 방법
  1. Thread class를 직접 상속해 사용
     * 상속 개념을 사용해서 객체사용에 제한
  2. Runnable interface구현한 객체를 이용해 Thread 생성
     * run() 메소드를 재정의하여 구현