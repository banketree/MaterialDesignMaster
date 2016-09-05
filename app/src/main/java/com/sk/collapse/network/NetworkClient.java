package com.sk.collapse.network;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

/**
 * Created by sk on 16-9-5.
 */
public class NetworkClient {

    private static NetworkClient mInstance;
    private static RequestQueue mQueue;

    public NetworkClient(Context context) {
        mQueue = Volley.newRequestQueue(context);
    }


    public static NetworkClient newInstance(Context context) {
        if(mInstance == null) {
            synchronized (NetworkClient.class) {
                mInstance = new NetworkClient(context);
            }
        }

        return mInstance;
    }

    public static NetworkClient getInstance(Context context) {
        if(mInstance == null) {
            mInstance = newInstance(context);
        }

        return mInstance;
    }

    public void get(String url, Map<String, String> params, onHttpResponse onRes) {

        mQueue.add(new VolleyRequest(Request.Method.GET, url, params, onRes));
    }

    public void post(String baseurl, Map<String, String> params, onHttpResponse onRes) {

        mQueue.add(new VolleyRequest(Request.Method.POST, baseurl, params, onRes));

    }


    private class VolleyRequest extends StringRequest {

        private Map<String, String> mMap = null;

        public VolleyRequest(int method, String url, Map<String, String> map, onHttpResponse onRes) {

            this(method, url, onRes, onRes);
            mMap = map;
        }

        public VolleyRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
            super(method, url, listener, errorListener);
        }

        @Override
        protected Map<String, String> getParams() throws AuthFailureError {

            return mMap;
        }
    }
}
