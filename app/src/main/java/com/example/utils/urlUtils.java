package com.example.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class urlUtils {
	
	private static String encode="utf-8";

	public static String urladdparam(String mUrl,Map<String, String> parms) {
		StringBuilder url=new StringBuilder(mUrl);  
	    url.append("?");
        for(Map.Entry<String, String> entry:parms.entrySet())  
        {  
            url.append(entry.getKey()).append("=");  

			try {
				url.append(URLEncoder.encode(entry.getValue(), encode));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}


			url.append("&");
            
        }

        url.deleteCharAt(url.length()-1);  
		System.out.println(url);	
		return url.toString();
	}

}
