# Camera2 API

> * 3개 이상의 카메라를 쓸수 있다
>   * (pie 9.0)이상에서는 여러 카메라를 동시에 쓸 수있음
> * 수동 컨트롤 지원
>   * 초점 거리를 정하거나 노출 시간, ISO등을 API에서 직접 설정 해줄 수 있음
> * 연속 촬영, RAW지원 추가

* Camera2 API 순서도
  * CameraManager 
    * 시스템 서비스로, 사용 가능한 카메라와 카메라 기능을 쿼리할 수 있고 카메라를 열수 있다
  * CameraCharacteristics
    * 카메라의속성을 담고 있는 객체
    * 속성을 가져오는 것만 가능
    * 속성을 정하는건 다른 방식으로 처리를 해줘야한다
  * CameraDevice
    * 카메라 객체
  * CaptureRequest
    * 사진 촬영이나 카메라 미리보기를 요청하는데 쓰이는 객체
    * 카메라의 설정을 병경할 때도 관여한다.
  * CameraCaptureSession
    * CaptureRequest를 보내고 카메라하드웨어에서 결과를 받는 세션
  * CaptureResult
    * CaptureRequest의 결과
    * 이미지의 메타데이터도 가져 올수 있다.
* WorkFlow

![image-20200521082954826](image/image-20200521082954826.png) 

![image-20200522112128385](image/image-20200522112128385.png) 



## Data Send

* Base64
  * 인코딩시 인코딩된 문자열의 한Line을 표시하기 위한 flag bit로서 LF(\n)을 삽입된다
  * 76글자가 넘는다면, 해당 위치에 개행문자(LF)를 삽입한다
* EncodeToString

```java
public static String encodeToString (byte[] input, int flags)
```

![img](image/img.png)

* DEFAULT

```java
String serialized = Base64.encodeToString(bytes,Base64.DEFAULT);
```

![img](image/img-1590541655418.png)

* NO_WRAP

```java
String serialized = Base64.encodeToString(bytes,Base64.NO_WRAP);
```

![img](image/img-1590541669156.png)



### Reference

[Base64](https://kjwsx23.tistory.com/234)

[Java Socket Image Send](https://javaexpert.tistory.com/420)

## Err

```
camera Gradle sync failed: The specified Gradle distribution 'https://services.gradle.org/distributions/gradle-4.4-all.zip' does not appear to contain a Gradle distribution.
```

### Reference

[Gradle Download](https://services.gradle.org/distributions/)

[Stackoverflow](https://stackoverflow.com/questions/30526613/android-studio-gradle-sync-error-on-gradle-diagnostics-x-x-x-jar)

[참고 blog](https://nobase-dev.tistory.com/248)

# Reference

[Basic Code](https://github.com/googlearchive/android-Camera2Basic)

[AndroidDevelopers](https://developer.android.com/jetpack/androidx/releases/camera)

[AndroidDevelopers 개요](https://developer.android.com/training/camerax)

[camera2 API Blog](https://blog.shift.moe/2018/09/05/camera2-overview/)

[asdf](https://pluu.github.io/blog/android/droidkaigi/2016/06/11/droidkaigi-2016-first-step-camera2-apis/)

[Camera API LifeCycle](https://cnwlcjf.tistory.com/86)

[Camera Manual Example](http://blog.naver.com/PostView.nhn?blogId=gream50&logNo=221438568982&parentCategoryNo=&categoryNo=20&viewDate=&isShowPopularPosts=true&from=search)

