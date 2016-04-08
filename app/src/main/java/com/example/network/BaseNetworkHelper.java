package com.example.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

public class BaseNetworkHelper {
    private RequestQueue requestQueue;

    public BaseNetworkHelper(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    public void sendGetRequest(String url, Map<String, String> params,  final NetworkCallBack callBack) {
        if (params != null) {
            url = url + "?";
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (!url.endsWith("?")) url=url+"&";
                url = url + entry.getKey() + "=" + entry.getValue();
            }
        }
        url = url.replace("\n", "");
        Log.d("sohonet", "sendGetRequest:" + url);
        StringRequest getRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("sohonet", "sendGetRequest respone:" + response);
                        if (callBack != null)
                            callBack.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (callBack != null)
                            callBack.onFailed(error);
                    }
                });
        requestQueue.add(getRequest);
    }

    protected void sendLongGetRequest(String url, int timeout, Map<String, String> params, final NetworkCallBack callBack) {
        if (params != null) {
            url = url + "?";
            for (Map.Entry<String, String> entry : params.entrySet()) {
                url = url + entry.getKey() + "=" + entry.getValue();
            }
        }
        StringRequest getRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (callBack != null)
                            callBack.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (callBack != null)
                            callBack.onFailed(error);
                    }
                });
        getRequest.setRetryPolicy(new DefaultRetryPolicy(timeout, 1, 1.0f));
        requestQueue.add(getRequest);
    }

    protected void sendPostRequest(String url, final Map<String, String> params, final NetworkCallBack callBack) {
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (callBack != null) {
                            callBack.onSuccess(response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (callBack != null) {
                            callBack.onFailed(error);
                        }
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        requestQueue.add(postRequest);
    }

}