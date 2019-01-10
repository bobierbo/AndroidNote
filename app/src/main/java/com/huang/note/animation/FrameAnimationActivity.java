package com.huang.note.animation;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.huang.note.R;

public class FrameAnimationActivity extends Activity implements OnClickListener {

    private AnimationDrawable rocketAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_frame_animation);

        initView();
    }

    private void initView() {
        ImageView rocketImage = (ImageView) findViewById(R.id.iv_animation);
        rocketImage.setBackgroundResource(R.drawable.animation_frame);
        rocketAnimation = (AnimationDrawable) rocketImage.getBackground();

        findViewById(R.id.bt_start).setOnClickListener(this);

        rocketImage.post(new Runnable() {

            @Override
            public void run() {
                startAnimation();
            }
        });
    }

    // 提问：app启动的时候就让我们的帧动画执行

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_start:
                startAnimation();
                break;
            case R.id.bt_stop:
                stopAnimation();
                break;

            default:
                break;
        }
    }

    private void startAnimation() {
        if (!rocketAnimation.isRunning()) {
            rocketAnimation.start();
        }
    }

    private void stopAnimation() {
        if (rocketAnimation.isRunning()) {
            rocketAnimation.stop();
        }
    }

    /**
     * 当我们的window窗口焦点方式改变的时候调用该方法
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        // startAnimation();
    }

}
