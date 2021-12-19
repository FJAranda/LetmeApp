package com.example.letmeapp;

import android.app.Application;

import com.facebook.appevents.AppEventsLogger;

public class LetMeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppEventsLogger.activateApp(this);
    }
}
