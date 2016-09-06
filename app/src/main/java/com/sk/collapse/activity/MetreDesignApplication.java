package com.sk.collapse.activity;

import android.app.Application;

import com.sk.collapse.network.NetworkClient;

/**
 * Created by sk on 16-9-5.
 */
public class MetreDesignApplication extends Application {

    private static MetreDesignApplication mInstance = null;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        NetworkClient.newInstance(this);
    }



}
