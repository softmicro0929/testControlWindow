package com.example.testcontrolwindow;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import com.example.network.NetworkCallBack;
import com.example.network.RequestDanmuTextHelper;
import com.example.system.BarrageParam;
import com.example.ui.FloatWindowBigView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FloatWindowService extends Service {

	public String urlString = "http://192.168.146.1/danmuServer/index.php";
	//public String mUrl = "http://192.168.146.1/danmuServer/index.php";
	public String mUrl = "http://101.200.131.45/appinterface/app1/index111.php";
	private Map<String, String> mMap = new HashMap<String, String>();

	/**
	 * 用于在线程中创建或移除悬浮窗。
	 */
	private Handler handler = new Handler();

	Handler handler1 = new Handler();
	/**
	 * 定时器，定时进行检测当前应该创建还是移除悬浮窗。
	 */
	private Timer timer;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// 开启定时器，每隔0.5秒刷新一次
		if (timer == null) {
			timer = new Timer();
			timer.scheduleAtFixedRate(new RefreshTask(), 0, BarrageParam.requestDuration);
		}
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		// Service被终止的同时也停止定时器继续运行
		timer.cancel();
		timer = null;
	}

	class RefreshTask extends TimerTask {

		@Override
		public void run() {
			// 当前没有悬浮窗显示，则创建悬浮窗。
			if (!MyWindowManager.isWindowShowing()) {
				handler.post(new Runnable() {
					public void run() {
						MyWindowManager.createSmallWindow(getApplicationContext());

					}
				});
			}

			/**
			 * 判断当前弹幕开关是否开启，开启则开始  *定时*  读取数据
			 */
			if(FloatWindowBigView.barr_Switch){
				/**
				 * 用的getApplicationContext（）来获取 context
				 */
				RequestDanmuTextHelper mRequest = new RequestDanmuTextHelper(getApplicationContext());
				mMap.put("screen_mac", "1");
				mRequest.getBarragePackage(mUrl,mMap,callBack);

			}
		}

	}

	NetworkCallBack callBack = new NetworkCallBack() {
		@Override
		public void onSuccess(String response) {
			System.out.println(response);
			JSONObject jsonObject = null;
			try {
				jsonObject = new JSONObject(response);

				BarrageParam.barrageNum = Integer.parseInt(jsonObject.getString("msg"));
				System.out.println(""+BarrageParam.barrageNum);

				JSONArray jsonArray = jsonObject.getJSONArray("data");

				for (int i = 0;i<jsonArray.length();i++){
					BarrageParam.itemText.add(jsonArray.getString(i));
					System.out.println(BarrageParam.itemText.get(i));
				}

				System.out.println("================数据存放结束");

			} catch (JSONException e) {
				e.printStackTrace();
			}

		}

		@Override
		public void onFailed(Exception e) {
			System.out.println("CALLBACK ERROR !!!");

		}
	};


}
