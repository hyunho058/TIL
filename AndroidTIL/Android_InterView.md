# Android - InterView

## 안드로이드의 실행한경

* 크게 4가지 실행환경으로 구성되어있다.
  * 리눅스커널, 라이브러리, 어플리케이션 프레임워크, 어플리케이션
  * 리눅스커널
    * OS로 안드로이드 디바이스의 다양한 하드웨어(카메라, 화면, GPS,메모리, 등)를 관리
  * 라이브러리
    * 다양한기능 (UI처리, 미디어 프레임워크, 데이터베이스, 그래픽 처리)을 소프트웨어적으로 구현해 놓은 환경뿐만 아니라 안드로이드 앱을 구성해주는 dalvik 가상머신과 코어 라이브러리까지 포함한는 영역  
  * 어플리케이션 프레임워크
    * 사용자의 입력 또는 특정한 이벤트에 따라 출력을 담당하는 환경
  * 어플리케이션
    * 실제로 동작하는 앱이 설치되는 환경

## 안드로이드는 다른 플랫폼에 비해 어떤 장점이 있는지

* 안드로이드를 구성하는 모든 소스가 오픈소스로 무료로 개방되어있어 비용적인 부담이 없다
* 전 세계의 개발자로부터 피드백을 받아 수정되기 때문에 안정성과 버그 수정이 빠르다
* 원하는 소스를 다운받아 수정해서쓰기 편리하다
* 리눅스 커널을 OS로 채택했기 때문에 다양한 하드웨어에 대한 드라이버 소스가 풍부하다

## 안드로이드 4대 컴포넌트(Component)

* 액티비티
  * 화면을 관리하고 이벤트를 처리하는 컴포넌트
* 서비스
  * 화면에 보이지 않지만 특정한 기능을 백그라운드에서 수행하는 컴포넌트
* 브로드캐스트 리시버
  * 디바이스에서 발생하는 특정 메세지를 처리하기 위한 컴포넌트
* 콘텐트 프로바이더
  * 앱간 데이터의 공유를 위해 표준화된 인터페이서를 제공

## 안드로이드 프로젝트 구성요소

* libs - 프로젝트에서 사용하는 다양한 라이브러리 소스가 저장된 공간
* androidTest - 앱의 일부 코드를 테스트하기  위한 소스를 공간
* java - 자바 코드를 저장하는 공간
* res - 리소스를 저장하는 공간(이미지, xml 메뉴)
* AandroidMaifest.xml - 앱에 대한 전체적인 정보를 담고있는 파일
  * 앱의 구성요소와 실행 권한 ㅈ어보가 정의
* build.gradle(project) - 프로그래머가 직접 장성한 그래들 빌드 스크립트 파일
* build.gradle(app) - 앱에 대한 컴파일 버전정보, 으존성 프로젝트에 대한 정의

## AndroidManifest.xml 에 대해 설명

* 앱의 이름, 버전, 구성요소, 권한등의 앱의 실행에 있어서 필요한 각종 정보가 저장되어있는 파일
* 반드시 존재해야하는 xml형식의 파일로 안드로이드 프로젝트의 최상위에 위치하고 있다.

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
package="com.example.booksearchrecyclerviewkakaoapi">
<!--패키지명, 앱버전 코드 , 뱁 버전 이름-->
<uses-permission android:name="android.permission.INTERNET" />
<!--앱에서 필요한 권한 내용-->
<application
    android:allowBackup="true"
    android:hardwareAccelerated="true"
    android:icon="@mipmap/ic_launcher"
    android:label="@string/app_name"
    android:roundIcon="@mipmap/ic_launcher_round"
    android:supportsRtl="true"
    android:theme="@style/AppTheme">
    <!--앱 아이콘, 앱 이름 정의-->
    <activity android:name=".MainActivity">
        <!--클래스명과 액티비티 이름읠 정의-->
        <intent-filter>
            <!--액티비티에 대한 인텐트 작업시 필요한 action 정의-->
            <action android:name="android.intent.action.MAIN" />
            <category android:name="android.intent.category.LAUNCHER" />
        </intent-filter>
    </activity>
</application>

</manifest>
```

## Display, window, surface, View, ViewGroup, ViewContainer,layout에 대한 설명

* Display - 단말기가 가지고있는 하드웨어 화면
* Window - 실행되는 앱이 View를 그릴수 있는 영역
  * 사용자로부터 입력 이벤트를 받아 앱에 전달
* surface - 윈도우에 View를 그릴때 그림이 저장되는 메모리 버퍼
* View - 인터페이스를 구성하는 최상위 클래스
  * 화면에 어떤모양으로 그림을 그릴지와 발생하는 이벤트를 처리하는 기능을 구현뷰중에서 일반적인 제어 역할을 하고 있는 것들을 위젯이라고 한다
* ViewGroup - 여러개의 뷰를 포함하고 있는 뷰를 의미
* ViewContainer - 다른 뷰를 포함할 수 있는 뷰를 의미
  * ex - ListView, ScrollView, GridView
* Layout - 뷰 그룹 중에서 내부에 뷰를 포함하고 있으면서 해당 뷰를 어떻게 윈도우에 배치할지 정의하는 관리자 역할을 하는 클래스

## 인플레이션(inflation)?

* xml 레이아웃 파일로 정의한 정보를 런타임에 setContentView() 메소드가 호출됨에 따라 메모리 상에 객체로 만들어주는 과정

## application과 context에 대해 설명

* application
  * 앱 프로세스가 실행되면 가장 먼저 생서되는 객체로 하나의 어플리케이션 객체는 하나의 앱프로세스와 대응된다
* context
  * 컴포넌트들이 동작하기 위해 필요한 정보를 담고 있는객체
  * 어플리케이션 정보(패키지염 등), 컨텍스트가 실행되는데 필요한 정보를 얻거나 시스템 서비스를 구동하는데 사용

