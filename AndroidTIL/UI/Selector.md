# Selector

> * 버튼을 클릭할때  버튼 색상 변경할때 사용한다



## Selector속성 종류

* android:state_pressed - 뷰가 눌렸을 때 (터치나 클릭이 발생했을 때)
* android:state_focused - 뷰에 포커스가 위치했을 때(EditText 를 입력할 수 있을 때)
* android:state_selected - 뷰를 선택했을 때 (방향키로 이동하다가 선택했을 때)
* android:state_checkable - 체크 가능한 상태일 때(체크 박스를 체크할 수 있는상태일 때)
* android:state_checked - 체크된 상태일 때(체크박스가 체크된 상태일 때)
* android:state_enabled - 사용할 수 있는 ㅅ아태 일떄(터치나 클릭 이벤트 등을 받을 수 있는 상태)



## Basic Code

* selector.xml
  * /res/drawable 위치에 drawable리소스로 만들어 준다.

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
  <!-- 해제상태 -->
  <item android:drawable="@color/color_eeeeee" android:state_selected="false" />
  <!-- 선택상태 -->
  <item android:drawable="@color/color_CEDDEF"  android:state_selected="true" />
  <!-- default state -->
  <item android:drawable="@color/color_eeeeee" />
</selector>
```

* main.xml

```xml
<com.github.florent37.shapeofview.shapes.RoundRectView
android:id="@+id/_round_rect_view_a"
android:layout_width="0dp"
android:layout_height="match_parent"
android:layout_weight="10">
<RelativeLayout
android:id="@+id/btn_a"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:background="@drawable/selector_background_color">
    <androidx.appcompat.widget.AppCompatTextView
        android:id="@+id/text_view_a"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="아파트"
        android:textSize="16dp"
        android:textColor="@drawable/selector_text_color"
        android:layout_centerInParent="true"/>
    </RelativeLayout>
</com.github.florent37.shapeofview.shapes.RoundRectView>

<com.github.florent37.shapeofview.shapes.RoundRectView
android:id="@+id/_round_rect_view_b"
android:layout_width="0dp"
android:layout_height="match_parent"
android:layout_weight="10">
<RelativeLayout
android:id="@+id/btn_b"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:background="@drawable/selector_background_color">
    <androidx.appcompat.widget.AppCompatTextView
        android:id="@+id/text_view_b"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="그 외"
        android:textSize="16dp"
        android:textColor="@drawable/selector_text_color"
        android:layout_centerInParent="true"/>
    </RelativeLayout>
</com.github.florent37.shapeofview.shapes.RoundRectView>
```

* Activity
  * btn_a 를 클릭하면 btn_a는 선택상태가 되고, btn_b는 해제 상태가 된다.

```java
 @OnClick(R.id.btn_a)
  public void touched(){
    btn_a.setSelected(true);
    btn_b.setSelected(false);
  }
 @OnClick(R.id.btn_b)
  public void touched(){
    btn_a.setSelected(false);
    btn_b.setSelected(true);
  }
```

* image

![image-20200914214546201](Selector.assets/image-20200914214546201.png)

![image-20200914214558186](Selector.assets/image-20200914214558186.png)







## Reference

[https://sharp57dev.tistory.com/13](https://sharp57dev.tistory.com/13)