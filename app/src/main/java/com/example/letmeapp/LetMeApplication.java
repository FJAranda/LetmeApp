package com.example.letmeapp;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.media.ToneGenerator;
import android.os.Build;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatDelegate;

import com.example.letmeapp.model.database.MyDatabase;
import com.facebook.appevents.AppEventsLogger;

public class LetMeApplication extends Application {
    private static Context context;
    public static final String IDCHANNEL = "123456";

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        changeTheme();
        AppEventsLogger.activateApp(this);
        MyDatabase.create(this);
        createNotificationChannel();
    }

    private void changeTheme() {
        if (!PreferenceManager.getDefaultSharedPreferences(this).getBoolean(getString(R.string.automatic_mode_key), true)){
            //Si está activado el modo oscuro, lo seteo
            if (PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean(getString(R.string.night_mode_key), false)){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        }
    }

    private void createNotificationChannel() {
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        String nameChannel = "letmeapp_channel";
        NotificationChannel notificationChannel = new NotificationChannel(IDCHANNEL, nameChannel, importance);
        getSystemService(NotificationManager.class).createNotificationChannel(notificationChannel);
    }

    public static Context getContext(){
        return context;
    }
}
