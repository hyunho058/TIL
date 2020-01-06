package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CalActivity extends AppCompatActivity {
    private final String TAG = "CalActivity";

    TextView tv_text1;
    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate((savedInstanceState));
        setContentView(R.layout.activity_cal);

        Intent recvIntent = getIntent();
        String text1 = recvIntent.getStringExtra("text1");


        tv_text1 = (TextView) findViewById(R.id.tv_text1);
        tv_text1.setText(text1);

        btn_back = (Button) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(mClickListener);
    }

    View.OnClickListener mClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            switch(v.getId()){
                case R.id.btn_back:
                    finish();

                    break;
            }
        }
    };



}
