package com.huang.note.customer;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.IBinder;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

public class DreamPopupWindow {

    private Context context;
    private WindowManager windowManager;

    private int mWidth;

    private int mHeight;

    private View contentView;

    private Drawable mBackground;

    public DreamPopupWindow(Context context) {
        this.context = context;

        windowManager = (WindowManager) this.context.getSystemService(Context.WINDOW_SERVICE);
    }

    public void setContentView(View v) {
        this.contentView = v;
    }

    private LayoutParams createPopupLayout(IBinder token) {
        LayoutParams p = new LayoutParams();
        p.gravity = Gravity.START | Gravity.TOP;
        p.width = mWidth;
        p.height = mHeight;
        if (mBackground != null) {
            p.format = mBackground.getOpacity();
        } else {
            p.format = PixelFormat.TRANSLUCENT;
        }
        // p.flags = computeFlags(p.flags);
        // p.type = mWindowLayoutType;
        p.token = token;
        p.softInputMode = LayoutParams.SOFT_INPUT_STATE_UNCHANGED;
        p.setTitle("PopupWindow:" + Integer.toHexString(hashCode()));

        return p;
    }

    public void show(View v) {


        int[] location = new int[2];
        v.getLocationInWindow(location);

        WindowManager wmManager = (WindowManager) this.context.getSystemService(Context.WINDOW_SERVICE);
        // 2.设置WindowManager.LayoutParams参数
        LayoutParams wmParams = new LayoutParams();
        wmParams.type = LayoutParams.TYPE_PHONE; // 设置window type
        wmParams.format = PixelFormat.RGBA_8888; // 设置图片格式，效果为背景透明
        wmParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL | LayoutParams.FLAG_NOT_FOCUSABLE | LayoutParams
                .FLAG_NOT_TOUCHABLE;
        wmParams.gravity = Gravity.START | Gravity.TOP; // 调整悬浮窗口至右侧中间
        wmParams.x = location[0];// 以屏幕左上角为原点，设置x、y初始值
        wmParams.y = location[1];
        wmParams.width = LayoutParams.WRAP_CONTENT;// 设置悬浮窗口长宽数据
        wmParams.height = LayoutParams.WRAP_CONTENT;
        // 3.添加view到屏幕
        wmManager.addView(contentView, wmParams);
        // // 4.从屏幕上删除view
        // wmManager.removeView(contentView);

    }

    public void dismiss() {
        windowManager.removeViewImmediate(contentView);
    }

}
