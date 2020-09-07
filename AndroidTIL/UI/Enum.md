# Enum

> * 데이터 열거 타입(Enumeration Type)
>   * 한정된 값만을 가지는 데이터 타입

* 열거터입 장점
  * 문자열과 비교해 IDE의 지원을 받을 수 있다 (자동완성, 오타 검증, 텍스트 리팩토링)
  * 허용 가능한 값들을 제한
  * 리펙토링 시 변경 범위가 최소화 된다
    * 내용 추가가 필요해도  Enum 코드 외에 수정할 필요가 없다

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

  

