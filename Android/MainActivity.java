package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView tv_time;

    Button btn_hello;
    Button btn_time;
    Button btn_google;
    Button btn_naver;
    Button btn_move;
    Button btn_Rtime;
    Button btn_cal;

    EditText et_text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_time.setText(new Date().toGMTString());

        et_text1 = (EditText) findViewById(R.id.et_text1) ;

        /*헬로우월드 토스트 출력*/
        btn_hello = (Button) findViewById(R.id.btn_hello);
        btn_hello.setOnClickListener(mClickListener);

        btn_time = (Button) findViewById(R.id.btn_time);
        btn_time.setOnClickListener(mClickListener);

        btn_google = (Button) findViewById(R.id.btn_google);
        btn_google.setOnClickListener(mClickListener);

        btn_naver = (Button) findViewById(R.id.btn_naver);
        btn_naver.setOnClickListener(mClickListener);

        btn_move = (Button) findViewById(R.id.btn_move);
        btn_move.setOnClickListener(mClickListener);

        btn_Rtime = (Button)findViewById(R.id.btn_Rtime);
        btn_Rtime.setOnClickListener(mClickListener);

        btn_cal = (Button)findViewById(R.id.btn_cal);
        btn_cal.setOnClickListener(mClickListener);

    }

    View.OnClickListener mClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
           // Log.v(Tag, "[mClickListener] view getId=" + v.getId());

            switch (v.getId()) {
                case R.id.btn_hello:
                    try {
                        Toast.makeText(getApplicationContext(), "Hello Android!", Toast.LENGTH_SHORT).show();
                        Log.d("MainActivity", "1234");
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        Log.d("MainActivity", "finally");
                    }
                    break;

                case R.id.btn_time:
                    try {
                        tv_time.setText(new Date().toGMTString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        Log.d("MainActivity", "finally");
                    }
                    break;

                case R.id.btn_Rtime:
                    try{
                        Toast.makeText(getApplicationContext(), "Time!", Toast.LENGTH_SHORT).show();

                    }catch (Exception e){
                        e.printStackTrace();
                    }finally{
                        Log.d("MainActivity","finally");
                    }
                    break;

                case R.id.btn_naver:
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
                    startActivity(myIntent);
                    break;

                case R.id.btn_google:
                    myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://google.com"));
                    startActivity(myIntent);
                    break;

                case R.id.btn_move:
                    String text1 = et_text1.getText().toString();

                    if(text1.isEmpty()){
                        showToastShort("이름을 입력해주세요.");
                        return;
                    }
                    Intent moveIntent = new Intent(getApplicationContext(),SubActivity.class);
                    moveIntent.putExtra("text1",text1);
                    startActivity(moveIntent);
                    break;

                case R.id.btn_cal:
                    Intent calIntent = new Intent(getApplicationContext(),CalActivity.class);
                    startActivity(calIntent);
                    break;
            }
        }
    };

    public void showToast(String msg, int duration){
        if(!msg.isEmpty()){
            Toast.makeText(getApplicationContext(),msg,duration).show();
        }
    }

    public void showToastLong(String msg){
        if(!msg.isEmpty()){
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
        }
    }

    public void showToastShort(String msg){
        if(!msg.isEmpty()){
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }
}
