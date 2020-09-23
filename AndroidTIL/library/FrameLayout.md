#  FrameLayout

> - 자식으로 추가된 여려 View 위젯들 중 하나를 Layout의 전면에 표시할 때 사용하는 class
> - 액자 처럼 화면에 표시될 View를 바꿔가면서 표시하기 위함.

* 구현 화면

> Button을 누를때마다 FrameLayout에 속한 imageView 가 전환되면서 보이는 기능

![image-20200209121817713](C:\Users\hyunh\AppData\Roaming\Typora\typora-user-images\image-20200209121817713.png)

![FrameLayout에서 표시되는 뷰는 가장 마지막 뷰](https://t1.daumcdn.net/cfile/tistory/26354E3C5919493712)

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity">

    <Button
        android:id="@+id/button01"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:text="이미지 바꾸기"
        android:onClick="onButtonClicked"/>

    <FrameLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <ImageView
            android:id="@+id/imageView01"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:src="@drawable/image01"
            android:visibility="invisible"/>

        <ImageView
            android:id="@+id/imageView02"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:src="@drawable/image02"
            android:visibility="visible"/>
    </FrameLayout>

</LinearLayout>
```

## android:visibility

* visible - FrameLayout에 표시
* invisible - 보이지 않게(보이지 않지만 공간을 차지하고 있음.)
* gone - 보이지 안게(공간 자체도 차지하지 않고 있다가 나나는 역할)

```java
package com.example.framelaout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    int imageIndex = 0;
    ImageView imageView01;
    ImageView imageView02;
    Button button01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView01 = (ImageView) findViewById(R.id.imageView01);
        imageView02 = (ImageView) findViewById(R.id.imageView02);
        button01 = (Button) findViewById(R.id.button01);
    }
    public void onButtonClicked(View v){
        changeImage();
    }
    public void changeImage(){
        if(imageIndex == 0){
           imageView01.setVisibility(View.VISIBLE);;
           imageView02.setVisibility(View.INVISIBLE);

           imageIndex = 1;
        }else if(imageIndex == 1){
            imageView01.setVisibility(View.INVISIBLE);
            imageView02.setVisibility(View.VISIBLE);

            imageIndex = 0;
        }
    }
}
```

