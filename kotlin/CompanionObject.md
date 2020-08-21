# Companion Object

> * Java의 Static 키워드는 클래스 맴버임을 지정하기 위해 사용
>   * static이 붙은 변수와 메소드를 각각 클래스 변수, 클래스 메소드라 부른다
>   * static이 붙지 않은 클래스 내의 변수와 메소드는 각각 인스턴스 변수, 인스턴스 메소드라 한다\



## static, companion object 비교

* java static basic code

```java
public final class MyClass{
  static public final String TEST = "test";  //클래스 변수
  static public method(int i):int{     //클래스 메소드
    return i + 10
  }
}

System.out.println(MyClass.TEST);   //test
System.out.println(MyClass.method(1));  //11
```



* Kotlin companion object basic code
  * companion object 키워드와 함께 ({}) 안에 멤버를 구성한다

```kotlin
class MyClass{
    companion object{
        val TEST = "test"
        fun method(i:Int) = i + 10
    }
}
fun main(args: Array<String>){
    println(MyClass.TEST);    //test
    println(MyClass.method(1));   //11
}
```





## Companion object 사용법&특징

> 1. Companion object는 객체이다
> 2. Companion object에 이름을 지을 수 있다
> 3. 클래스 내 Companion object는 하나만 쓸 수 있다
> 4. Interface내에도 Companion object를 정의할 수 있다.



* Companion object는 객체이다
  * 주석 (1)
    * companion object는 객체이므로 변수에 할당할 수 있다
    * 할당된 변수에는 . 으로 Myclass2에 정의된 companion object의 맴버에 접흔할 수있다
    * 자바의 클래스에서 static 키워드로 저으이된 맴버로는 불가능한 방법이다.
  * 주석 (2)
    * Companion을 뺴고 직접 MyClass2로 할당한 것
    * **클래스 내 정의돈 companion object는 클래스 이름만으로도 참조 접근이 가능하다**

```kotlin
class MyClass2{
    companion object{
        val prop = "나는 Companion object의 속성이다."
        fun method() = "나는 Companion object의 메소드다."
    }
}
fun main(args: Array<String>) {
    println(MyClass2.Companion.prop)
    println(MyClass2.Companion.method())
 
    val comp1 = MyClass2.Companion  //--(1)
    println(comp1.prop)
    println(comp1.method())
 
    val comp2 = MyClass2  //--(2)
    println(comp2.prop)
    println(comp2.method())
}
```



* Companion object에 이름을 지을 수 있다
  * 기본 이름은 Companion이다

```kotlin
class MyClass3{
    companion object MyCompanion{  // -- (1)
        val prop = "나는 Companion object의 속성이다."
        fun method() = "나는 Companion object의 메소드다."
    }
}
fun main(args: Array<String>) {
    println(MyClass3.MyCompanion.prop) // -- (2)
    println(MyClass3.MyCompanion.method())
 
    val comp1 = MyClass3.MyCompanion // -- (3)
    println(comp1.prop)
    println(comp1.method())
 
    val comp2 = MyClass3 // -- (4)
    println(comp2.prop)
    println(comp2.method())
     
    val comp3 = MyClass3.Companion // -- (5) 에러발생!!!
    println(comp3.prop)
    println(comp3.method())
}
```



* 클래스 내 Companion object는 하나만 쓸 수 있다
  * 클래스 내에 2개 이상 companion object를 쓰는 것은 안도니다
  * 코틀린은 클래스 명만으로 companion object객체를 참조할 수 있기 때문에 한 번에 2개를 참조하는 것은 불가능



* Interface내에도 Companion object를 정의할 수 있다.
  * 인터페이스 수준에서 상수항을 정의할 수 있다
  * 관련된 중요 로직을 기술할 수 있다

```kotlin
interface MyInterface{
    companion object{
        val prop = "나는 인터페이스 내의 Companion object의 속성이다."
        fun method() = "나는 인터페이스 내의 Companion object의 메소드다."
    }
}
fun main(args: Array<String>) {
    println(MyInterface.prop)
    println(MyInterface.method())
 
    val comp1 = MyInterface.Companion
    println(comp1.prop)
    println(comp1.method())
 
    val comp2 = MyInterface
    println(comp2.prop)
    println(comp2.method())
}
```





## object

> * 코틀린에는 자바에 없는 독특한 **Singleton(인스턴스가 하나만 있는 클래스)**  선언 방법이 있다
> * class키워드 대신 object 키워드를 사용한다

```kotlin
object MySingleton{
    val prop = "나는 MySingleton의 속성이다."
    fun method() = "나는 MySingleton의 메소드다."
}
fun main(args: Array<String>){
    println(MySingleton.prop);    //나는 MySingleton의 속성이다.
    println(MySingleton.method());  //나는 MySingleton의 메소드다.
}
```

* object는 특정 클래스나 인터페이서를 확장(var obj = object.Myclass(){} 또는 var obj = object.MyInterface{}) 해 만들 수 있으며 선언문이 아닌 표현식(var obj = object{})으로 생성할 수있다.
  * var obj = object.Myclass(){}
  * var obj = object.MyInterface{}
  * var obj = object{}
* 싱글톤이기 때문에 시스템 전체에서 쓸 기능을 수행하는 도움이 된다
* 전역 상태를 유지하는 데 쓰면 스레드 경합 등으로 위험할 수 있으니 주의 해서 사용해야 한다





## Reference

[https://www.bsidesoft.com/category/programming/kotlin](https://www.bsidesoft.com/category/programming/kotlin)