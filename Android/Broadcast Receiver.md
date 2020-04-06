# Broadcast Receiver

* Broadcast  - Android System 신호를 받는 역할

* Android의 Broadcast signal

  1. Android system 자체에서 발생(베터리 부족,메세지 알람, wi-fi신호...)

  2. 사용자 App에서 임의로 발생

## 기본 Broadcast message

* 일반적인 Broadcast message

  * Broadcast Receiver를 등록(생성)

    1. AndroidManifest.xml file에 명시

    2. 코드상에서 Receiver를 만들어서 등록

       1. IntentFilter 생성
       2. Broadcast Receiver 객체 생성
       3. Filter와 함께 Broadcast Receiver를 등록

       ```java
       case R.id.btnBroadcastRegister:
           //Broadcast Receiver 호출
           //Broadcast Receiver 객체를 만들어 IntentFilter 와 함께 시스템에 등록
           //1. IntentFilter 생성
           IntentFilter intentFilter = new IntentFilter();
           intentFilter.addAction("MY_BROADCAST_SIGNAL");
           //2.Broadcast Receiver 객체 생성
           broadcastReceiver = new BroadcastReceiver() {
               @Override
               public void onReceive(Context context, Intent intent) {
                   //Receiver 가 신호를 받게 되면 이 부분이 호출
                   //해당 신호를 받게 되면 Logic 처리
                   if(intent.getAction().equals("MY_BROADCAST_SIGNAL")){
                       Toast.makeText(getApplicationContext(),"신호 받음",
                                      Toast.LENGTH_LONG).show();
                   }
               }
           };
           //3. Filter와 함께 Broadcast Receiver를 등록
           registerReceiver(broadcastReceiver,intentFilter);
           break;
       case R.id.btnBroadcastunRegister:
           //등록 해제
           unregisterReceiver(broadcastReceiver);
           break;
       case R.id.btnSendBroadcast:
           Log.v(TAG,"onClick=="+btnSendBroadcast.getText());
           //onClick 시 Broadcast 를 임의로 발생
           Intent intent = new Intent("MY_BROADCAST_SIGNAL");
           sendBroadcast(intent);
           break;
       ```



## SMS Broadcast Receiver

