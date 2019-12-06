package com.xacheliangroup.check.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;


/**
 * author:yz
 * data: 2018/12/17,16:01
 */
public class LogoSplashActivity extends Activity {

    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }
        countDownTimer = new CountDownTimer(1 * 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                AdvertActivity.launch(LogoSplashActivity.this, LogoSplashActivity.this);
                finish();
            }
        };
        countDownTimer.start();

    }
    public void toCancelTimer(){
        if(countDownTimer!=null){
            countDownTimer.cancel();
            countDownTimer=null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        toCancelTimer();
    }
}
