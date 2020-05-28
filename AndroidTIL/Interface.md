# Interface

**interfaceTest** project

> 1. 클레스에 접근할 수있는 방법이 무성인지를 정의한것.
> 2. nterface(=구현하다)-인터페이스는 껍데기만 있고 실행될 코드가 없던것에 직접 실행 가능한 기능을 넣어서 사용



* # 구현 화면

  > 버튼을 클릭하면 각 버튼마다 이미지를 다르게 표헌해주는 기능

![image-20200208224924750](C:\Users\hyunh\AppData\Roaming\Typora\typora-user-images\image-20200208224924750.png)



* interface 구현

```java
package com.example.interfacetest;

public interface interfaceTest {
    public void toasTest(String sound);
}
```

* interface  구현한 class

> Toast 를 활용한 인터페이스 사용

context

> 자신이 어떤 어플리케이션을 나타내고 있는지 알려주는 ID 역할
>
> ActivityManagerService  에 접근 할 수 있도록 하는 통로 역할

* Application Context는 안드로이드 어플리케이션의 핵심 기능을 위한 중심부 역할을 합니다.
* Context는 여러 Activity 인스턴스들 간에 리소스를 공유하거나 설정등에 접근하기 위해 사용됩니다.

```java
package com.example.interfacetest;

import android.content.Context;
import android.widget.Toast;

public class ImageInter implements interfaceTest {

    Context mContext;

    public ImageInter() {
        super();
    }
    public ImageInter(Context context) {
        this.mContext = context;
    }

    public void toasTest(String sound){
        Toast.makeText(mContext, sound, Toast.LENGTH_SHORT).show();
    };
}
```

* MainActivity

```java
package com.example.interfacetest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    int buttonLIst[] = {
            R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
            R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8,
            R.id.btn_9, R.id.btn_10, R.id.btn_11, R.id.btn_12,
    };

    ImageView image01;
    ImageInter mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image01 = (ImageView) findViewById(R.id.image01);
        mToast = new ImageInter(getApplicationContext());

        for(int btnId : buttonLIst){
            Button btn = (Button) findViewById(btnId);
            btn.setSoundEffectsEnabled(true);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
                        case  R.id.btn_1:
                            image01.setImageResource(R.drawable.ig1);
                            image01.setScaleType(ImageView.ScaleType.CENTER_CROP);
                             mToast.toasTest("야옹");
//                            AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//                            audioManager.playSoundEffect(SoundEffectConstants.CLICK);
                            break;
                        case  R.id.btn_2:
                            image01.setImageResource(R.drawable.ig2);
                            break;
                        case  R.id.btn_3:
                            image01.setImageResource(R.drawable.ig3);
                            break;
                        case  R.id.btn_4:
                            image01.setImageResource(R.drawable.ig4);
                            break;
                        case  R.id.btn_5:
                            image01.setImageResource(R.drawable.ig5);
                            break;
                        case  R.id.btn_6:
                            image01.setImageResource(R.drawable.ig6);
                            break;
                        case  R.id.btn_7:
                            image01.setImageResource(R.drawable.ig7);
                            break;
                        case  R.id.btn_8:
                            image01.setImageResource(R.drawable.ig8);
                            break;
                        case  R.id.btn_9:
                            image01.setImageResource(R.drawable.ig9);
                            break;
                        case  R.id.btn_10:
                            image01.setImageResource(R.drawable.ig10);
                            break;
                        case  R.id.btn_11:
                            image01.setImageResource(R.drawable.ig11);
                            break;
                        case  R.id.btn_12:
                            image01.setImageResource(R.drawable.ig12);
                            break;
                    }
                }
            });
        }
    }
}
```



* MainXML

> maintActivity 에 해당 코드 값을 부여하고 image01.setScaleType(ImageView.ScaleType.CENTER_CROP); 
>
> mainXlML 에서 src를 image id를 대입하고  padding  을 적용하면 background를 상대물로하여 src에 선언된 이미지에 padding 값이 적용 된다.

```xml
<ImageView
        android:id="@+id/image01"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        android:layout_marginTop="80dp"
        android:padding="15dp"
        android:scaleType="centerCrop"
        android:src="@drawable/ig4"
        android:background="@drawable/imageview"/>   //shape tag를 활용하여 백그라운드에 노란 태두리 형성
```

* imageview.xml file

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item>
        <shape android:shape="rectangle">
            <stroke android:color="@color/imageViewCol" android:width="15dp"/>
            <size android:width="250dp" android:height="250dp"/>
        </shape>
    </item>
</selector>
```

