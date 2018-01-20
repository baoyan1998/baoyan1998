package com.github.baoyan1998.zhihu.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.AndroidException;
import android.widget.ImageView;

import com.github.baoyan1998.zhihu.R;

public class SplashActivity extends AppCompatActivity {

    private ImageView mIvSplashBg;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mIvSplashBg = findViewById(R.id.iv_splash_bg);
        mIvSplashBg.animate().scaleX(1.2f).scaleY(1.2f).setDuration(2000);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(
                        SplashActivity.this,MainActivity.class);
                                      startActivity(intent);
                                      SplashActivity.this.finish();
                                      overridePendingTransition(android.R.anim.fade_in,
                                              android.R.anim.fade_out);

            }
        },2000);

    }
}
