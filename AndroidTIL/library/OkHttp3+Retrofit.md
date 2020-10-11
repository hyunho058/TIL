# OkHttp3+Retrofit2

> * 기존 안드로이드에서 네트워크를 구현 시 Thread의 개념을 적용하고 여러 메소드를 오버라으 해야 하는 방식의 AsyncTask를 사용 하였다.
>   * 내부적으로 AsyncTask에서 구현해야 했던 Thread개념이 적용되어 있고 기존에 쓰였던 HttpUrlConnection Class의 기능을 확장해서 특화된 Request, Response의 Class들을 제공
> * Retrofit2는 기본적으로 OkHttp를 네트워킹 계층으로 활용하며 그위에 구축된다.
>   * Retrofit은 자동적으로 JOSN 응답을 사전에 정의된POJO를 통해 직렬화 할수 있다.
>   * JSON을 직렬화 하기 위해서는 먼저 Gson converter가 필요하다

![image-20201011232115866](OkHttp3.assets/image-20201011232115866.png)

## 의존성

```java
implementation 'com.squareup.okhttp3:okhttp:3.13.1'
implementation 'com.squareup.okhttp3:logging-interceptor:3.4.2'
```

##  장점

1. Interceptor 처리하는 것이 편하다
   1. Apllication Interceptors
      * Application과 OKHttp 사이에 Request, Response정보를 intercept하여 추가적으로 처리
   2. Network Interceptors
      * Network와 OKHttp사이에  Requests, Responses 정보를 intercept하여 추가적으로 처리
2. 기본 설정값 : OKHttp는 강력한 기본값들이 잘 설정되어 있음
3. Retrofit2는 기본적으로 OkHttp를 네트워킹 계층으로 활용하며 그위에 구축된다.

## OkHttp Interceptors

* OkHttp에 있는 강력한 메커니즘으로 호출을 모니터, 재 작성 및 재시도를 할수 있다.
* Interceptorsms 는 두가지 카테고리로 분류할 수 있다
  * Apllication Interceptors
    * Application Interceptor를 등록하려면 `OkHttpClient.Builder`에서 `addInterceptor()`를 호출해야 한다.
  * Network Interceptors
    * Network Interceptor를 등록하려면 `addInterceptor()` 대신 `addNetworkInterceptor()`를 추가해야 한다.

## Retrofit Interface Setting

* APIClient.java
  * getClient()는 Retrofit Interface를 설정할 때마다 호출된다.
  * Retrofit은 @GET, @POST, @PUT, @DELETE, @PATCH or @HEAD와 같은 **Annotation**을 통해 HTTP method를 이용한다

```java
package com.journaldev.retrofitintro;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class APIClient {

    private static Retrofit retrofit = null;

    static Retrofit getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = 
            new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }
}
```



* APIInterface.java
  * Annotation을 통해  HTTp request 작성
  * @GET() 은 dogetListResources()를 호출
    * dogetListResources()

```java
package com.journaldev.retrofitintro;

import com.journaldev.retrofitintro.pojo.MultipleResource;
import com.journaldev.retrofitintro.pojo.User;
import com.journaldev.retrofitintro.pojo.UserList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

interface APIInterface {

    @GET("api/unknown")
    Call<MultipleResource> doGetListResources();

    @POST("api/users")
    Call<User> createUser(@Body User user);

    @GET("api/users?")
    Call<UserList> doGetUserList(@Query("page") String page);

    @FormUrlEncoded
    @POST("api/users?")
    Call<UserList> doCreateUserWithField(
        @Field("name") String name, @Field("job") String job);
}
```



## Reference

[Github - Okhttp3 오픈소스](https://github.com/square/okhttp)

[Android - OkHttp3 / Web Server 연동](https://m.blog.naver.com/PostView.nhn?blogId=scw0531&logNo=220829952304&proxyReferer=https:%2F%2Fwww.google.com%2F)

[Android - Retrofit2 + OkHttp3](https://jongmin92.github.io/2018/01/29/Programming/android-retrofit2-okhttp3/)

