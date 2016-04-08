package com.example.system;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shuainan on 2016/3/31.
 */
public class BarrageParam {

    public static final long BARRAGE_GAP_MIN_DURATION = 1000;//两个弹幕的最小间隔时间
    public static final long BARRAGE_GAP_MAX_DURATION = 2000;//两个弹幕的最大间隔时间
    public static final int maxSpeed = 10000;//速度，ms
    public static final int minSpeed = 5000;//速度，ms
    public static final int maxSize = 30;//文字大小，dp
    public static final int minSize = 15;//文字大小，dp

    public static int requestDuration = 5000;  //弹幕请求间隔时间 ms  ，默认5000ms=>5s
    public static int barrageNum; //弹幕数量
    public static List<String> itemText = new ArrayList<String>();  //存储弹幕数组

    public static final int totalHeight = 0;
    public static final int lineHeight = 0;//每一行弹幕的高度
    public static final int totalLine = 0;//弹幕的行数

}
