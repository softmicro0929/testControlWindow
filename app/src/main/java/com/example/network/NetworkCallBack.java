package com.example.network;

/**
 * Created by shuainan on 2016/3/20.
 */
public interface NetworkCallBack {
    public void onSuccess(String response);
    public void onFailed(Exception e);
}
