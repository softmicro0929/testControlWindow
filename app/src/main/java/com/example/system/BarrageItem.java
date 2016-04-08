package com.example.system;

import android.widget.TextView;

/**
 * Created by shuainan on 16/2/19.
 */
public class BarrageItem {
    public TextView textView;
    public int textColor;
    public String text;
    public int textSize;
    public int moveSpeed;//移动速度
    public int verticalPos;//垂直方向显示的位置
    public int textMeasuredWidth;//字体显示占据的宽度

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public int getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public int getVerticalPos() {
        return verticalPos;
    }

    public void setVerticalPos(int verticalPos) {
        this.verticalPos = verticalPos;
    }

    public int getTextMeasuredWidth() {
        return textMeasuredWidth;
    }

    public void setTextMeasuredWidth(int textMeasuredWidth) {
        this.textMeasuredWidth = textMeasuredWidth;
    }
}
