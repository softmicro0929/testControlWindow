package com.example.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.testcontrolwindow.R;

public class FloatWindowDanmuView extends RelativeLayout {

	@SuppressLint("InflateParams")
	public FloatWindowDanmuView(Context context) {
		super(context);

		this.setLayoutParams( new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		
		View view = LayoutInflater.from(context).inflate(  
                R.layout.danmu_layout, null);
		this.addView(view);
	}

}
