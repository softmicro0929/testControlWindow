package com.example.network;

import android.content.Context;
import java.util.Map;

/**
 * Created by shuainan on 2016/4/1.
 */
public class RequestDanmuTextHelper extends BaseNetworkHelper{


    private Context mContext;

    public RequestDanmuTextHelper(Context context) {
        super(context);
        this.mContext = context;
    }
    /*
    * 以GET方式获取弹幕包，URL,参数，回调接口
    * */
    public void getBarragePackage(String URL, Map<String,String> MAP, NetworkCallBack callback){

        sendGetRequest(URL, MAP,callback);
    }


}
