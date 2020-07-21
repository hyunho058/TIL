# Coroutine

* Basic Code

```kotlin
import kotlinx.coroutines.* 
fun main() { 
    GlobalScope.launch {
        delay(1000L)
        println("World!")
    } 
    println("Hello,")
    Thread.sleep(2000L)
}
```



## Reference

[Android Developer](https://developer.android.com/kotlin/coroutines?hl=ko)

[https://tourspace.tistory.com/150](https://tourspace.tistory.com/150)