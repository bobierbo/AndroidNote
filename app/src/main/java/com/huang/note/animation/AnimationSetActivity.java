package com.huang.note.animation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.huang.note.R;

/**
 * 补间动画实现activity跳转动画
 */
public class AnimationSetActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_set_animation);
        initView();
    }

    private void initView() {
        ImageView animationIV = (ImageView) findViewById(R.id.iv_animation);
        animationIV.startAnimation(AnimationUtils.loadAnimation(this, R.anim.tween_alpha_animation));

        ImageView topIV = (ImageView) findViewById(R.id.iv_top);
        topIV.startAnimation(AnimationUtils.loadAnimation(this, R.anim.tween_translate_top_animation));

        ImageView bottomIV = (ImageView) findViewById(R.id.iv_bottom);
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.tween_translate_bottom_animation);
        bottomIV.startAnimation(loadAnimation);

        loadAnimation.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(AnimationSetActivity.this, FrameAnimationActivity.class));
                //activity切换动画
//                overridePendingTransition(R.anim.activity_enter_animation, R.anim.activity_exit_animation);
            }
        });

    }
}
