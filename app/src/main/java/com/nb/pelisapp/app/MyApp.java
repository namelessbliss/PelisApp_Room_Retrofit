package com.nb.pelisapp.app;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {

    private static MyApp sInstance;

    public static MyApp getInstance() {
        return sInstance;
    }

    public static Context getContext() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        sInstance = this;
        super.onCreate();
    }
}
