package com.sk.collapse.model;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by sk on 16-9-5.
 */
abstract public class onHttpResponse implements Response.Listener, Response.ErrorListener{

    private final static int ON_RESPONSE = 100;


    private Handler mHandler = null;
    private Context mContext = null;
    private Object mData = null;
    private Object mResult = null;
    private int mCode = 0;

    public onHttpResponse(Context context, Object data) {
        this.mContext = context;
        this.mData = data;

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case ON_RESPONSE:
                        onResult(mCode, mResult, mData);
                        break;

                }
            }
        };
    }

    @Override
    public void onResponse(Object response) {

        String ret = null;
        if(response != null && response instanceof String) {
            ret = (String) response;
        }

        if(TextUtils.isEmpty(ret)) {
            mCode = -1;
            mResult = response;
        }else {
            mCode = 0;
            mResult = response;
        }

        mHandler.sendEmptyMessage(ON_RESPONSE);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        int statusCode = -1;
        if(error != null && error.networkResponse != null) {
            statusCode = error.networkResponse.statusCode;
            Log.d("onHttpResponse", "onErrorResponse->statusCode is:" + statusCode);
        }

        mCode = -1;
        mResult = null;

        mHandler.sendEmptyMessage(ON_RESPONSE);
    }


    abstract public void onResult(int code, Object result, Object data);
}
