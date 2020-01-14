# Android

## Calculator Project



* MainActivity_java

```java
package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    final String TAG = "CALCULATOR";

    int buttonResourceIds [] = {
            R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3,
            R.id.btn_4, R.id.btn_5, R.id.btn_6,
            R.id.btn_7, R.id.btn_8, R.id.btn_9,
            R.id.btn_plus,R.id.btn_minus,R.id.btn_multi,R.id.btn_div
    };

    EditText et_result;
    EditText et_result_1;
    Button btn_clear;
    Button btn_enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_result = findViewById(R.id.et_result);

        et_result_1 = findViewById(R.id.et_result_1);

        btn_clear = (Button) findViewById(R.id.btn_clear);
        btn_clear.setOnClickListener(mClickListener);

        btn_enter = (Button) findViewById(R.id.btn_enter);
        btn_enter.setOnClickListener((mClickListener));

        for(int rId : buttonResourceIds) {
            final Button btn = (Button) findViewById(rId);
            Log.d(TAG, "Button=" + btn);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    et_result.setText(String.valueOf(et_result.getText().toString() + btn.getText().toString()));
                }
            });
        }
    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                //clear
                case R.id.btn_clear:
                    et_result.setText("");
                    et_result_1.setText("");
                    break;
                case R.id.btn_enter:
                    String rowData = et_result.getText().toString();
                    Log.v(TAG, "[btn_enter] rowData=" + rowData);
                    String [] operator = new String[15];

                    String [] data = rowData.split("X|/|-|\\+");

                    int index = 0;

                    for(char c : rowData.toCharArray()) {
                        switch (c) {
                            case 1:
                                Log.d(TAG, "num=" + c);
                                break;
                            case 'X' :
                                Log.d(TAG, "X operator" + c);

                                int multi = 0;
                                int mumtiTemp = 0;
                                for(String r : data) {
                                    Log.v(TAG, "Number=" + r);
                                    multi = Integer.parseInt(r);
                                    mumtiTemp = multi;
                                    Log.d(TAG, "multi=" + multi);
                                }
                                multi = multi * mumtiTemp;
                                Log.d(TAG, "multi=" + multi);
                                et_result_1.setText(Integer.toString(multi));
                                break;
                            case '/' :
                                Log.d(TAG, "/ operator" + c);

                                int div = 0;
                                for(String r : data) {
                                    Log.v(TAG, "Number=" + r);
                                    div = div / Integer.parseInt(r);
                                    Log.d(TAG, "div=" + div);
                                }
                                et_result_1.setText(Integer.toString(div));
                                break;
                            case '+' :
                                Log.d(TAG, "+ operator" + c);

                                int sum = 0;
                                for(String r : data) {
                                    Log.v(TAG, "Number=" + r);
                                    sum = sum + Integer.parseInt(r);
                                    Log.d(TAG, "sum=" + sum);
                                }
                                et_result_1.setText(Integer.toString(sum));

//                                et_result_1.setText(et_result.getText());
                                break;
                            case '-' :
                                Log.d(TAG, "- operator" + c);

                                int sub = 0;
                                for(String r : data) {
                                    Log.v(TAG, "Number=" + r);
                                    sub = sub - Integer.parseInt(r);
                                    Log.d(TAG, "sub=" + sub);
                                }
                                et_result_1.setText(Integer.toString(sub));
                                operator[index] = String.valueOf(c);
                                index++;
                                break;
                        }
                    }
//                    int sum = 0;
//                    for(String r : data) {
//                        Log.v(TAG, "Number=" + r);
//                        sum = sum + Integer.parseInt(r);
//                        Log.d(TAG, "sum=" + sum);
//                    }
//                    et_result_1.setText(Integer.toString(sum));

//                    et_result_1.setText(Integer.parseInt(et_result.getText().toString()));
//                    et_result_1.setText(et_result.getText());
                    break;
            }
        }
    };
}

```

```java
package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    final String TAG = "CALCULATOR";

    int buttonResourceIds [] = {
            R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3,
            R.id.btn_4, R.id.btn_5, R.id.btn_6,
            R.id.btn_7, R.id.btn_8, R.id.btn_9,
            R.id.btn_plus,R.id.btn_minus,R.id.btn_multi,R.id.btn_div
    };

    EditText et_result;
    EditText et_result_1;
    Button btn_clear;
    Button btn_enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_result = findViewById(R.id.et_result);

        et_result_1 = findViewById(R.id.et_result_1);

        btn_clear = (Button) findViewById(R.id.btn_clear);
        btn_clear.setOnClickListener(mClickListener);

        btn_enter = (Button) findViewById(R.id.btn_enter);
        btn_enter.setOnClickListener((mClickListener));

        for(int rId : buttonResourceIds) {
            final Button btn = (Button) findViewById(rId);
            Log.d(TAG, "Button=" + btn);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    et_result.setText(String.valueOf(et_result.getText().toString() + btn.getText().toString()));
                }
            });
        }
    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                //clear
                case R.id.btn_clear:
                    et_result.setText("");
                    et_result_1.setText("");
                    break;
                case R.id.btn_enter:
                    //문자열로 받은 숫자와 연산자롤 각각 linkedList 에 저장
                    LinkedList<Integer> numList = new  LinkedList<Integer>();
                    LinkedList<Character> opList = new LinkedList<Character>();

                    String rowData = et_result.getText().toString();
                    Log.v(TAG, "[btn_enter] rowData=" + rowData);
                    String num ="";

                    for(int i = 0; i<rowData.length(); i++){
                        char c = rowData.charAt(i);

                        if(c == '+' || c == '-' || c == '*' || c == '/'){
                            numList.add(Integer.parseInt(num));
                            opList.add(c);
                            Log.v(TAG, "[btn_enter] op=" + c);
                            num ="";
                            continue;
                        }
                        num += c;
                        Log.v(TAG, "[btn_enter] num= " + num);
                    }
                    numList.add(Integer.parseInt(num));
                    Log.v(TAG, "[btn_enter] numList=" + numList);
                    Log.v(TAG, "[btn_enter] opList=" + opList);

                    while (!opList.isEmpty()){
                        Log.v(TAG, "[btn_enter] while opList=" + opList);
                        int prevNum = numList.poll();
                        Log.v(TAG, "[btn_enter] while prevNum=" + prevNum);
                        int nextNum = numList.poll();
                        Log.v(TAG, "[btn_enter] while nextNum=" + nextNum);
                        char op = opList.poll();

                        if(op == '+') {
                            numList.addFirst(prevNum + nextNum);
                            Log.v(TAG, "[btn_enter] while numList=" + numList);
                        } else if(op == '-') {
                            numList.addFirst(prevNum - nextNum);
                        } else if(op == '*') {
                            numList.addFirst(prevNum * nextNum);
                        } else if(op == '/') {
                            numList.addFirst(prevNum / nextNum);
                        }
                    }

                    et_result_1.setText(numList.poll());
                    Log.v(TAG, "[btn_enter] final="  );
                    break;
            }
        }
    };
}
```

