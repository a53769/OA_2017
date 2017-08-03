package com.example.shixi_a.myapplication.widget;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by a5376 on 2017/8/2.
 */

public class CountDownTimerUtils extends CountDownTimer {

    TextView mTextView;
    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public CountDownTimerUtils(TextView textView, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        mTextView = textView;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        mTextView.setClickable(false); //设置不可点击
        mTextView.setText("获取中" + millisUntilFinished / 1000 + "s");  //设置倒计时时间

    }

    @Override
    public void onFinish() {
        mTextView.setText("获取验证码");
        mTextView.setClickable(true);//重新获得点击
    }
}
