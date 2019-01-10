package com.huang.note.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.huang.note.R;

public class GifActivity extends Activity {

    GifSurfaceView gifSurfaceView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif);

        gifSurfaceView = (GifSurfaceView) findViewById(R.id.gsv);
        gifSurfaceView.setPath("mn.gif");
        gifSurfaceView.setZoom(2);
        
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gifSurfaceView.start();
            }
        });

        findViewById(R.id.btn_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gifSurfaceView.stop();
            }
        });
    }

}
