package com.example.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.testcontrolwindow.FloatWindowService;
import com.example.testcontrolwindow.MyWindowManager;
import com.example.testcontrolwindow.R;
import com.example.ui.BarrageView;

public class FloatWindowBigView extends LinearLayout {

	/*
	 * 记录大悬浮窗,弹幕开关，图片开关，视频开关
	 */
	public static boolean barr_Switch = false;
	public static boolean pic_Switch = false;
	public static boolean video_Switch = false;
	/**
	 * 记录大悬浮窗的宽度
	 */
	public static int viewWidth;

	/**
	 * 记录大悬浮窗的高度
	 */
	public static int viewHeight;

	public FloatWindowBigView(final Context context) {
		super(context);
		LayoutInflater.from(context).inflate(R.layout.float_window_big, this);
		View view = findViewById(R.id.big_window_layout);
		viewWidth = view.getLayoutParams().width;
		viewHeight = view.getLayoutParams().height;

		final Button barrageButton = (Button) findViewById(R.id.barrage_switch);
		final Button picButton = (Button) findViewById(R.id.pic_switch);
		final Button videoButton = (Button) findViewById(R.id.video_switch);

		final Button close = (Button) findViewById(R.id.close);
		final Button back = (Button) findViewById(R.id.back);
		
		if(barr_Switch){
			barrageButton.setText("关闭弹幕");
		}else {
			barrageButton.setText("打开弹幕");
		}
		if(pic_Switch){
			picButton.setText("关闭图片");
		}else {
			picButton.setText("打开图片");
		}
		if(video_Switch){
			videoButton.setText("关闭视频");
		}else {
			videoButton.setText("打开视频");
		}
		
		
		//弹幕按钮---动作
		barrageButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// 弹幕开关
				if(!barr_Switch){
					//弹幕是关的->去打开
					barrageButton.setText("关闭弹幕");
					MyWindowManager.createBarrageWindow(context);
					barr_Switch = true;
					BarrageView.FLAG_SHOW = true;
					System.out.println("我打开弹幕了");
				}else {
					//弹幕是开的->去关闭
					barrageButton.setText("打开弹幕");
					MyWindowManager.removeBarrageWindow(context);
					barr_Switch = false;
					BarrageView.FLAG_SHOW = false;
					System.out.println("我关闭弹幕了");
				}
			}
		});


		//图片按钮动作
		picButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(context,"我按了图片按钮",Toast.LENGTH_SHORT).show();
				if(!pic_Switch){
					//图片是关的->去打开
					picButton.setText("关闭图片");
					//MyWindowManager.createBarrageWindow(context);
					pic_Switch = true;
					System.out.println("我打开图片了");
				}else {
					//弹幕是开的->去关闭
					picButton.setText("打开图片");
					//MyWindowManager.removeBarrageWindow(context);
					pic_Switch = false;
					System.out.println("我关闭图片了");
				}
			}
		});

		//视频按钮动作
		videoButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(context,"我按了视频按钮",Toast.LENGTH_SHORT).show();
				MyWindowManager.createVideoWindow(context);
				if(!video_Switch){
					//弹幕是关的->去打开
					videoButton.setText("关闭视频");
					MyWindowManager.createVideoWindow(context);
					video_Switch = true;
					//BarrageView.FLAG_SHOW = true;
					System.out.println("我打开视频了");
				}else {
					//弹幕是开的->去关闭
					videoButton.setText("打开视频");
					MyWindowManager.removeVideoWindow(context);
					video_Switch = false;
					//BarrageView.FLAG_SHOW = false;
					System.out.println("我关闭视频了");
				}
			}
		});


		
		close.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// 点击关闭悬浮窗的时候，移除所有悬浮窗，并停止Service
				if(barr_Switch){
					//先将弹幕的相关开关处置好
					MyWindowManager.removeBarrageWindow(context);
					barrageButton.setText("打开弹幕");
					barr_Switch = false;
					BarrageView.FLAG_SHOW = false;
				}
				if(pic_Switch){
					//关闭图片所做的处理
					picButton.setText("打开图片");
					pic_Switch = false;
				}
				if(video_Switch){
					videoButton.setText("打开视频");
					MyWindowManager.removeVideoWindow(context);
					video_Switch = false;
				}

				MyWindowManager.removeBigWindow(context);
				MyWindowManager.removeSmallWindow(context);
				Intent intent = new Intent(getContext(), FloatWindowService.class);
				context.stopService(intent);
			}
		});
		back.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// 点击返回的时候，移除大悬浮窗，创建小悬浮窗
				MyWindowManager.removeBigWindow(context);
				MyWindowManager.createSmallWindow(context);
			}
		});
	}
}
