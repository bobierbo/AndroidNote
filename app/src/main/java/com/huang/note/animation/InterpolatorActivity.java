package com.huang.note.animation;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

import com.huang.note.R;

/**
 * 加速度插值器
 */
public class InterpolatorActivity extends Activity implements OnClickListener {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolator);
        initView();
    }

    private void initView() {
        imageView = (ImageView) findViewById(R.id.iv);
        imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // accelerateInterpolatorAnimation();
        // decelerateInterpolatorAnimation();
        // accelerateDecelerateInterpolatorAnimation();
        // overshootInterpolatorAnimation();
//      // cycleInterpolatorAnimation();
        // bounceInterpolatorAnimation();
        // anticipateInterpolatorAnimation();
         anticipateOvershootInterpolatorAnimation();
    }

    /**
     * 加速插值器
     */
    protected void accelerateInterpolatorAnimation() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "translationX", 0.0f, 200.0f);
        objectAnimator.setDuration(2000);
        objectAnimator.setInterpolator(new AccelerateInterpolator());
        objectAnimator.start();
    }

    /**
     * 减速插值器
     */
    protected void decelerateInterpolatorAnimation() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "translationX", 0.0f, 200.0f);
        objectAnimator.setDuration(2000);
        objectAnimator.setInterpolator(new DecelerateInterpolator());
        objectAnimator.start();
    }

    /**
     * 加速－减速插值器
     */
    protected void accelerateDecelerateInterpolatorAnimation() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "translationX", 0.0f, 200.0f);
        objectAnimator.setDuration(2000);
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator.start();
    }

    /**
     * 向前甩一定值后再回到原来位置(回弹插值器)
     */
    protected void overshootInterpolatorAnimation() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "translationX", 0.0f, 200.0f);
        objectAnimator.setDuration(2000);
        objectAnimator.setInterpolator(new OvershootInterpolator(5));
        objectAnimator.start();
    }

    /**
     * 正弦周期变化插值器
     */
    protected void cycleInterpolatorAnimation() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "translationY", 0.0f, 300.0f);
        objectAnimator.setDuration(2000);
        objectAnimator.setInterpolator(new CycleInterpolator(1));
        objectAnimator.start();
    }

    /**
     * 弹跳插值器
     */
    protected void bounceInterpolatorAnimation() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "translationY", 0.0f, 300.0f);
        objectAnimator.setDuration(2000);
        objectAnimator.setInterpolator(new BounceInterpolator());
        objectAnimator.start();
    }

    /**
     * 回荡秋千插值器
     */
    protected void anticipateInterpolatorAnimation() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "translationY", 0.0f, 300.0f);
        objectAnimator.setDuration(2000);
        objectAnimator.setInterpolator(new AnticipateInterpolator());
        objectAnimator.start();
    }

    /**
     *  心跳效果
     */
    protected void anticipateOvershootInterpolatorAnimation() {
//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "translationY", 0.0f, 300.0f);
//        objectAnimator.setDuration(2000);
//        objectAnimator.setInterpolator(new AnticipateOvershootInterpolator());
//        objectAnimator.start();

        PropertyValuesHolder scaleXAnim = PropertyValuesHolder.ofFloat("scaleX", 0.5f, 1.0f);
        PropertyValuesHolder scaleYAnim = PropertyValuesHolder.ofFloat("scaleY", 0.5f, 1.0f);
        
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(imageView, scaleXAnim, scaleYAnim);
        objectAnimator.setInterpolator(new AnticipateOvershootInterpolator(5));
        objectAnimator.setDuration(2000);
        objectAnimator.start();
    }

}
