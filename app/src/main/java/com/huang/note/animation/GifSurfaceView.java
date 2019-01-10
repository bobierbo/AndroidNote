package com.huang.note.animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

import java.io.IOException;
import java.io.InputStream;

public class GifSurfaceView extends SurfaceView implements Callback {

    //缓存，相当于工具箱，从里面拿画册(Canvas)
    private SurfaceHolder holder;

    // gif图片路径
    private String path;
    //我们如何将GIF里面的一帧的画面解析出来，然后绘制在canvas上面？   
    // Move类（影片对象） 相当于画笔
    private Movie movie;

    // 放大倍数
    private int zoom = 1;

    // 执行动画
    private Handler handler = new Handler();

    // 线程
    private Runnable runnable = new Runnable() {

        @Override
        public void run() {
            // 获取画布(加锁)
            Canvas canvas = holder.lockCanvas();

            canvas.save();

            canvas.scale(zoom, zoom);

            // 画图片(设置图片显示的位置)
            movie.draw(canvas, 0, 0);

            // 逐帧绘制图片(图片数量5)
            // 1 2 3 4 5 6 7 8 9 10
            // 1 2 3 4 0 1 2 3 4 0
            movie.setTime((int) (System.currentTimeMillis() % movie.duration()));

            canvas.restore();

            // 画画完成(解锁)
            holder.unlockCanvasAndPost(canvas);

            handler.postDelayed(runnable, 50);
        }
    };

    public void setPath(String path) {
        this.path = path;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public GifSurfaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initParam();
    }

    public GifSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initParam();
    }

    public GifSurfaceView(Context context) {
        super(context);
        initParam();
    }

    /**
     * 初始化参数
     */
    private void initParam() {
        holder = getHolder();
        holder.addCallback(this);

        // handler = new Handler();
    }

    /**
     * 计算视图宽高
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 加载gif图片
        // 1、获取gif图片路径
        if (!TextUtils.isEmpty(path)) {
            try {
                // 加载gif图片
                InputStream stream = getContext().getAssets().open(path);
                movie = Movie.decodeStream(stream);
                // 获取gif图片宽高
                int width = movie.width();
                int height = movie.height();
                setMeasuredDimension((int) (width * zoom), (int) (height * zoom));
                // 执行gif动画
                handler.post(runnable);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // 停止gif动画
        handler.removeCallbacks(runnable);
    }

    
    public void start() {
        // 执行gif动画
        handler.post(runnable);
    }
    
    public void stop() {
        handler.removeCallbacks(runnable);
    }
    
    public int getZoom() {
        return zoom;
    }
}
