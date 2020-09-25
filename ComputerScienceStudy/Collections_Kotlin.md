# Collections_Kotlin

> * 코틀린의 Collection은 기본적으로 Mutable(변할 수 없는)과 Immutable(불변의)을 별개로 지원한다
> * Mutable
>   * 추가, 삭제 가능
> * Immutable
>   * 수정 X



* Kotlin Collection

![image-20200924233649561](Collections_Kotlin.assets/image-20200924233649561.png)

* Java Collection

<img src="Collections_Kotlin.assets/image-20200924233419898.png" alt="image-20200924233419898" style="zoom: 80%;" />



## List

* Immutable (수정할 수 없는 객체, 불변의) => GET만 가능
  * ListOf<Type>(item)

```kotlin
val fruits= listOf<String>("apple", "banana", "kiwi", "peach")
// val fruits= listOf("apple", "banana", "kiwi", "peach") -> 타입 생략 가능

Log.i("fruits size = "+fruits.size)
Log.i("fruits.get(2) = "+fruits.get(2))
Log.i("fruits[3] = "+fruits[3])
Log.i("fruits.indexOf("peach") = "+fruits.indexOf("peach"))
```

​	![image-20200925232818518](Collections_Kotlin.assets/image-20200925232818518.png)



* Mutable (수정가능한 객체, 변할 수 있는)
  * mutableListOf => 추가 및 삭제 가능

