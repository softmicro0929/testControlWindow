package com.example.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.system.BarrageParam;
import com.example.system.BarrageItem;
import com.example.testcontrolwindow.MyWindowManager;

import java.util.Random;

/**
 * Created by shuainan on 2016/3/31.
 */
public class BarrageView extends RelativeLayout {

    public  static  boolean FLAG_SHOW = true;


    private Context mContext;
    private BarrageHandler mHandler = new BarrageHandler();
    private Random random = new Random(System.currentTimeMillis());

    /*private int maxSize = 30;//文字大小，dp
    private int minSize = 15;//文字大小，dp*/

    private int totalHeight = 0;
    private int lineHeight = 0;//每一行弹幕的高度
    private int totalLine = 0;//弹幕的行数
    /*private String[] itemText = {"是否需要帮忙", "what are you 弄啥来", "哈哈哈哈哈哈哈", "抢占沙发。。。。。。", "************", "是否需要帮忙",
            "我不会轻易的狗带", "嘿嘿", "这是我见过的最长长长长长长长长长长长的评论"};*/

    private int textCount;
//    private List<BarrageItem> itemList = new ArrayList<BarrageItem>();

    public BarrageView(Context context) {
        this(context, null);
        mContext = context;
    }

    public BarrageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        mContext = context;
    }

    public BarrageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        if(BarrageParam.itemText != null && BarrageParam.itemText.size() != 0){
            textCount = BarrageParam.itemText.size();
        }


        //int duration = (int) ((BarrageParam.BARRAGE_GAP_MAX_DURATION - BarrageParam.BARRAGE_GAP_MIN_DURATION) * Math.random());
        mHandler.sendEmptyMessageDelayed(0, 0);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        /*totalHeight = getMeasuredHeight();
        lineHeight = getLineHeight();*/

        totalHeight = MyWindowManager.getWindowManager(mContext).getDefaultDisplay().getWidth();
        lineHeight = getLineHeight();
        //totalHeight = 1000;
        //lineHeight = 100;
        totalLine = totalHeight / lineHeight;
    }

    private void generateItem() {
        BarrageItem item = new BarrageItem();
        String tx = BarrageParam.itemText.get(0);   //    (int) (Math.random() * textCount)
        BarrageParam.itemText.remove(0);
        item.setText(tx);
        int sz = (int) (BarrageParam.minSize + (BarrageParam.maxSize - BarrageParam.minSize) * Math.random());
        item.setTextSize(sz);
        item.textView = new TextView(mContext);
        item.textView.setText(item.getText());
        item.textView.setTextSize(item.getTextSize());
        item.textView.setTextColor(Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
        item.textMeasuredWidth = (int) getTextWidth(item, tx, sz);
        item.moveSpeed = (int) (BarrageParam.minSpeed + (BarrageParam.maxSpeed - BarrageParam.minSpeed) * Math.random());
        if (totalLine == 0) {
            totalHeight = getMeasuredHeight();
            lineHeight = getLineHeight();
            totalLine = totalHeight / lineHeight;
        }
        item.verticalPos = random.nextInt(totalLine) * lineHeight;
//        itemList.add(item);
        showBarrageItem(item);
    }

    private void showBarrageItem(final BarrageItem item) {

        int leftMargin = this.getRight() - this.getLeft() - this.getPaddingLeft();

        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        params.topMargin = item.verticalPos;
        this.addView(item.textView, params);
        Animation anim = generateTranslateAnim(item, leftMargin);
        anim.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {

            }

            public void onAnimationEnd(Animation animation) {
                item.textView.clearAnimation();
                BarrageView.this.removeView(item.textView);
            }

            public void onAnimationRepeat(Animation animation) {

            }
        });
        item.textView.startAnimation(anim);
    }

    private TranslateAnimation generateTranslateAnim(BarrageItem item, int leftMargin) {
        TranslateAnimation anim = new TranslateAnimation(leftMargin, -item.textMeasuredWidth, 0, 0);
        anim.setDuration(item.moveSpeed);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.setFillAfter(true);
        return anim;
    }

    /**
     * 计算TextView中字符串的长度
     *
     * @param text 要计算的字符串
     * @param Size 字体大小
     * @return TextView中字符串的长度
     */
    public float getTextWidth(BarrageItem item, String text, float Size) {
        Rect bounds = new Rect();
        TextPaint paint;
        paint = item.textView.getPaint();
        paint.getTextBounds(text, 0, text.length(), bounds);
        return bounds.width();

    }

    /**
     * 获得每一行弹幕的最大高度
     *
     * @return
     */
    private int getLineHeight() {
        //   ???   length
        if(BarrageParam.itemText != null){
            BarrageItem item = new BarrageItem();
            String tx = BarrageParam.itemText.get(0);
            item.textView = new TextView(mContext);
            item.textView.setText(tx);
            item.textView.setTextSize(BarrageParam.maxSize);

            Rect bounds = new Rect();
            TextPaint paint;
            paint = item.textView.getPaint();
            paint.getTextBounds(tx, 0, tx.length(), bounds);
            return bounds.height();
        }
        return 100;
    }

    class BarrageHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            //super.handleMessage(msg);
            Log.i("meidanmu",">>>>>>>>>>>>>>>>>>>>>>>>>>>");
            if(FLAG_SHOW){
                if(BarrageParam.itemText == null || BarrageParam.itemText.size() == 0){
                    //如果网络请求尚未结束或其他原因，弹幕数组尚未有值，  每隔0.5s 继续尝试请求
                    this.sendEmptyMessageDelayed(0,500);
                    Log.i("meidanmu","########################");
                }else {
                    generateItem();
                    //每个弹幕产生的间隔时间随机
                    //int duration = (int) ((BarrageParam.BARRAGE_GAP_MAX_DURATION - BarrageParam.BARRAGE_GAP_MIN_DURATION) * Math.random());
                    int duration = BarrageParam.requestDuration/BarrageParam.barrageNum;
                    this.sendEmptyMessageDelayed(0, duration);

                    Log.i("danmu", "****************************");
                }

            }
            else {
                Log.i("danmuting", "_____________________________");
            }


        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        Log.i("onWindowChanged","@@@@@@@@@@@");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.i("onDetachedFromWindow", "!!!!!!!!!!!");

        FLAG_SHOW = false;
    }
}