# Coroutine

> * 비동기적으로 실행되는 코드를 간소화하기 위해 **Android에서 사용할 수 있는 동시 실행 설계 패턴 이다.
> * 기본 스레드를 차단하여 앱이 등답하지 않게 만들 수도 있는 장기 실행 작업을 관리하는 데 도움이 된다

## 기능

* 경량
  * 코루틴을 실행 중인 스레드를 차단하지 않는 정지를 지원하므로 단일 스레드에서 많은 코루틴을 실행할 수 있다. 정지는 많은 동시 작업을 지원하면서도 차단보다는 메모리를 절약한다
* 메모리 누수 감소
  * 구조화된 동시 실행을 사용하여 범위 내에서 작업을 실행
* 기본으로 제공되는 취소 지원
  * 실행 중인 코루틴 계층 구조를 통해 자동으로 취소가 전달된다
* Jetpack 통합
  * 많은 Jetpack 라이브러리에 코루틴을 완전히 지원하는 확장 프로그램이 포함되어 있다
  * 일부 라이브러리는 구조화된 동시 실행에 사용할수 있는 자체 코루틴범위도 제공한다

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