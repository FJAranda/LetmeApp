package com.example.letmeapp.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letmeapp.R;
import com.example.letmeapp.model.User;
import com.example.letmeapp.ui.MainActivity;
import com.example.letmeapp.ui.login.LoginActivity;
import com.example.letmeapp.utils.MyUtils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
        startLogin();
    }

    private void startLogin() {
        User user = MyUtils.getUserData(this);
        if (PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean(getString(R.string.account_key), false)) {
            if (user.isCompleted()) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }

        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }
}