# QR code

* Zxing library
* Dependencies

```
compile 'com.google.zxing:core:3.2.0'
compile 'com.journeyapps:zxing-android-embedded:3.0.0@aar'
```

* Manifest.xml

```xml
<uses-feature android:name="android.hardware.camera"/>
<uses-permission android:name="android.permission.CAMERA"/>
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.FLASHLIGHT"/>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
```

* Fragment Zxing code

```java
@Nullable
@Override
public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_fridge,container,false);
    context=container.getContext();
    //QR code Scanner Start
    IntentIntegrator.forSupportFragment(FragmentFridge.this).initiateScan();

    return  view;
}
/**
* QR code - Zxing Library
*/
@Override
public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    Log.v(TAG,"onActivityResult"+resultCode);
    if(resultCode == Activity.RESULT_OK) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        Log.v(TAG, "result.getContents() == "+scanResult.getContents());
        Toast.makeText(context,scanResult.getContents(),Toast.LENGTH_SHORT).show();
    } else {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
```



# 참고 자료

[QRcode](https://namjackson.tistory.com/15)

[naver and Zxing](http://blog.naver.com/PostView.nhn?blogId=rain483&logNo=220848490340&parentCategoryNo=&categoryNo=16&viewDate=&isShowPopularPosts=false&from=postView)

[Lotto조회]()https://everyshare.tistory.com/51

[StackOverflow](https://stackoverflow.com/questions/37251583/how-to-start-zxing-on-a-fragment/43966669)

