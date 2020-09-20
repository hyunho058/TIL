# Butter_Knife



> Java의 Annotation을 활용한 방법



* 의존성

  * Gradle - Project

  ```groovy
  dependencies{
      classpath 'com.jakewharton:butterknife-gradle-plugin:10.2.1'
  }
  ```

  * Gradle - Module

  ```groovy
  android{
      compileOptions {
          sourceCompatibility JavaVersion.VERSION_1_8
          targetCompatibility JavaVersion.VERSION_1_8
      }
  }
  
  dependencies{
  	implementation 'com.jakewharton:butterknife:10.2.1'
  	annotationProcessor 'com.jakewharton:butterknife-compiler:10.2.1'
  }
  ```

  

* 기존 Code

```java
public class MainActivity extends AppCompatActivity {

    TextRoundCornerProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = findViewById(R.id.text_round_corner_progress_bar);

        mProgressBar.setProgress(80);
        mProgressBar.setProgressText("80%");

    }
}
```



* Butter_Knife 적용한 Code

```java
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.text_round_corner_progress_bar)
    TextRoundCornerProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mProgressBar.setProgress(80);
        mProgressBar.setProgressText("80%");

    }
    
    @OnClick(R.id.text_round_corner_progress_bar)
    public void proGressBarTouched(){
        Log.v("MainActivity", "text_round_corner_progress_bar");
    }
}
```





## Reference

[https://gun0912.tistory.com/2](https://gun0912.tistory.com/2)

[https://lcw126.tistory.com/257](https://lcw126.tistory.com/257)