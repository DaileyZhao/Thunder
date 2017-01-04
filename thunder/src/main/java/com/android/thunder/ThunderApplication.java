package com.android.thunder;

import android.app.Application;
import android.content.Context;

/**
 * Copyright(c) 2016 All Rights Reserved.
 * Author: ZhaoCunming
 * Time: 16-11-23 11:47
 * E-mail: zhaocunming@dayima.com
 * Version：V1.0.1
 * ProjectName: Thunder
 * Description: TODO
 */
public class ThunderApplication extends Application {
    private static Context context;
    private static boolean mDebug = true;

    public static boolean isDebug(){
        return mDebug;
    }
    public static Context getContext(){
        return context;
    }

    @Override
    public Context getApplicationContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
    }
}
