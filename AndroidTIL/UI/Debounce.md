# Debounce

* 많은 이벤트가 발생해도 모두 무시하고 특정 시간사이에 어떤 이벤트도 발생하지 않았을때 한번만 이벤트를 발생
  * 예로 5ms가 지나기전에 계속이벤트가 발생할 경우 Callback 반응하는 이벤트는 발생하지 않고 무시 된다

* build.gradle

```java
implementation 'io.reactivex.rxjava2:rxandroid:2.0.2'
implementation 'io.reactivex.rxjava2:rxjava:2.1.13'
```

* RxAndroid 스케줄러
  * newThread() - 새로운 스레드 생성
  * single() - 단일 스레드 생성 후 사용
  * computation() - 계산요 Thread
  * io() - network, 파일 입출력 스레드
  * trampoline() - 현제 스레드에 대기행렬 생성



