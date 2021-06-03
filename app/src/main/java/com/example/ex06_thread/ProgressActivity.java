package com.example.ex06_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ProgressActivity extends AppCompatActivity implements Runnable {
    ProgressBar pb;
    TextView txtRate;
    Button btnStart;
    int value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        pb=findViewById(R.id.pb);
        txtRate=findViewById(R.id.txtRate);
        btnStart=findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Thread th= new Thread(ProgressActivity.this);
                th.start();
            }
        });
    }

    @Override
    public void run() {
            for(int i =1;i<=100;i++){
                value =i;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pb.setProgress(value);
                        txtRate.setText("진행률:" +value + "%");
                    }
                });
                try {
                    Thread.sleep(100);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(ProgressActivity.this,"작업이 완료되었습니다",Toast.LENGTH_LONG).show();
                }
            });
    }
}
