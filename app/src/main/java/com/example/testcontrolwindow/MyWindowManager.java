package com.example.testcontrolwindow;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

import com.example.ui.FloatWindowBigView;
import com.example.ui.FloatWindowSmallView;
import com.example.ui.FloatWindowDanmuView;
import com.example.ui.FloatWindowVideoView;

public class MyWindowManager {

	/**
	 * 小悬浮窗View的实例
	 */
	private static FloatWindowSmallView smallWindow;

	/**
	 * 大悬浮窗View的实例
	 */
	private static FloatWindowBigView bigWindow;

	/**
	 * 弹幕悬浮窗的实例
	 */
	private static FloatWindowDanmuView barrageWindow;

	/**
	 * 图片悬浮窗的实例
	 */
	//private static FloatWindowDanmuView picWindow;

	/**
	 * 视频悬浮窗的实例
	 */
	private static FloatWindowVideoView videoWindow;

	/**
	 * 小悬浮窗View的参数
	 */
	private static LayoutParams smallWindowParams;

	/**
	 * 大悬浮窗View的参数
	 */
	private static LayoutParams bigWindowParams;

	/**
	 * 弹幕悬浮窗View的参数
	 */
	private static LayoutParams barrageWindowParams;

	/**
	 * 图片悬浮窗View的参数
	 */
	private static LayoutParams picWindowParams;

	/**
	 * 视频悬浮窗View的参数
	 */
	private static LayoutParams videoWindowParams;

	/**
	 * 用于控制在屏幕上添加或移除悬浮窗
	 */
	private static WindowManager mWindowManager;

	/**
	 * 用于获取手机可用内存
	 */
	private static ActivityManager mActivityManager;

	/**
	 * 创建一个小悬浮窗。初始位置为屏幕的右部中间位置。
	 * 
	 * @param context
	 *            必须为应用程序的Context.
	 */
	public static void createSmallWindow(Context context) {
		WindowManager windowManager = getWindowManager(context);
		int screenWidth = windowManager.getDefaultDisplay().getWidth();
		int screenHeight = windowManager.getDefaultDisplay().getHeight();
		if (smallWindow == null) {
			smallWindow = new FloatWindowSmallView(context);
			if (smallWindowParams == null) {
				smallWindowParams = new LayoutParams();
				smallWindowParams.type = LayoutParams.TYPE_PHONE;
				smallWindowParams.format = PixelFormat.RGBA_8888;
				smallWindowParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
						| LayoutParams.FLAG_NOT_FOCUSABLE;
				smallWindowParams.gravity = Gravity.LEFT | Gravity.TOP;
				smallWindowParams.width = FloatWindowSmallView.viewWidth;
				smallWindowParams.height = FloatWindowSmallView.viewHeight;
				smallWindowParams.x = screenWidth;
				smallWindowParams.y = screenHeight / 2;
			}
			smallWindow.setParams(smallWindowParams);
			windowManager.addView(smallWindow, smallWindowParams);
		}
	}

	/**
	 * 将小悬浮窗从屏幕上移除。
	 * 
	 * @param context
	 *            必须为应用程序的Context.
	 */
	public static void removeSmallWindow(Context context) {
		if (smallWindow != null) {
			WindowManager windowManager = getWindowManager(context);
			windowManager.removeView(smallWindow);
			smallWindow = null;
		}
	}

	/**
	 * 创建一个大悬浮窗。位置为屏幕正中间。
	 * 
	 * @param context
	 *            必须为应用程序的Context.
	 */
	public static void createBigWindow(Context context) {
		WindowManager windowManager = getWindowManager(context);
		int screenWidth = windowManager.getDefaultDisplay().getWidth();
		int screenHeight = windowManager.getDefaultDisplay().getHeight();
		if (bigWindow == null) {
			bigWindow = new FloatWindowBigView(context);
			if (bigWindowParams == null) {
				bigWindowParams = new LayoutParams();
				bigWindowParams.x = screenWidth / 2 - FloatWindowBigView.viewWidth / 2;
				bigWindowParams.y = screenHeight / 2 - FloatWindowBigView.viewHeight / 2;
				bigWindowParams.type = LayoutParams.TYPE_PHONE;
				bigWindowParams.format = PixelFormat.RGBA_8888;
				bigWindowParams.gravity = Gravity.LEFT | Gravity.TOP;
				bigWindowParams.width = FloatWindowBigView.viewWidth;
				bigWindowParams.height = FloatWindowBigView.viewHeight;
			}
			windowManager.addView(bigWindow, bigWindowParams);
		}
	}

