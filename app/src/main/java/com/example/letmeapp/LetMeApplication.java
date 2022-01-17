package com.example.letmeapp;

import android.app.Application;
import android.content.Context;

import com.facebook.appevents.AppEventsLogger;

public class LetMeApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        AppEventsLogger.activateApp(this);
    }

    public static Context getContext(){
        return context;
    }
}
