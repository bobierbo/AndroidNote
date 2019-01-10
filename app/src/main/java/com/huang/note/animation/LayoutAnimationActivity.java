package com.huang.note.animation;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import com.huang.note.R;

/**
 * viewGroup增加或者移除view产生的动画
 */
public class LayoutAnimationActivity extends Activity implements OnClickListener, OnCheckedChangeListener {

    private CheckBox appearingCB;
    private CheckBox disappearingCB;
    private CheckBox changeAppearingCB;
    private CheckBox changeDisappearingCB;
    private GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_animation);
        initView();
    }

    private void initView() {
        LinearLayout rootLayout = (LinearLayout) findViewById(R.id.ll_root_view);
        findViewById(R.id.bt_add).setOnClickListener(this);
        appearingCB = (CheckBox) findViewById(R.id.cb_appearing);
        disappearingCB = (CheckBox) findViewById(R.id.cb_disappearing);
        changeAppearingCB = (CheckBox) findViewById(R.id.cb_change_appearing);
        changeDisappearingCB = (CheckBox) findViewById(R.id.cb_change_disappearing);

        appearingCB.setOnCheckedChangeListener(this);
        disappearingCB.setOnCheckedChangeListener(this);
        changeAppearingCB.setOnCheckedChangeListener(this);
        changeDisappearingCB.setOnCheckedChangeListener(this);

        gridLayout = new GridLayout(this);
        // 列
        gridLayout.setColumnCount(4);
        // 行
        gridLayout.setRowCount(5);

        rootLayout.addView(gridLayout);
        addLayoutTransition();
    }

    private int index = 1;

    @Override
    public void onClick(View v) {
        Button button = new Button(this);
        button.setText(String.valueOf(index));
        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                gridLayout.removeView(v);
            }
        });
        gridLayout.addView(button, Math.min(0, gridLayout.getChildCount()));
        index++;
    }
    
    public void addLayoutTransition(){
        LayoutTransition layoutTransition = new LayoutTransition();
        // LayoutTransition.APPEARING表示：当一个新的view在viewgroup中出现时，对当前新增的子view设置动画
        layoutTransition.setAnimator(LayoutTransition.APPEARING, appearingCB.isChecked() ? layoutTransition
                .getAnimator(LayoutTransition.APPEARING) : null);
        layoutTransition.setAnimator(LayoutTransition.APPEARING, appearingCB.isChecked() ? ObjectAnimator.ofFloat
                (null, "scaleX", 0.0f, 1.0f) : null);
        // LayoutTransition.APPEARING表示：当一个子view在viewgroup中消失时，对当前消失的子view设置动画
        layoutTransition.setAnimator(LayoutTransition.DISAPPEARING, disappearingCB.isChecked() ? layoutTransition
                .getAnimator(LayoutTransition.DISAPPEARING) : null);
        // LayoutTransition.APPEARING表示：当一个新的子view在viewgroup中出现时，
        // 这个时候新的子view对其他的view的位置造成了影响，这里就是对被影响的view设置动画
        layoutTransition.setAnimator(LayoutTransition.CHANGE_APPEARING, changeAppearingCB.isChecked() ?
                layoutTransition.getAnimator(LayoutTransition.CHANGE_APPEARING) : null);
        // LayoutTransition.APPEARING表示：当一个新的子view在viewgroup中消失时，
        // 这个时候新的子view对其他的view的位置造成了影响，这里就是对被影响的view设置动画
        layoutTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, changeDisappearingCB.isChecked() ? layoutTransition.getAnimator(LayoutTransition.CHANGE_DISAPPEARING) : null);

        gridLayout.setLayoutTransition(layoutTransition);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        
    }

}
