package com.gky.okhttpdeme;

import android.app.Application;
import android.content.Context;

/**
 * Created by 凯阳 on 2016/6/23.
 */
public class MainApp extends Application{

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext(){
        return mContext;
    }
}
