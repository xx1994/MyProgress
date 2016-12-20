package com.xx.myviewdemo;

import android.animation.ObjectAnimator;
import android.app.DownloadManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private float i = 0.0f;
    private CircleProgressView circleProgressView;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            circleProgressView.setSchedule(i);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circleProgressView = (CircleProgressView) findViewById(R.id.progress);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while ((int) i != 100) {
                    i++;
                    handler.sendEmptyMessage(0);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

    }
}
