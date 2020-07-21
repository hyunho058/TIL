# RETROFIT2

* http 서버통신을 쉽게 사용할 수 있게 해주는 Android Library
* HTTP call 을 단순화

## 의존성

* Build.gradle

```java
implementation 'com.squareup.retrofit2:retrofit:2.4.0'
implementation 'com.squareup.retrofit2:converter-gson:2.4.0'
implementation 'com.squareup.retrofit2:converter-scalars:2.4.0'
```





# 참고사이트

[Rerofit](https://square.github.io/retrofit/)

[Retrofit2 Example](https://falinrush.tistory.com/5)

[kakaoAPI_MAP](https://youngest-programming.tistory.com/163)

[jsonschema2pojo](http://www.jsonschema2pojo.org/)

[REST API](https://medium.com/@joycehong0524/android-studio-retrofit2-%EA%B8%B0%EB%B3%B8-%EC%82%AC%EC%9A%A9%EB%B2%95-retrofit-%EC%9D%98%EB%AC%B8%EC%A0%90-%ED%92%80%EC%96%B4%ED%97%A4%EC%B9%98%EA%B8%B0-%EC%8A%A4%EC%95%95-f150db436add)





```kotlin
lateinit var retrofit : Retrofit
    lateinit var myAPI : IWakaServer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val TAG = MainActivity::class.java.simpleName
        Log.d(TAG,"on create!")
        setContentView(R.layout.activity_main)
        //retrofit setting
        retrofit = RetrofitClient.getInstnace() // 2에서 만든 Retrofit client의 instance를 불러옵니다.
        myAPI = retrofit.create(IWakaServer :: class.java) // 여기서 retrofit이 우리의 interface를 구현해주고
                                                      //우리는 이제 그것을 사용할 수 있습니다.
       //Runnable로 감싸주는 이유는!
       // Android 에서 MainThread 에서 네트워킹 관련 일을 못해서
       // 새로운 스레드에서 해주어야 합니다. 마지막에 .run() 잊지 마세요
       Runnable { myAPI.getCodingTime("2019-11-09","MY_API_KEY").enqueue(object : Callback<RawResponseData>{
           
           //이때 onFaliure는 Cal을 서버쪽으로 아예 보내지 못한 경우입니다.
           override fun onFailure(call: Call<RawResponseData>, t: Throwable) {
                Log.d(TAG,t.message) 
           }
           
        
          //만약 보낸 것이 성공했을 경우는 resonse를 가지고 들어옵니다.
          //그리고 call을 때릴 때 RawResponseData로 갔으니까 Reponse도 그 타입을 가지고 옵니다. 
           override fun onResponse(call: Call<RawResponseData>, response: Response<RawResponseData>) {
               Log.d(TAG,"response : ${response.body()!!.start}") // 정상출력이 되야 합니다. 
              
               //만약 정상 출력이 되지 않으면 문제가 있는 겁니다. 
               //이때는 Call은 제대로 보냈으나 서버에서 이거뭐냐? 하고 reponse를 보낸 경우 입니다. 
               Log.d(TAG,"response : ${response.errorBody()}")
               Log.d(TAG,"response : ${response.message()}")
               Log.d(TAG,"response : ${response.code()}") //이게 가장 에러를 알아보기 쉬운 곳 입니다. 
               Log.d(TAG,"response : ${response.raw().request().url().url()}") //무슨 url로 api call 을 보냈는지
                                                                             //확인 할 수 있습니다. 
           }
       })
       }.run() //잊지 마세요!
```

https://dapi.kakao.com/v3/search/book?target=title&query=JAVA

https://dapi.kakao.com/v3/search/book?target=title&query=java

https://dapi.kakao.com/v3/search/book?target=title&query=java&sort=recency&page=1&size=10

https://dapi.kakao.com/v3/search/book?query=java&sort=recency&page=1&size=10

https://dapi.kakao.com/v2/search/web?query=java&sort=recency&page=1&size=10

