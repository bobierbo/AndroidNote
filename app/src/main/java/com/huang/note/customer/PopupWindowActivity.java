package com.huang.note.customer;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.PopupWindow;

import com.huang.note.R;

public class PopupWindowActivity extends Activity implements OnClickListener {
    private PopupWindow popupWindow;
    private View popupView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);

        initView();
    }

    private void initView() {
        findViewById(R.id.bt_show_popupwindow).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        
        showPopupWindow();
        
//        popupView = getLayoutInflater().inflate(R.layout.popup_window_layout, null);
//        if(popupWindow == null) {
//            popupWindow = new PopupWindow(popupView, 100, 300);
//            popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_launcher));
//            popupWindow.setFocusable(false);
//            popupWindow.setOutsideTouchable(false);
//            popupWindow.showAsDropDown(v);
//        } else {
//            if(!popupWindow.isShowing()) {
//                popupWindow.showAsDropDown(v);
//            } else {
//                popupWindow.dismiss();
//            }
//        }

//        DreamPopupWindow dreamPopupWindow = new DreamPopupWindow(this);
//        dreamPopupWindow.setContentView(popupView);
//        dreamPopupWindow.show(v);
    }

    private void showPopupWindow() {

        if (popupWindow == null) {
            popupView = getLayoutInflater().inflate(R.layout.popup_window_layout, null);
            // 参数2,3：指明popupwindow的宽度和高度
            popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager
                    .LayoutParams.WRAP_CONTENT);

            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {

                }
            });

            // 设置背景图片， 必须设置，不然动画没作用
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            popupWindow.setFocusable(true);

            // 设置点击popupwindow外屏幕其它地方消失
            popupWindow.setOutsideTouchable(true);
        }

        // 在点击之后设置popupwindow的销毁
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        }

        // 平移动画相对于手机屏幕的底部开始，X轴不变，Y轴从1变0
        TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0, Animation
                .RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);
        animation.setInterpolator(new AccelerateInterpolator());
        animation.setDuration(1000);

        // 设置popupWindow的显示位置，此处是在手机屏幕底部且水平居中的位置
        popupWindow.showAtLocation(getWindow().getDecorView().getRootView(), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        popupView.startAnimation(animation);
    }
}
