package com.example.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.StrictMode;

public class JsonUtil {

	public static String getJsonStringFromUrl(String urlString) {
		String dateString = null;
		try {

			StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
	        StrictMode.setThreadPolicy(policy);

			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("GET");
			//InputStream in = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String str;
			while((str = reader.readLine())!=null){
				stringBuffer.append(str);
			}

			dateString = stringBuffer.toString();
			System.out.println("=========网络请求结束");
			return dateString;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("********************jsonutil.getJsonStringFromUrl->网络异常");
		}
		return dateString;		
	}
}
