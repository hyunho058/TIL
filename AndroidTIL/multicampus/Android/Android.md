# Android

> * 안드로이드 실습 -10일
> * 네트워크(JAVA) - 5일
>   * Thread, Java IO(NIO), TCP 
>   * 메신져 체팅방(1개방, N개방)
> * 아두이노- 5일
> * CAN프로토콜 이용한 시리얼 통신 - 5일

## 개발환경

* JDK
* Android Studio IDI
* AVD(Android VIrtual Device)

## Android 정의

> 아래 요소들들이 포함되어 있는 모바일 디바이스를 위한 소프트웨어 스텍

* OS(Linux Kernel)
* middlware
* 핵심 어플리케이션
  * 전화
  * 캘러리
  * 카메라
  * browser

## 특징

* 오픈소스
* 사용언어
  * JAVA
  * Kotlin
* 앱간의 자유로운 연동
  * 설정 필요
* [ART Runtime](https://developer.android.com/guide/practices/verifying-apps-art?hl=ko)

## Version

* SDK - 2007년
* 8(오레오) - 9(파이) - 10(Q)

## 시장점유율

* 오픈소스
* Eco system이 잘 되어있음.

## Android Framework

* library
  * 틍정 기능을 쉽고 편하게 구현
  * 유지보수가 어려움
* framework
  * framework를 이해해야 사용 가능
  * 유지보수에 좋다

## component

* Activity
  * Android App의 화면 1개 의미
  * UI를 담당하는 component
  * 사용자의 Event처리 담당
  * load가 많은 작업처리를 하지 않는다.
    * Service, Thread를 이용해 Logic처리 해야함
* Service
  * 내부background logic처리
    * DB연결, NetWork연결, 
  * 사용자와의 interaction은 담당하지 않음
* Broadcast Receiver
  * android 내부 시스템 신호를 받아 처리하는 역할
    * LTE도중 Wi-FI신호를 받을떄 wi-fi 전환
* Content Provider & Resolver
  * 모든 android App은 sandbox model을 이용
  * App간에 데이터 공유
    * 전화 엡에서 전화걸때 주소록에서 데이터를 공유