package com.example.letmeapp.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import com.example.letmeapp.R;
import com.example.letmeapp.model.User;
import com.example.letmeapp.ui.MainActivity;
import com.example.letmeapp.ui.login.LoginActivity;

import java.util.prefs.Preferences;

public class SplashActivity extends AppCompatActivity {
    private static final long WAIT_TIME = 2000;

    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).contains(User.EMAIL_TAG)) {
                    startMain();
                } else {
                    startLogin();
                }
            }
        }, WAIT_TIME);
    }

    private void startMain() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

    private void startLogin() {
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }
}