package com.example.javabean;

/**
 * Created by shuainan on 2016/3/31.
 */
public class BarragePackage {
    private int barrage_Num;
    private float barrage_Duration;
    private String barrage_ItemText[];

    public BarragePackage(int num,float duration,String itemtext[]){
        this.barrage_Num = num;
        this.barrage_Duration = duration;
        this.barrage_ItemText = itemtext;
    }

    public int getBarrage_Num() {
        return barrage_Num;
    }

    public void setBarrage_Num(int barrage_Num) {
        this.barrage_Num = barrage_Num;
    }

    public float getBarrage_Duration() {
        return barrage_Duration;
    }

    public void setBarrage_Duration(float barrage_Duration) {
        this.barrage_Duration = barrage_Duration;
    }

    public String[] getBarrage_ItemText() {
        return barrage_ItemText;
    }

    public void setBarrage_ItemText(String[] barrage_ItemText) {
        this.barrage_ItemText = barrage_ItemText;
    }
}