	/**
	 * 将大悬浮窗从屏幕上移除。
	 * 
	 * @param context
	 *            必须为应用程序的Context.
	 */
	public static void removeBigWindow(Context context) {
		if (bigWindow != null) {
			WindowManager windowManager = getWindowManager(context);
			windowManager.removeView(bigWindow);
			bigWindow = null;
		}
	}

	public static void createBarrageWindow(Context context) {
		WindowManager windowManager = getWindowManager(context);
		if(barrageWindow == null){
			barrageWindow = new FloatWindowDanmuView(context);
			System.out.println("我的barragewindow创建了");
		   if(barrageWindowParams == null){
			// 窗体的布局样式
			barrageWindowParams = new LayoutParams();
			// 设置窗体显示类型——TYPE_SYSTEM_ALERT(系统提示)
			barrageWindowParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
			// 设置窗体焦点及触摸：
			// FLAG_NOT_FOCUSABLE(不能获得按键输入焦点)
			barrageWindowParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
					| WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;

			// 设置显示的模式
			barrageWindowParams.format = PixelFormat.RGBA_8888;
			// 设置对齐的方法
			barrageWindowParams.gravity = Gravity.TOP | Gravity.LEFT;
			/* 设置窗体宽度和高度 */
			barrageWindowParams.width = WindowManager.LayoutParams.MATCH_PARENT;
			barrageWindowParams.height = WindowManager.LayoutParams.MATCH_PARENT;
		   }
		   windowManager.addView(barrageWindow, barrageWindowParams);
		}
		
	}
	
	public static void removeBarrageWindow(Context context) {
		if (barrageWindow != null) {
			WindowManager windowManager = getWindowManager(context);
			windowManager.removeView(barrageWindow);
			barrageWindow = null;
		}
	}

	public static void createVideoWindow(Context context){
		WindowManager windowManager = getWindowManager(context);
		int screenWidth = windowManager.getDefaultDisplay().getWidth();
		int screenHeight = windowManager.getDefaultDisplay().getHeight();
		if (videoWindow == null) {
			videoWindow = new FloatWindowVideoView(context);
			if (videoWindowParams == null) {
				videoWindowParams = new LayoutParams();
				videoWindowParams.x = screenWidth / 2 - FloatWindowVideoView.viewWidth / 2;
				videoWindowParams.y = screenHeight / 2 - FloatWindowVideoView.viewHeight / 2;
				videoWindowParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
						| LayoutParams.FLAG_NOT_FOCUSABLE;
				videoWindowParams.type = LayoutParams.TYPE_PHONE;
				videoWindowParams.format = PixelFormat.RGBA_8888;
				videoWindowParams.gravity = Gravity.LEFT | Gravity.TOP;
				videoWindowParams.width = FloatWindowVideoView.viewWidth;
				videoWindowParams.height = FloatWindowVideoView.viewHeight;
			}
			videoWindow.setParams(videoWindowParams);
			windowManager.addView(videoWindow, videoWindowParams);
		}
	}

	public static void removeVideoWindow(Context context) {
		if (videoWindow != null) {
			WindowManager windowManager = getWindowManager(context);
			windowManager.removeView(videoWindow);
			videoWindow = null;
		}
	}

	/**
	 * 是否有悬浮窗(包括小悬浮窗和大悬浮窗)显示在屏幕上。
	 * 
	 * @return 有悬浮窗显示在桌面上返回true，没有的话返回false。
	 */
	public static boolean isWindowShowing() {
		return smallWindow != null || bigWindow != null;
	}

	/**
	 * 如果WindowManager还未创建，则创建一个新的WindowManager返回。否则返回当前已创建的WindowManager。
	 * 
	 * @param context
	 *            必须为应用程序的Context.
	 * @return WindowManager的实例，用于控制在屏幕上添加或移除悬浮窗。
	 */
	public static WindowManager getWindowManager(Context context) {
		if (mWindowManager == null) {
			mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		}
		return mWindowManager;
	}

	/**
	 * 如果ActivityManager还未创建，则创建一个新的ActivityManager返回。否则返回当前已创建的ActivityManager。
	 * 
	 * @param context
	 *            可传入应用程序上下文。
	 * @return ActivityManager的实例，用于获取手机可用内存。
	 */
	private static ActivityManager getActivityManager(Context context) {
		if (mActivityManager == null) {
			mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		}
		return mActivityManager;
	}



}
