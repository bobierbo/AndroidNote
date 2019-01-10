package com.huang.note.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.LinearLayout;

import com.huang.note.R;

/**
 * 属性动画应用实例
 */
public class CoolSafariActivity extends Activity implements OnClickListener {

    private LinearLayout showLayout;
    private LinearLayout hideLayout;

    private int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cool_safari);
        initView();
    }

    private void initView() {
        showLayout = (LinearLayout) findViewById(R.id.ll_show);
        hideLayout = (LinearLayout) findViewById(R.id.ll_hide);
        hideLayout.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

            // 监听我们的视图是非绘制完成
            // 已经绘制完成回调onGlobalLayout
            @Override
            public void onGlobalLayout() {
                height = hideLayout.getHeight();
                hideLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        findViewById(R.id.bt_show).setOnClickListener(this);
        findViewById(R.id.bt_hide).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_show:
                showLayout();
                break;
            case R.id.bt_hide:
                hideLayout();
                break;

            default:
                break;
        }
    }

    private void showLayout() {
        // 缩放动画
        ObjectAnimator showLayoutScaleXAnimator = ObjectAnimator.ofFloat(showLayout, "scaleX", 1.0f, 0.8f)
                .setDuration(300);
        ObjectAnimator showLayoutScaleYAnimator = ObjectAnimator.ofFloat(showLayout, "scaleY", 1.0f, 0.8f)
                .setDuration(300);

        // 透明度
        ObjectAnimator showLayoutAlphaAnimator = ObjectAnimator.ofFloat(showLayout, "alpha", 1.0f, 0.5f).setDuration
                (300);

        // 旋转
        // 顺时针旋转20度
        ObjectAnimator showLayoutClockwiseAnimator = ObjectAnimator.ofFloat(showLayout, "rotationX", 0, 20)
                .setDuration(200);
        ObjectAnimator showLayoutUnClockwiseAnimator = ObjectAnimator.ofFloat(showLayout, "rotationX", 20, 0)
                .setDuration(200);
        showLayoutUnClockwiseAnimator.setStartDelay(200);

        // 平移
        ObjectAnimator showLayoutTranslationYAnimator = ObjectAnimator.ofFloat(showLayout, "translationY", 0f, -30f)
                .setDuration(300);
        ObjectAnimator hideLayoutTranslationYAnimator = ObjectAnimator.ofFloat(hideLayout, "translationY", height, 0)
                .setDuration(300);
        hideLayoutTranslationYAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                hideLayout.setVisibility(View.VISIBLE);
            }
        });

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(showLayoutScaleXAnimator, showLayoutScaleYAnimator, showLayoutAlphaAnimator, 
                showLayoutClockwiseAnimator, showLayoutUnClockwiseAnimator, showLayoutTranslationYAnimator, 
                hideLayoutTranslationYAnimator);
        animatorSet.start();
    }

    private void hideLayout() {
        // 留给同学们搞定隐藏功能
        // 缩放动画
        ObjectAnimator showLayoutScaleXAnimator = ObjectAnimator.ofFloat(showLayout, "scaleX", 0.8f, 1.0f)
                .setDuration(300);
        ObjectAnimator showLayoutScaleYAnimator = ObjectAnimator.ofFloat(showLayout, "scaleY", 0.8f, 1.0f)
                .setDuration(300);

        // 透明度
        ObjectAnimator showLayoutAlphaAnimator = ObjectAnimator.ofFloat(showLayout, "alpha", 0.5f, 1.0f).setDuration
                (300);

        // 旋转
        // 顺时针旋转20度
        ObjectAnimator showLayoutClockwiseAnimator = ObjectAnimator.ofFloat(showLayout, "rotationX", 0, -20)
                .setDuration(200);
        ObjectAnimator showLayoutUnClockwiseAnimator = ObjectAnimator.ofFloat(showLayout, "rotationX", -20, 0)
                .setDuration(200);
        showLayoutUnClockwiseAnimator.setStartDelay(200);

        // 平移
        ObjectAnimator showLayoutTranslationYAnimator = ObjectAnimator.ofFloat(showLayout, "translationY", 0f, 30f)
                .setDuration(300);
        ObjectAnimator hideLayoutTranslationYAnimator = ObjectAnimator.ofFloat(hideLayout, "translationY", 0, height)
                .setDuration(300);
        hideLayoutTranslationYAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                hideLayout.setVisibility(View.GONE);
            }
        });

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(showLayoutScaleXAnimator, showLayoutScaleYAnimator, showLayoutAlphaAnimator,
                showLayoutClockwiseAnimator, showLayoutUnClockwiseAnimator, showLayoutTranslationYAnimator,
                hideLayoutTranslationYAnimator);
        animatorSet.start();
    }

}
