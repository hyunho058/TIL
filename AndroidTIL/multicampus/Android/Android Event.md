# Android Event

## ImageView

* ImageVIew 이미지 적용및 전환
  * ImageView의 Id값을 받아오고 setImageResource()함수를 이용하여 이미지 변경

```java
public class EvnetActivity extends AppCompatActivity {
    ImageView iv_01;
    Button btn_imagechange;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evnet);

        iv_01 = findViewById(R.id.iv_01);
        btn_imagechange = findViewById(R.id.btn_imagechange);
        btn_imagechange.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                iv_01.setImageResource(R.drawable.android_image2);
            }
        });
    }
}
```

## Swipe

* View.OnTouchListener 활용
  * MotionEvent event class  => 화면의 좌표를 받을수 있는 class
    * ACTION_DOWN - 처음 눌렀을때
    * ACTION_UP - 이동할떄
    * ACTION_MOVIE - 누른걸 뗏을떄

```java
public class SwipeActivity extends AppCompatActivity {
    LinearLayout linealLayout;
    double x1, x2;
    String TAG = "SwipeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);

        linealLayout = findViewById(R.id.linealLayout);
        linealLayout.setOnTouchListener(onTouchListener);
    }

    View.OnTouchListener onTouchListener = new View.OnTouchListener(){
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Log.v("SwipeActivity","onTouch");

            if(event.getAction() ==MotionEvent.ACTION_DOWN){
                x1 = event.getX();
            }
            if(event.getAction() ==MotionEvent.ACTION_UP){
                x2 = event.getX();
                if(x1<x2){
                    Log.v(TAG,"onTouchListener_Right");
                }else{
                    Log.v(TAG,"onTouchListener_Left");
                }
            }
            return true;
        }
    };
}
```

## AlertDialog

* 알림창을 용하여 데이터 전달

```java
  case R.id.btnSend:
                    final EditText editText = new EditText(MainActivity.this);
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("ActivityDataSend");
                    builder.setMessage("Activity에 전달할 내용");
                    builder.setView(editText);
                    builder.setPositiveButton("send", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MainActivity.this, SendMassageActivity.class);
                            intent.putExtra("sendMsg",editText.getText().toString());
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                    break;
```

## Spinner&ArrayAdapter

* ArrayList에 저장된 객체 Data 를DropDown 형식으로 List화 해서 보여줌

```xml
    <Spinner
        android:id="@+id/spinner_01"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>
```

```java
 arrayList = new arrayList<String>();
        arrayList.add("포도");
        arrayList.add("딸기");
        arrayList.add("사과");
        arrayList.add("거봉");

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_dropdown_item_1line,arrayList);
        spinner_01.setAdapter(arrayAdapter);
```



## onActivityResult

* 데이터 전달 MainActivity => DataFromActivity => MaintActivity
  * 위 상황시 sub 에서 Main으로 돌아올때 Data를 전달한다
* MainActivity

```java
                case R.id.btnPull:
                    Intent intent5 = new Intent(MainActivity.this, DataFromActivity.class);
                    startActivityForResult(intent5,1);
                    break;
            }
        }
    };
//requestCode == 1 && resultCode ==7000 Code 값으로 데이터 식별
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode ==7000){
            Toast.makeText(this, data.getStringExtra("resultValue"),Toast.LENGTH_SHORT).show();
            Log.v("MainActivity", "onActivityResult======="+data.getStringExtra("resultValue"));
        }
    }
```

* DataFromActivity

```java
public class DataFromActivity extends AppCompatActivity {
    Spinner spinner_01;
    Button btnSendData;
    ArrayList<String> arrayList;
    String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_from);

        spinner_01 = findViewById(R.id.spinner_01);
        btnSendData = findViewById(R.id.btnSendData);
        btnSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("resultValue", result);
                setResult(7000,intent);
                finish();
            }
        });

        arrayList = new ArrayList<String>();
        arrayList.add("포도");
        arrayList.add("딸기");
        arrayList.add("사과");
        arrayList.add("거봉");

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_dropdown_item_1line,arrayList);
        spinner_01.setAdapter(arrayAdapter);

        spinner_01.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                result = arrayList.get(position);
                Log.v("DataFromActivity","arrayList.get(position)=========="+arrayList.get(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
```

