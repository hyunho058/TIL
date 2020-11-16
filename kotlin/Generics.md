# Generics

> * Java
>   * 제네릭 정의를 하지 않아도 기본 Object로 만들어준다
> * Kotlin
>   * java에서 사용하는 Generics와 동일하게 사용할 수 있다.
>   * Generics정의한 클래스를 상속받을 때 명시적으로 지정히애 한다



## IN/OUT

* T

  * 별도의 Wildcard정의가 없이 read/write모두 가능

* in T

  * Java의 ? super T와 같다.
  * input의 약자이며 write만 가능

  ```kotlin
  private fun addItem(items: ArrayList<in Output<String>>) {
       items.add(object : Output<String> {
          override fun isArgument(argument: String) = false
       })
  
      // items.indices
      //        .filter { items[it].isArgument("") }
      //        .forEach { println("item : " + items[it]) }
      // items.add(null)
  }
  ```

* out T

  * Java의 ? extends T와 같다.
  * output의 약자이며 read만 가능

  ```kotlin
  private fun printAll(items: ArrayList<out Output<String>>) {
      // Error...
      // items.add(object : Output<String> {
      //    override fun isArgument(argument: String) = false
      // })
  
      items.indices
              .filter { items[it].isArgument("") }
              .forEach { println("item : " + items[it]) }
      // items.add(null) - Error
  }
  ```

  * 위의 코드에서 주석을 제거하면 Err가 발생한다
    * 문법상 add자체가 불가능하기 때문에 null을 포함하든 하지 않든 오류 발생



* Basic Code

```kotlin
interface Output<T>{
    fun isArgument(argument: T): Boolean
}
```

```kotlin
class ExampleUnitTest {

    val items = ArrayList<Output<String>>()

    init {
        items.add(object : Output<String> {
            override fun isArgument(argument: String) = false
        })
        items.add(object : Output<String> {
            override fun isArgument(argument: String) = true
        })
    }
}
```





## Reference

[https://thdev.tech/kotlin/androiddev/2017/10/03/Kotlin-Generics/](https://thdev.tech/kotlin/androiddev/2017/10/03/Kotlin-Generics/)