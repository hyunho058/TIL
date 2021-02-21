# Extension

> * Kotlin
> * IOS

* 확장이라는 기능을 통해 간단하게 객체의 함수나 프로퍼티를 임의로 확장 정의하여 사용할 수 있다.
* 잠점
  * 유틸리티 클래스 등을 별도로 저장하지 않고, 직접적인 객체 확장의 방법을 제공
  * 함수와 프로퍼티 양측에 대한 확장을 지원한다.
  * Generic을 통해 객체의 타입을 처리 할수 있다.
  * Extension이 적용될 범위를 지정할 수 있다.
* 예시
  * String 타입에 extension() 라는 이름의 확장 함수를 구현하고자 한다면 다음과 같이 작성한다.
  * String의 함수인 것처럼 extension()을 정의하여 사용할 수 있다.
  * 별도의 인터페이스를 선언할 필요도 유틸리티나 상속 역시 필요없는 아주 간단한 방식

```kotlin
fun String.extension(): String{
    return "Extension, $this"
}

fun main(args: Array<String>){
    val name = "Kotlin"
    print(name.extension())
}

//Result//
Extension, Kotlin
```





## Extension 규칙

* Extensions은 정적으로 처리된다.

  * Extension은 실제로 클래스를 상속/수정하지 않는다. 클래스에 새 머ㅐㅁ버를 삽입하지 않고 단순히 해당 타입의 변수에 . 을 기반으로 호출 가능한 함수를 생성한다.
  * Extension이 리시버의 타입에 의한 가상 함수가 아니라 **정적** 으로 처리된다. 이는 호출되는 확잠 함수는 표현식의 타입에 따라 결정 된다는 것을 의미.

  ```kotlin
  open class C
  class D : C()
  
  fun C.foo() = "c"
  fun D.foo() = "d"
  fun printFoo(c: C) {
      println(c.foo())
  }
  
  class Demo {
      fun run() {
          printFoo(D())
      }
  }
  ```

* Extension보다 맴버가 우선이다.

  * 이미 클래스에 동일한 시그니처를 가지는 맴버가 있을 때에도 Extension을 정의할 수 있다.
    * 맴버가 Extension에 대해 항상 우선권을 가지기 때문

  ````kotlin
  class Person {
      fun hello() { println("hello!") }
  }
  fun Person.hello() { println("HELLLLLLOOOOOOOOO!!!!") }
  
  fun main(args: Array<String>) {
      Person().hello()
  }
  
  //Result//
  hello
  ````

* Extension은 범위를 가진다.

  * Extension의 선택적인impolt가 가능하다

  ```kotlin
  class D {
      fun bar() { println("D.bar()") }
  }
  class C {
      fun baz() { println("C.bar()") }
      fun D.foo() {
          bar()   // calls D.bar
          baz()   // calls C.baz
      }
      fun caller(d: D) {
          d.foo()   // call the extension function
      }
  }
  ```

  * 만약  범위로 인하여 this의 대상이 혼동될 경우는 @을 통해 이를 처리 할 수 있다.

  ```kotlin
  class D {
      fun bar() { println("D.bar()") }
  }
  class C {
      fun D.foo() {
          toString()         // calls D.toString()
          this@C.toString()  // calls C.toString()
      }
  }
  ```

  





```java




   public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        ArrayList<Integer> food_time = new ArrayList<>();
        int currentPosition = 0;

        for (int i = 0; i < N; i++) {
            food_time.add(scanner.nextInt());
        }

        int K = scanner.nextInt();


        while (K > 0){

            for (int i = 0; i<food_time.size(); i++){
                food_time.set(i, food_time.get(0) -1);
                K -= 1;
                currentPosition = i;
            }

        }
        System.out.println("RRESULT==="+(currentPosition+1));
    }
```

