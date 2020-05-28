# Compound Button

> 사용자가 IoT 기기 로부터 On 실행 하면 통신이 넘어오면서 Android Device에 해당 Button이 On이 되면서 IoT기기에 실행 하라는 명령어를 날려준다 이러하면, IoT 기기 가 실을 하고 있어도 동일한 On신호가 전달 되기때문에 문제가 발생 할수 있다.
>
>  이것을 방지하기 위해 Android에서 onTouch이 동작 되었을때 IoT에 동작 신호를 보내면 이를 방지 할 수 있다. Android 는 View 의 onTouch가 최상위 이기때문에 이를 가지고 여러 조건을 부여 할 수있다. 

## Button 사용시 주의사항

Log로 예시 확인

* On Touch

> onTouch 가 실행 되고 OnClickListener가 실행 되는 모습을 확인 할수있따.

![image-20200209164908224](C:\Users\hyunh\AppData\Roaming\Typora\typora-user-images\image-20200209164908224.png)

* On Long Touch

> Long Touch 를 사용 할 수도 있다.

![image-20200209170204014](C:\Users\hyunh\AppData\Roaming\Typora\typora-user-images\image-20200209170204014.png)

<img src="C:\Users\hyunh\AppData\Roaming\Typora\typora-user-images\image-20200209161620747.png" alt="image-20200209161620747" style="zoom:25%;" />

![image-20200209135626501](C:\Users\hyunh\AppData\Roaming\Typora\typora-user-images\image-20200209135626501.png)

> Checked - default : false
>
> Listener - OnCheckedChangedListener

## Check Box

> 클릭할때 상태가 check/uncheck
>
> check Box는 서로 독립적으로 동작하며 여러개의 옵션을 고를때 사용

## Toggle Button

>On/Off 상태 표시

```xml
<ToggleButton
        android:id="@+id/tb_01"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>

    <ToggleButton
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:checked="true"/>
```

![image-20200209161413355](C:\Users\hyunh\AppData\Roaming\Typora\typora-user-images\image-20200209161413355.png)

## Switch

> On/Off 상태 표시 

## Radio Button

> 여러 래 중에서 하나만 선택할경우 사용
>
> Radio button 은 Radio Group 안에 정의 해주어야 한다.(Group에 속하지 않으면 중복 체크가 된다.)

```xml
  <RadioGroup
        android:id="@+id/radioG_01"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:layout_margin="18dp">
        <RadioButton
            android:id="@+id/rb_01"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="남성"
            android:textSize="25sp"
            android:layoutDirection="rtl"
            android:textColor="@color/colorRadioButton"/>
        <RadioButton
            android:id="@+id/rb_02"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="영성"
            android:layoutDirection="rtl"
            android:textSize="25sp"
            android:textColor="@color/colorRadioButton"/>
    </RadioGroup>
```





## code

* main XML

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity">
    <Switch
        android:id="@+id/sw"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>
    <Button
        android:id="@+id/btn_01"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="선택"
        android:textSize="25sp"
        android:textStyle="italic"/>

    <RadioGroup
        android:id="@+id/radioG_01"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:layout_margin="18dp">
        <RadioButton
            android:id="@+id/rb_01"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="남성"
            android:textSize="25sp"
            android:layoutDirection="rtl"
            android:textColor="@color/colorRadioButton"/>
        <RadioButton
            android:id="@+id/rb_02"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="영성"
            android:layoutDirection="rtl"
            android:textSize="25sp"
            android:textColor="@color/colorRadioButton"/>
    </RadioGroup>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center_vertical|center_horizontal"
        android:layout_marginTop="50dp"
        android:paddingTop="10dp">
        <CheckBox
            android:id="@+id/cb_01"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="아침"
            android:textColor="@color/colorRadioButton"
            android:textSize="24sp"
            android:layoutDirection="rtl"/>
        <CheckBox
            android:id="@+id/cb_02"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="점심"
            android:textColor="@color/colorRadioButton"
            android:textSize="24sp"
            android:layout_marginLeft="10dp"
            android:layoutDirection="rtl"/>
        <CheckBox
            android:id="@+id/cb_03"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="점심"
            android:textColor="@color/colorRadioButton"
            android:textSize="24sp"
            android:layout_marginLeft="10dp"
            android:layoutDirection="rtl"/>
    </LinearLayout>

    <ImageButton
        android:id="@+id/imagebtn_01"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/ic_launcher_foreground"
        android:layout_marginTop="50dp"
        android:layout_gravity="center"/>
    <ToggleButton
        android:id="@+id/tb_01"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>
    <ToggleButton
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:checked="true"/>

    <include layout="@layout/include_layout"/>
    
</LinearLayout>
```

* main XML 에 include 된 xml

  > main xml과 incllude xml의 Check Box ch_01의 Id 가 같지만 
  >
  > 실제 ID 값은 정수로 저장이 되고 xml이 다르기 때문에 동일한 이름을 적용해도 err가 발생하지 않는다.

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <CheckBox
        android:id="@+id/cb_01"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="include cb"/>
    
</LinearLayout>
```

* MainActtivity

```java
package com.example.compoundbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity /*implements CompoundButton.OnCheckedChangeListener*/ {
    Button btn_01;
    RadioButton rb_01;
    RadioButton rb_02;
    CheckBox cb_01;
    CheckBox cb_02;
    CheckBox cb_03;
    ImageButton imagebtn_01;
    Switch sw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sw = findViewById(R.id.sw);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.v("SWITCH", "onCheckedChanged/" + isChecked);
            }
        });
        sw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("SWITCH", "onclick");
            }
        });

        btn_01 = (Button)findViewById(R.id.btn_01);
        btn_01.setOnClickListener(mClickListener);
        btn_01.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.v("onTouch", "onTouch");
                return false;
            }
        });

        btn_01.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.v("onLongClick", "onLongClick");
                return false;
            }
        });

        rb_01 = (RadioButton) findViewById(R.id.rb_01);
        rb_02 = (RadioButton) findViewById(R.id.rb_02);

        cb_01 = (CheckBox) findViewById(R.id.cb_01);
        cb_01.setOnCheckedChangeListener(mcheckedChanged);
        cb_02 = (CheckBox) findViewById(R.id.cb_02);
        cb_02.setOnCheckedChangeListener(mcheckedChanged);
        cb_03 = (CheckBox) findViewById(R.id.cb_03);
        cb_03.setOnCheckedChangeListener(mcheckedChanged);

        imagebtn_01 = (ImageButton) findViewById(R.id.imagebtn_01);
    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_01 :
                    Log.v("onClick", "OnClickListener");
                break;
            }
        }
    };

    CompoundButton.OnCheckedChangeListener mcheckedChanged = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Log.v("onCheckedChanged", "onCheckedChanged:id=" + buttonView.getId() + "/" + isChecked);
        }
    };
```

