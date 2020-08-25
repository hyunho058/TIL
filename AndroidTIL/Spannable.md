# Spannable

> * 텍스트의 **'일부'** 에만 색을 입히거나, 크기를 늘리거나 등등 효과를 넣을 수있다



## Basic Code

* Spannable를 사용하기 위해서는 Buffer Type속성을 Spannalbe로 지정 해야한다

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    tools:context=".MainActivity">

    <TextView
        android:id="@+id/tv_sample"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="안녕하세요 라크라꾸입니다."
        android:bufferType="spannable"
        android:textSize="30dp"/>

</LinearLayout>
```

```java
public class MainActivity extends AppCompatActivity {
    private Context mContext;

    private TextView tv_sample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();

        tv_sample = (TextView) findViewById(R.id.tv_sample);

        Spannable span = (Spannable) tv_sample.getText();
        span.setSpan(new ForegroundColorSpan(Color.RED), 0, 5, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
    }
}
```

<img src="Spannable.assets/img.png" alt="img" style="zoom: 33%;" />

* 활용 Code

  * 글자색 지정

  ```java
  Spannable span = (Spannable) tv_sample.getText();
          span.setSpan(new ForegroundColorSpan(Color.RED), 0, 5, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
  ```

  * 배경색 지정

  ```java
  Spannable span = (Spannable) tv_sample.getText();
          span.setSpan(new ForegroundColorSpan(Color.WHITE), 0, 5, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
          span.setSpan(new BackgroundColorSpan(Color.RED), 0, 5, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
  ```

  * 글자크기 고정

  ```java
  Spannable span = (Spannable) tv_sample.getText();
          span.setSpan(new ForegroundColorSpan(Color.WHITE), 0, 5, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
          span.setSpan(new BackgroundColorSpan(Color.RED), 0, 5, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
          span.setSpan(new AbsoluteSizeSpan(100), 0, 5, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
  ```

  * 주변 글자 크기에 따라 크기 변경

  ```java
  Spannable span = (Spannable) tv_sample.getText();
          span.setSpan(new ForegroundColorSpan(Color.WHITE), 0, 5, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
          span.setSpan(new BackgroundColorSpan(Color.RED), 0, 5, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
          span.setSpan(new RelativeSizeSpan(1.5f), 0, 5, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
  ```

  * 밑줄 표시

  ```java
  Spannable span = (Spannable) tv_sample.getText();
          span.setSpan(new ForegroundColorSpan(Color.WHITE), 0, 5, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
          span.setSpan(new BackgroundColorSpan(Color.RED), 0, 5, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
          span.setSpan(new RelativeSizeSpan(1.5f), 0, 5, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
          span.setSpan(new UnderlineSpan(), 0, 5, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
  ```

  * 글자 사이 이미지 적용

  ```java
  Drawable image = getResources().getDrawable(R.drawable.best);
          image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
  
          Spannable span = (Spannable) tv_sample.getText();
          span.setSpan(new ImageSpan(image), 0, 5, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
  ```

  * URL링크 바로가기

  ```java
  Spannable span = (Spannable) tv_sample.getText();
          String url = "https://lakue.tistory.com/4";
          span.setSpan(new URLSpan(url), 6, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
          tv_sample.setMovementMethod(LinkMovementMethod.getInstance());
  ```

  



* Code used in the project

```java
Spannable resultText = new Spanner()
          .append("회원님의 아이디는\n", Spans.sizeDP(16), Spans.foreground(mActivity.getColor(R.color.color_666666)))
          .append("id@email.com\n", Spans.sizeDP(16), Spans.bold(), Spans.foreground(mActivity.getColor(R.color.colorPrimary)))
          .append("입니다.", Spans.sizeDP(16), Spans.foreground(mActivity.getColor(R.color.color_666666)));

 mNoInfoTextView.setText(resultText);
```



## Reference

[https://lakue.tistory.com/4](https://lakue.tistory.com/4)