# Enum

> * 데이터 열거 타입(Enumeration Type)
>   * 한정된 값만을 가지는 데이터 타입

* 열거터입 장점
  * 문자열과 비교해 IDE의 지원을 받을 수 있다 (자동완성, 오타 검증, 텍스트 리팩토링)
  * 허용 가능한 값들을 제한
  * 리펙토링 시 변경 범위가 최소화 된다
    * 내용 추가가 필요해도  Enum 코드 외에 수정할 필요가 없다
* Java의 Enum 장점
    * C/C++ 의 경우 Enum이 결국 int값이다
    * Java의 Enum은 **완전한 기능을 갖춘 Class**이다
  
* 열거 타입 선언

  * 타입의 이름을 정하고 열거 타입 이름으로 소스파일을 생성해야한다.
  * 첫 문자를 대문자로 하고 나머지는 소문자로 구성
  * 두 단어 이상으로 구성됬다면 단어의 첫 문자를 대문자로 한다

  ```java
  Season.java
  Week.java
  ```

  * Season.java
    * public enum 키워드는 열거 터입을 선언하기 위한 키워드 이다.
    * 열거 상수는 열거 타입의 값으로 사용되는데, 관례적으로 모두 대문자로 작성한다

  ```java
  public enum Season { 
      SPRING, 
      SUMMER, 
      AUTUMN, 
      WINTER 
  }
  ```

* 열거 타입 변수

  * 열거 타입도 하나의 데이터 타입이므로 변수를 선언하고 사용해야한다.
  * 선언 방법

  ```java
  열거타입 변수; 
  Season season; 
  Season weddingSeason;
  ```

  * 열거 타입 변수를 선언했다면 다음과 같이 열거 상수를 저장할 수 있다.

  ```java
  열거타입 변수 = 열거타입.열거상수; 
  Season weddingSeason = Season.AUTUMN;
  ```

  * 열거 타입 변수는 다음과 같이 null을 저장할 수 있는데, 열거 타입도 참조 타입이기 떄문이다

  ```java
  Season season = null;
  ```

* 열거 객체의 메소드

  * name() - 열거 객체의 문자열 리턴

  ```java
  Season season = Season.SPRING; 
  String name = season.name(); // "SPRING"
  ```

  * ordinal() - 열거 객체가 몇 번째인지 리턴

  ```java
  public enum Season { 
      SPRING, // 0 
      SUMMER, // 1 
      AUTUMN, // 2 
      WINTER // 3 
  } 
  Season season = Season.AUTUMN; 
  int ordinal = season.ordinal(); // 2
  ```

  * compareTo() - 매개값으로 주어진 열거 객체를 기준으로 전 후로 몇 번째 위치하는지 비교

  ```java
  Season season1 = Season.SPRING; // 0 
  Season season2 = Season.WINTER; // 3
  int result1 = season1.compareTo(season2); // -3 
  int result2 = season2.compareTo(season1); // 3
  ```

  * valueOf() - 매개값으로 주어지는 문자여로가 동일한 문자열을 가지는 열거 객체를 리턴, 외부로부터 문자열을 받아 열거 객체로 변환할때 유용

  ```java
  Season season = Season.valueOf("SPRING")
  ```

  * values() - 열거 타입의 모든 열거 객체들을 배열로 만들어 리턴

  ```java
  Season[] seasons = Season.values(); 
  for(Season season : seasons) { 
      System.out.println(season); 
  }
  ```

* 열거 상수를 다른 값과 연결하기

  * 열거 상수 각각이 열거 객체이므로 열거 객체에 생성자를 사용해서 다음과 같이 열거 상수에 다른 값을 할당할 수 있다

  ```java
  public enum Type { // 상수("연결할 문자") 
      WALKING("워킹화"), 
      RUNNING("러닝화"), 
      TRACKING("트래킹화"), 
      HIKING("등산화"); 
      
      final private String name; 
      private Type(Stirng name) { // enum에서 생성자 같은 역할 
          this.name = name; 
      }
      
      public String getName() { // 문자를 받아오는 함수 
          return name; 
      } 
      
  } 
  
  public class Shoes { 
      public static void main(String[] args) { 
          for(Type type : Type.values()){ 
              System.out.println(type.getName()); 
          } 
      } 
  }
  ```

  

## Reference

[https://itmining.tistory.com/149](https://itmining.tistory.com/149)

[https://woowabros.github.io/tools/2017/07/10/java-enum-uses.html](https://woowabros.github.io/tools/2017/07/10/java-enum-uses.html)