# Function

## 기본 함수(Basic Function)

* return을 사용하지 않은 경우
  * Unit 생략 가능하다

```kotlin
fun 함수명(변수) : Unit{
    
}
```

* return type을 가지는경우 

```kotlin
fun 함수명(변수) : 리턴타입{
    return 값
}

fun sum (a: int, b: int): int{
    return a+b
}

fun sum (a: int, b: int): int = a+b;    // 위의 함수를 간단하게 나타낼 수 있다.
```



## 멤버 변수(Member Function)

* 클래스 내에 정의된 함수

```kotlin
class Date(){
    fun getData(){
        println("20/07/20")
    }
}
```