* SMS Broadcast Receiver

  1. 보안처리

     * AndroidManifest.xml 파일에 permission 추가

     ```xml
     <uses-permission android:name="android.permission.RECEIVE_SMS"/>
     ```

  2. Activity실행시 보안설정 표시

     * 마쉬멜로우 버전 이후부터 적용

     * ```java
       requestPermissions(new String[]{Manifest.permission.RECEIVE_SMS},100);
       String[]{}배열 안에 여러 permission 을 넣어주면 여러개의 권한 설정들을 넣어 권한들을 물어볼수 있다.
       ```

  3. Broadcast Receiver Component 생성

     * AndroidManifest.xml에 등록해서 생성

     * 외부 component로 생성되기 떄문에 AndroidManifest.xml에 자동 등록

       * receiver 파일 생성 하면 AndroidManifest.xml 파일에 receiver 등록
       * intent-filter를 이용해서 어떤 broadcast signal을 수신할 지를 설정

       ```xml
       <receiver
       	android:name=".SUbBroadcastSMS"
       	android:enabled="true"
       	android:exported="true">
       </receiver>
       
       <receiver
       	android:name=".SUbBroadcastSMS"
       	android:enabled="true"
       	android:exported="true">
       	<intent-filter>
       		<action android:name="android.provider.Telephony.SMS_RECEIVED"/>
       	</intent-filter>
       </receiver>
       ```

     * SMS문자가 오면 이 문자를 Broadcast Receiver가 받아서 화면에 표현하기 위해 Activity에게 전달

       * Broadcast
         * SMS데이터 받아와 Activity에 전달

       ```java
       import android.content.BroadcastReceiver;
       import android.content.Context;
       import android.content.Intent;
       import android.os.Build;
       import android.os.Bundle;
       import android.telephony.SmsMessage;
       import android.util.Log;
       
       import java.util.Date;
       
       public class SUbBroadcastSMS extends BroadcastReceiver {
           //보안 설정이 되었으면 특정 Signal(Broadcast기 전파되면)이 발생하면 해당 Broadcast를 받을수 있다.
           String TAG="SUbBroadcastSMS";
       
           @Override
           public void onReceive(Context context, Intent intent) {
               //Broadcast를 받으면 호출
               //SMS가 도착하면 호출
               Log.v(TAG,"SMS 도착");
               //Intent안에 포함되어 있는 정보 추출
               Bundle bundle = intent.getExtras();
               //Bundle안에 key,value형태로 데이터가 여러게 저장되어있는데 sms 정보는 "pdus"라는 키값으로 저장되어있다
               //동시에 여러개의 SMS가 도착할수 있다 (객체 1개가 SMS메시지 1개를 의미함)
               Object[] objects = (Object[])bundle.get("pdus");
       
               SmsMessage[] smsMessages = new SmsMessage[objects.length];
               //예제에서는 1개의 SMS만 전달된다고 가정하고 진행한다.
               if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                   //Object 객체 형테를SmsMessage 객체 형테로 converting
                   String format = bundle.getString("format");
                   smsMessages[0] = SmsMessage.createFromPdu((byte[])objects[0],format);
               }else {
                   smsMessages[0] = SmsMessage.createFromPdu((byte[])objects[0]);
               }
               String sender = smsMessages[0].getOriginatingAddress();
               String msg = smsMessages[0].getMessageBody();
               String reDate = new Date(smsMessages[0].getTimestampMillis()).toString();
       
               Log.v(TAG,"sender="+sender);
               Log.v(TAG,"msg="+msg);
               Log.v(TAG,"reDate="+reDate);
       
               Intent intent1 = new Intent(context, BroadcastSMSActivity.class);
               intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
               intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               intent1.putExtra("sender",sender);
               intent1.putExtra("msg",msg);
               intent1.putExtra("reDate",reDate);
               context.startActivity(intent1);
           }
       }
       ```

       * Activity
         * 권한(보안) 설정
         * Broadcast로 부터 데이터(SMS) 반아오기
           * onNewIntent() 이용하여 데이터 받아옴

       ```java
       import androidx.annotation.NonNull;
       import androidx.appcompat.app.AppCompatActivity;
       import androidx.core.app.ActivityCompat;
       
       import android.Manifest;
       import android.app.AlertDialog;
       import android.content.DialogInterface;
       import android.content.Intent;
       import android.content.pm.PackageManager;
       import android.os.Build;
       import android.os.Bundle;
       import android.util.Log;
       import android.widget.Button;
       import android.widget.TextView;
       
       public class BroadcastSMSActivity extends AppCompatActivity {
           String TAG="BroadcastSMSActivity";
           TextView tvSender;
           TextView tvMessage;
           TextView tvDate;
           @Override
           protected void onCreate(Bundle savedInstanceState) {
               super.onCreate(savedInstanceState);
               setContentView(R.layout.activity_broadcast_sms);
       
               tvSender=findViewById(R.id.tvSender);
               tvMessage=findViewById(R.id.tvMessage);
               tvDate=findViewById(R.id.tvDate);
       
               //1. 보안처리 (AndroidManifest.xml파일에 기본 보안에 대한 설정)
               //1.1 Android version 이 마쉬멜로우 버전 이전, 이후인지 check
               if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                   //1.1.1 Version M 이상
                   Log.v(TAG,"Version Check=="+Build.VERSION.SDK_INT);
                   int permissionResult = ActivityCompat.checkSelfPermission(
                           getApplicationContext(), Manifest.permission.RECEIVE_SMS);
       
                   if(permissionResult == PackageManager.PERMISSION_DENIED){
                       //권한 없을 경우
                       //1. 앱을 처음 실행해서 물어본적이 없는경우
                       //2. 권한 허용에 대해서 사용자에게 물어보았지만 사용자가 거절을 선택 했을경우
                       if(shouldShowRequestPermissionRationale(Manifest.permission.RECEIVE_SMS)){
                           //true => 권한을 거부한적 있는 경우(일반적으로 dialog를 이용해서 다시 물어봄)
                           AlertDialog.Builder dialog = new AlertDialog.Builder(BroadcastSMSActivity.this);
                           dialog.setTitle("권한 필요");
                           dialog.setMessage("SMS 수신기능이 필요합니다, 수락할거니");
                           dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {
                                   Log.v(TAG,"dialog_onClick==YES");
                                   requestPermissions(new String[]{Manifest.permission.RECEIVE_SMS},100);
                               }
                           });
                           dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {
                                   Log.v(TAG,"dialog_onClick==NO");
                               }
                           });
                           dialog.create().show();
                       }else {
                           //false => 한번도 물어본적 없는경우
                           requestPermissions(new String[]{Manifest.permission.RECEIVE_SMS},100);
                           //여러개의 권한을 물어볼수 있기 때문에 String[] 배열에 넣어줌 한뻐너에 다 처리할 수 있음
                       }
                   }else {
                       //권한 있을 경우
                       Log.v(TAG,"Check=="+permissionResult+" /보안설정 통과");
                   }
               }else {
                   //1.1.2 Version M 미만
                   Log.v(TAG,"Version Check=="+Build.VERSION.SDK_INT+" /보안설정 통과");
               }
           }
       
           @Override
           public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
               super.onRequestPermissionsResult(requestCode, permissions, grantResults);
               Log.v(TAG,"onRequestPermissionsResult() _ requestCode=="+requestCode);
               //사용자가 권한을 설정하게 되면 이 Method가 마지막으로 호출 됨.
               if(requestCode == 100){
                   if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                       //사용자가 권한 허용을 눌렀을 경우
                       Log.v(TAG,"onRequestPermissionsResult()_보안 통과"+grantResults[0]);
                   }
               }
           }
       
           @Override
           protected void onNewIntent(Intent intent) {
               super.onNewIntent(intent);
               tvSender.setText(intent.getStringExtra("sender"));
               tvMessage.setText(intent.getStringExtra("msg"));
               tvDate.setText(intent.getStringExtra("reDate"));
           }
       }
       
       ```

       

     

     

     

