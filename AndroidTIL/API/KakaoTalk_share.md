# KakaoTalk_share

## 설정

* build.gradle(Project)

```
subprojects {
    repositories {
        mavenCentral()
        maven { url 'http://devrepo.kakao.com:8088/nexus/content/groups/public/' }
    }
}
```

* bulid.gradle(Module)

```
implementation group: 'com.kakao.sdk', name: 'kakaolink', version: project.KAKAO_SDK_VERSION
```

* gradle.properties

```
KAKAO_SDK_GROUP=com.kakao.sdk
KAKAO_SDK_VERSION=1.27.0
```

* AndroidManifest.xml

```xml
<activity
      android:name=".activity.main"
      android:configChanges="orientation|keyboardHidden"
      android:screenOrientation="portrait"
      android:windowSoftInputMode="stateHidden">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
    <!--아래 코드를 작성해준다-->
    <intent-filter>
        <action android:name="android.intent.action.VIEW" />
        <category android:name="android.intent.category.DEFAULT" />
        <category android:name="android.intent.category.BROWSABLE" />

        <data android:scheme="@string/kakao_scheme"
              android:host="@string/kakaolink_host" />
    </intent-filter>
</activity>
    <meta-data
        android:name="com.kakao.sdk.AppKey"
        android:value="@string/kakao_app_key" />
```

* res - values - kakao_string.xml
  * res - values 경로에 kakao_string.xml 파일을 생성해 아래 코드를 작성한다.

```xml
<resources>
    <!-- 카카오 디벨로퍼 앱 만들기에서 네이티브 키를 입력해주시면 됩니다. -->
    <string name="kakao_app_key">AAAAAAA네이티브 키AAAAAAA</string>
    <string name="kakao_scheme">kakaoAAAAAAA네이티브 키AAAAAAA</string>
    <string name="kakaolink_host">kakaolink</string>
</resources>
```

## Java Code

```java
public void kakaolink() {
    FeedTemplate params = FeedTemplate
            .newBuilder(ContentObject.newBuilder(
                "디저트 사진",
                "http://mud-kage.kakao.co.kr/dn/NTmhS/btqfEUdFAUf/FjKzkZsnoeE4o19klTOVI1/openlink_640x640s.jpg",
                    LinkObject.newBuilder().setWebUrl("https://developers.kakao.com")
                            .setMobileWebUrl("https://developers.kakao.com").build())
                    .setDescrption("아메리카노, 빵, 케익")
                    .build())
            .setSocial(SocialObject.newBuilder().setLikeCount(10).setCommentCount(20)
                    .setSharedCount(30).setViewCount(40).build())
            .addButton(new ButtonObject("웹에서 보기", LinkObject.newBuilder().setWebUrl("'https://developers.kakao.com").setMobileWebUrl("'https://developers.kakao.com").build()))
            .addButton(new ButtonObject("앱에서 보기", LinkObject.newBuilder()
                    .setWebUrl("'https://developers.kakao.com")
                    .setMobileWebUrl("'https://developers.kakao.com")
                    .setAndroidExecutionParams("key1=value1")
                    .setIosExecutionParams("key1=value1")
                    .build()))
            .build();

    Map<String, String> serverCallbackArgs = new HashMap<String, String>();
    serverCallbackArgs.put("user_id", "${current_user_id}");
    serverCallbackArgs.put("product_id", "${shared_product_id}");

    KakaoLinkService.getInstance().sendDefault(this, params, serverCallbackArgs, new ResponseCallback<KakaoLinkResponse>() {
        @Override
        public void onFailure(ErrorResult errorResult) {
            Logger.e(errorResult.toString());
            Timber.i("KeyHash kakao: %s",errorResult.toString());
        }

        @Override
        public void onSuccess(KakaoLinkResponse result) {
            // 템플릿 밸리데이션과 쿼터 체크가 성공적으로 끝남. 톡에서 정상적으로 보내졌는지 보장은 할 수 없다. 전송 성공 유무는 서버콜백 기능을 이용하여야 한다.
            Timber.i("KeyHash kakao: %s",result.toString());
        }
    });
}
```

## Kotlin Code

```kotlin
fun kakaoLink() {
        val params = FeedTemplate
            .newBuilder(
                ContentObject.newBuilder(
                    "디저트 사진",
                    "http://mud-kage.kakao.co.kr/dn/NTmhS/btqfEUdFAUf/FjKzkZsnoeE4o19klTOVI1/openlink_640x640s.jpg",
                    LinkObject.newBuilder().setWebUrl("https://developers.kakao.com")
                        .setMobileWebUrl("https://developers.kakao.com").build()
                )
                    .setDescrption("아메리카노, 빵, 케익")
                    .build()
            )
            .setSocial(
                SocialObject.newBuilder().setLikeCount(10).setCommentCount(20)
                    .setSharedCount(30).setViewCount(40).build()
            )
            .addButton(
                ButtonObject(
                    "웹에서 보기",
                    LinkObject.newBuilder().setWebUrl("https://developers.kakao.com").setMobileWebUrl(
                        "https://developers.kakao.com"
                    ).build()
                )
            )
            .addButton(
                ButtonObject(
                    "앱에서 보기", LinkObject.newBuilder()
                        .setWebUrl("'https://developers.kakao.com")
                        .setMobileWebUrl("https://developers.kakao.com")
                        .setAndroidExecutionParams("key1=value1")
                        .setIosExecutionParams("key1=value1")
                        .build()
                )
            )
            .build()

        val serverCallbackArgs: MutableMap<String, String> =
            HashMap()
        serverCallbackArgs["user_id"] = "\${current_user_id}"
        serverCallbackArgs["product_id"] = "\${shared_product_id}"

        KakaoLinkService.getInstance().sendDefault(
            this,
            params,
            serverCallbackArgs,
            object : ResponseCallback<KakaoLinkResponse?>() {
                override fun onFailure(errorResult: ErrorResult) {
                    Logger.e(errorResult.toString())
                }

                override fun onSuccess(result: KakaoLinkResponse?) { // 템플릿 밸리데이션과 쿼터 체크가 성공적으로 끝남. 톡에서 정상적으로 보내졌는지 보장은 할 수 없다. 전송 성공 유무는 서버콜백 기능을 이용하여야 한다.
                }
            })
    }
```



## Reference

[kakao Developer](https://developers.kakao.com/docs/latest/ko/message/android)

[https://thisisspear.tistory.com/52](https://thisisspear.tistory.com/52)

