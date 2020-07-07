# Kotlin

> * JVM, Android, Borowser를 위한 정적 타입의 프로그래밍 언어
> * Null안정성이 타입 시스템에 내장되어있다
>   * 언제 무엇이 null일 수 있을지 알 수 있으며 컴파일러가 이를 확인하도록 강제한다
> * 기반코드를 생성해주는 방식과 함수형 언어 기능을 가진다.
> * Java보다 간결하고 지나치게 간결하지 않아 가독성을 해치지 않는다.
> * **Java와 함께 사용할 수 있고, JVM이 실행할 수 있는 바이트 코드로 컴파일 된다**



## Null 안정성

* 변수에 **물음표**를 붙이면 nullable이 된다
  * 물음표가 없다면 null이 될수 없다.
  * nullable값을 부르기 전에 null을 확인하지 않으면 컴파일되지 않는다.

```kotlin
var maybeString: String? = "Hello"
maybeString.length
```

* length 전에 쓰인 물음표인 **안전호출 오버레이터(Safe Call Operator)** 사용
  * 객체가 null이 아닌 경우 length를 호출하고, 아니면 null을 반환 한다
  * val을 사용하면 불변 값에 문자열을 할당했으므로 컴파일러가 null이 될수 없는 값임을 알고 필요없는 코드를 대신 제거 해준다.

```kotlin
Kotlin
val maybeString: String? = “Hello"
maybeString?.length
```

```java
Java
String maybeString = "Hello";
maybeString.length();
```

* !!

```kotlin
val maybeString: String? = getString()
maybeString!!.length

//두개의 느낌표를 사용하면 컴파일러 오류를 피할 수 있다
//length를 호출하기 전에 두 개의 느낌표를 사용하면 확인 과정 없이 해당 변수에서 해당 메서드를 호출하게 된다
```

```java
String maybeString = this.getString();
if(maybeString == null) {
 Intrinsics.throwNpe();
}
maybeString.length();
```



## Delegation

* 상속을 대신할 수 있다
  * 상속대신 조합을 사용하면 상속을 사용할 때 벌어지는 긴밀한 결합을 피할 수있다.
  * 코드를 읽고 따라가면서 발생하는 인지를 위한 간접 비용과 시간낭비, 추척해야 할 가짓수를 줄일 수 있다
* CopyPrinter Example

```kotlin
Kotlin
class CopyPrinter(copier: Copy, printer: Print)
 : Copy by copier, Print by printer
interface Copy {
 fun copy(page: Page): Page
}
interface Print {
 fun print(page: Page)
}
```

```java
Java
public final class CopyPrinter implements Copy, Print {
   	// $FF: synthetic field
   	private final Copy $$delegate_0;
   	// $FF: synthetic field
   	private final Print $$delegate_1;
   	
   	public CopyPrinter(@NotNull Copy copier, @NotNull Print printer) {
	Intrinsics.checkParameterIsNotNull(copier, "copier");
	Intrinsics.checkParameterIsNotNull(printer, "printer");
	super();
	this.$$delegate_0 = copier;
	this.$$delegate_1 = printer;
	}
	
    @NotNull
    public Page copy(@NotNull Page page) {
		Intrinsics.checkParameterIsNotNull(page, "page");
        return this.$$delegate_0.copy(page);
     }
     public void print(@NotNull Page page) {
        Intrinsics.checkParameterIsNotNull(page, "page");
		this.$$delegate_1.print(page);
    }
}

public interface Copy {
@NotNull
Page copy(@NotNull Page var1);
}

public interface Print {
void print(@NotNull Page var1);
}
```



## 정적 도구 클래스(Static Utility Class)

* 보통 메서드들은 일관성이 있고 클래스에 특정된 것이지만 Application에 특정한 메서드를 적용할 수도 있다.
* kotlin은 익스텐션을 사용해서 이들을 부드럽게 처리한다.
  * 클래스 인스턴스로부터 이런 메서드에 접근할 수 있다.(String과 같은  final클래스라도 수정할 수 있다.)
* data time에 익스텐션 함수를 추가해서 시간에 경계를 쉽고 빠르게 설정할 수 있다.
* 클래스 수정 Example
  * String.double 처럼 클래스 이름 뒤에 점을 찍고 함수 이름을 추가해서 선언

```kotlin
// StringExt.kt
fun String.double(): String() {
	return this + this
}
```



## 함수형 언어 속성







## Reference

[<https://academy.realm.io/kr/posts/kotlin-does-java-droidcon-boston-2017-gonda/>](https://academy.realm.io/kr/posts/kotlin-does-java-droidcon-boston-2017-gonda/)