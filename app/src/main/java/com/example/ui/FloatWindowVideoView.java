package com.example.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.VideoView;

import com.example.system.ConstantData;
import com.example.testcontrolwindow.R;

import java.lang.reflect.Field;

/**
 * Created by shuainan on 2016/4/5.
 */
public class FloatWindowVideoView extends LinearLayout{

    private VideoView mVideoView;

    /**
     * 记录悬浮窗的宽度
     */
    public static int viewWidth;

    /**
     * 记录悬浮窗的高度
     */
    public static int viewHeight;

    /**
     * 记录系统状态栏的高度
     */
    private static int statusBarHeight;

    /**
     * 用于更新悬浮窗的位置
     */
    private WindowManager windowManager;

    /**
     * 悬浮窗的参数
     */
    private WindowManager.LayoutParams mParams;

    /**
     * 记录当前手指位置在屏幕上的横坐标值
     */
    private float xInScreen;

    /**
     * 记录当前手指位置在屏幕上的纵坐标值
     */
    private float yInScreen;

    /**
     * 记录手指按下时在屏幕上的横坐标的值
     */
    private float xDownInScreen;

    /**
     * 记录手指按下时在屏幕上的纵坐标的值
     */
    private float yDownInScreen;

    /**
     * 记录手指按下时在小悬浮窗的View上的横坐标的值
     */
    private float xInView;

    /**
     * 记录手指按下时在小悬浮窗的View上的纵坐标的值
     */
    private float yInView;



    public FloatWindowVideoView(Context context){
        super(context);
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        LayoutInflater.from(context).inflate(R.layout.float_window_video, this);
        View view = findViewById(R.id.video_window_layout);
        viewWidth = view.getLayoutParams().width;
        viewHeight = view.getLayoutParams().height;

        System.out.println("*************" + viewWidth + ":" + viewHeight);
        System.out.println("1+++++++++++++" + view.getWidth() + ":" + view.getHeight());

        init(context);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        System.out.println("2+++++++++++++" + getWidth() + ":" + getHeight());
        System.out.println("3+++++++++++++" + getMeasuredWidth() + ":" + getMeasuredHeight());

    }

    private void init(Context context) {
        mVideoView = (VideoView) findViewById(R.id.videoview);

        //http://127.0.0.1/danmuserver/video/testvideo.mp4
        //Environment.getExternalStorageDirectory() + "/testvideo.mp4"
        mVideoView.setVideoURI(Uri.parse(ConstantData.IP+"/danmuserver/video/testvideo.mp4"));
        mVideoView.start();
        mVideoView.requestFocus();

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 手指按下时记录必要数据,纵坐标的值都需要减去状态栏高度
                xInView = event.getX();
                yInView = event.getY();
                xDownInScreen = event.getRawX();
                yDownInScreen = event.getRawY() - getStatusBarHeight();
                xInScreen = event.getRawX();
                yInScreen = event.getRawY() - getStatusBarHeight();
                break;
            case MotionEvent.ACTION_MOVE:
                xInScreen = event.getRawX();
                yInScreen = event.getRawY() - getStatusBarHeight();
                // 手指移动的时候更新小悬浮窗的位置
                updateViewPosition();
                break;
            case MotionEvent.ACTION_UP:
                // 如果手指离开屏幕时，xDownInScreen和xInScreen相等，且yDownInScreen和yInScreen相等，则视为触发了单击事件。
                if (Math.abs(xDownInScreen-xInScreen)<10 && Math.abs(yDownInScreen-yInScreen)<10){
                    //openBigWindow();
                }

                break;
            default:
                break;
        }
        return true;
    }


    public void setParams(WindowManager.LayoutParams params) {
        mParams = params;
    }


    private void updateViewPosition() {
        mParams.x = (int) (xInScreen - xInView);
        mParams.y = (int) (yInScreen - yInView);
        windowManager.updateViewLayout(this, mParams);
    }


    /**
     * 用于获取状态栏的高度。
     *
     * @return 返回状态栏高度的像素值。
     */
    private int getStatusBarHeight() {
        if (statusBarHeight == 0) {
            try {
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                Object o = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = (Integer) field.get(o);
                statusBarHeight = getResources().getDimensionPixelSize(x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusBarHeight;
    }


}
