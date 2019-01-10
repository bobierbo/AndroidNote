package com.huang.note.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.huang.note.R;

public class TweenAnimationActivity extends Activity implements OnClickListener {
    private ImageView animationIV;
    private Animation translateAnimation;

    private boolean isRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_tween_animation);
        initView();
    }

    private void initView() {
        animationIV = (ImageView) findViewById(R.id.iv_animation);
        findViewById(R.id.bt_alpha).setOnClickListener(this);
        findViewById(R.id.bt_scale).setOnClickListener(this);
        findViewById(R.id.bt_tanslate).setOnClickListener(this);
        findViewById(R.id.bt_rotate).setOnClickListener(this);
        findViewById(R.id.bt_set).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_alpha:
                startAlphaAnimation();
                break;
            case R.id.bt_scale:
                startScaleAniamtion();
                break;
            case R.id.bt_tanslate:
                startTranslateAnimation();
                break;
            case R.id.bt_rotate:
                startRotateAnimation();
                break;
            case R.id.bt_set:
                startAnimationSet();
                break;

            default:
                break;
        }
    }

    private void startAlphaAnimation() {
        Animation alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.tween_alpha_animation);
        animationIV.startAnimation(alphaAnimation);
    }

    private void startScaleAniamtion() {
        animationIV.startAnimation(AnimationUtils.loadAnimation(this, R.anim.tween_scale_animation));
    }

    private void startTranslateAnimation() {
        if (translateAnimation == null) {
            translateAnimation = AnimationUtils.loadAnimation(this, R.anim.tween_translate_animation);
            translateAnimation.setAnimationListener(new AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {
                    isRunning = true;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    isRunning = false;
                }
            });
        }
        if (!isRunning) {
            animationIV.startAnimation(translateAnimation);
        }
    }

    private void startRotateAnimation() {
        animationIV.startAnimation(AnimationUtils.loadAnimation(this, R.anim.tween_rotate_animation));
    }

    private void startAnimationSet() {
        animationIV.startAnimation(AnimationUtils.loadAnimation(this, R.anim.tween_set_animation));
    }

}
