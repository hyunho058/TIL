# android Activity

* App을 구성하고 있는 화면 지칭
* 화면을 표현하고 관리하기 위한 calass
* xml에 여러가지 widget을 넣어서 사용자component를 표현 한다

## LinearLayout



## 이벤트처리

* 사용자&시스템에 의해 발생되는 모든것
* java
  * Event Delegation Model을 이용해서 event처리.
    1. Event Source객체 : 이벤트가 발생한 객체
    2. Event Handler객체 : 디벤트를 처리하는 객체
    3. Event 객체 : 발생된 이벤트에 대한 세부정보를 가지고있는 객체

### Button Event

```java
public class BUttonActivity extends AppCompatActivity {
    Button button_event;
    TextView tv_01;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

        tv_01 = findViewById(R.id.tv_01);

        //1. Event Source객체 얻어오기
        button_event = findViewById(R.id.button_event);
        //2.Event Handler객체 생성
//        MyEventHandler handler = new MyEventHandler(tv_01);
        button_event.setOnClickListener(mClickListner);
    }

    View.OnClickListener mClickListner = (new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            tv_01.setText("야아아아아아아아아");
        }
    });
}
//이벤트를 처리할 수 있는 특수한 능력을 가지ㅗㄱ 있는 리스너 객체
//특수한 interface를 구현한 클래스가 되어야한다
//class MyEventHandler implements View.OnClickListener{
//    TextView tv;
//
//    public MyEventHandler(TextView tv) {
//        this.tv = tv;
//    }
//
//    public MyEventHandler() {
//    }
//
//    @Override
//    public void onClick(View v) {
//        Log.v("BUttonActivity","onClick");
//        tv.setText("바뀐다아아아아");
//    }

```



## Activity 전환

* Intent
  * Explicit
  * Implicit

### Explicit방식

> 명시적 방식

* 실행할 component의 이름과 클래스명을 명시적으로 작성하여 싱행할떄

```java
Intent intent = new Intent();
ComponentName cname = new ComponentName("com.example.androidlectureexample",
                                        "com.example.androidlectureexample.LayoutActivity");
intent.setComponent(cname);
startActivity(intent);
```

### Implicit방식

> 암시적 방식

* 어떤 의도만으로 원하는 component를 싱핼 할때
  1. 특정 의도를 폼함하는 intent 객체 생성
  2. startActivity()호출
  3. Activity Manager는 해당 의도를 처리할 패키지 정보 요청
  4. 패키지 매니저는 요청한 데이터를 전달
  5. Activity Manager는 전달받은 정보에서 Activity실행

```
Intent intent = new Intent(MainActivity.this, LayoutActivity.class);
startActivity(intent);
```

