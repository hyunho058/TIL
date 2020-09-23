# JSON

* 서버에서 클라이언트에게 값을 전달할때 주로 사용
* 특정 key 값과 Value 값을 가진 형태로 되어있다.
* Json은 데이터의 교환 방식으로 Restful의 한 종류라고 이야기 할 수있으며 데이터 교환방식을 통일하여 상호간 값을 알아보기 쉽게 정의되어 편리한 작업, 소스코드를 통일할 수 있디.

* JsonObject => { {}, {}, [{}, {}, {}] }
* JsonArray => [{}, {}, {}]
* JsonElement => {} 
  *  JsonObject 와 같은 것이 되어버림. 왜냐면 JsonObject 는 모든 데이터 형식을 가질 수 있기 때문에 {} 하나만으로 이루어진 데이터도 JsonObject가 될 수 있다.
  * JsonElement.getAsJsonObject().get("key value").getAsString();

```
Object(중괄호 { }로 표기)
Array(대괄호 [ ]로 표기)
```

## Gradle

```java
dependencies {
	implementation 'com.google.code.gson:gson:2.8.5'
}
```



## 참고자료

[AndroidDeveloper](https://developer.android.com/reference/org/json/JSONObject)

[JSONObject](https://shlee0882.tistory.com/45)