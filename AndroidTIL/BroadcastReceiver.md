# BroadcastReceiver

> * Observer 패턴으로 사용되는 컴포넌트
> * Android는 Broadcast를 통해 시스템과 앱 또는 앱과 앱간의 이벤트를 주고받을 수 있다.

* Android 8.0 (Oreo) 부터는 BroadcastReceiver의 기능이 제한된다
  * [Android 8.0 이상의 SDK를 지원하는 서비스 참고](https://developer.android.com/about/versions/oreo/background?hl=ko)

## Broadcast Receiver  등록

* 앱이 Broadcast를 받으려면 먼저 Receiver를 등록해야 한다.
* BroadcastReceiver객체를 생성하고 이것을 Manifest에 등록하거나 registerReceiver()로 등록 해야한다.
* sendBroadcast()를 이요하여 앱에서 브로드캐스트를 보낼 수 있다.

### 1.정적 등록

* 장점 - 앱이 실행되지 않아도 리시버가 항상 등록되어있기 때문에 모든 이벤트를 받을 수 있다.
  * 대신에 리시버를 해지할수 없다(리시버 Component의 상태를 disabled로 변경하면 동작하지 않게 만들 수 있다.)

* AndroidManifest.xml
  * "<receiver>"  TAG로 아래와 같이 등록

```xml
  <receiver android:name=".MyBroadcastReceiver"  android:exported="true">
        <intent-filter>
            <action android:name="android.intent.action.BOOT_COMPLETED"/>
            <action android:name="android.intent.action.INPUT_METHOD_CHANGED" />
        </intent-filter>
    </receiver>
```

* Broadcast Receiver 서비스클래스 선언
  *  onReceive(Context, Intent) 구현

```java
    public class MyBroadcastReceiver extends BroadcastReceiver {
            private static final String TAG = "MyBroadcastReceiver";
            @Override
            public void onReceive(Context context, Intent intent) {
                StringBuilder sb = new StringBuilder();
                sb.append("Action: " + intent.getAction() + "\n");
                sb.append("URI: " + intent.toUri(Intent.URI_INTENT_SCHEME).toString() + "\n");
                String log = sb.toString();
                Log.d(TAG, log);
                Toast.makeText(context, log, Toast.LENGTH_LONG).show();
            }
        }
    
```

* 앱이 설치되면  BroadcastReceiver 를 등록하며, 앱이 실행중이지 않을때도 메세지를 받으면 메세지에 대해 동작이 가능



### 2.동적 등록

* 장점 - 우너하는 시간에, 원하는 ㅅ아황에서 리시버를 등록하고 해지할 수 있다.
* 단점 - 앱이 실행되지 않으면 리시버를 등록 할 수 없다.

* 안드로이드의 Context가 유효할동안 동작한다
  * 엑티비티  Context로 BroadcastReceiver를 등로갛다면  Activity 가 Destroy될때까지 Receiver는 유효하다
* 메시지를 수신시 등록된  Receiver의 onReceiver() Method에서 수신 가능하다
* BroadcastReceiver가 더이상 필요하지 않을 경우 unregisterReceiver() Method로 등록을 해제 해야한다

```java
SampleBroadcastReciver receiver = new SampleBroadcastReciver();
IntentFilter intentFilter = new IntentFilter();
intentFilter.addAction("test");
registerReceiver(receiver, intetFilter);
```

* sendOrderBroadcast() 
  * 한번에 한 수신자에게만 메세지를 전달
  * BroadcastReceiver를 따라 전파됨에 따라 다음 수신자에게 계속 메세지를 전달 또는 차단 할 수도 있다.
* sendBroadcast()
  * 모든 수신자에게 메세지를 전달
  * LocalBroadcastManager.sendBroadcast() 의 경우 송진자 내부 프로세스에서만 메세지를 전달한다
  * 앱 내부에서만 사용한다면 해당 메서드를 사용하는 것이 효율적이다
  * 프로세스산 통신이 불필요하며 속도 및 보안적으로 유리한 방법

* 리시버 객체 생성

  * java

  ```java
  //익명 클래스로 BroadcastReceiver를 상속받는 클래스 객체를 만든다
  private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
      @Override
      public void onReceive(Context context, Intent intent) {
          //이벤트 code 작성
      }
    };
  ```

  * kotlin

  ```kotlin
  //익명 클래스로 BroadcastReceiver를 상속받는 클래스 객체를 만든다
  private val broadcastReceiver: BroadcastReceiver = object:BroadcastReceiver() {
      override fun onReceive(context: Context?, intent: Intent?) {
          Log.d("Receiver", "Intent: $intent")
          //이벤트 code 작성
      }
  }
  
  //클래스를 정의하고 그 클래스를 객체로 생성
  private val broadcastReceiver = MyBroadcastReceiver()
  class MyBroadcastReceiver : BroadcastReceiver() {
      override fun onReceive(context: Context?, intent: Intent?) {
          Log.d("MyBroadcastReceiver", "Intent: $intent")
      }
  }
  ```

* 리시버 등록 및 해지

  * registerReceiver()로 리시버를 등록할 수 있다
  * 인자로 리시버 객체와., IntentFilter객체를 전달해줘야 한다.
    * IntentFilter는 브로드캐스트로 받고 싶은 인텐트 정보
  * java

  ```java
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState){					     super.onCreate(savedInstanceState);
      setContentView(this.inflateLayout());
                                                               
  	context.registerReceiver
          (mSearchTagReceiver, new IntentFilter(Constants.BROADCAT_RECEIVER));
  }
  
  ```

  * kotlin

  ```kotlin
  private val broadcastReceiver = MyBroadcastReceiver()
  
  override fun onCreate(savedInstanceState: Bundle?) {
      
      val filter = IntentFilter(Intent.ACTION_LOCALE_CHANGED)
      registerReceiver(broadcastReceiver, filter)
  }
  
  //리버 해지
  override fun onDestroy() {
      unregisterReceiver(broadcastReceiver)
  }
  ```

  









## Reference

[https://codechacha.com/ko/android-broadcast-receiver/](https://codechacha.com/ko/android-broadcast-receiver/)

