package com.example.letmeapp.utils;

import android.content.Context;
import android.preference.PreferenceManager;

import com.example.letmeapp.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyUtils {
    public static boolean isValidPassword(String password){
        final String password_pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&?;\\-_])(?!.*\\s).{8,20}$";
        Pattern pattern = Pattern.compile(password_pattern);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static User getUserData(Context context){
        User user = new User(
                PreferenceManager.getDefaultSharedPreferences(context).getString(User.USERNAME_TAG, ""),
                PreferenceManager.getDefaultSharedPreferences(context).getString(User.NAME_TAG, ""),
                PreferenceManager.getDefaultSharedPreferences(context).getString(User.EMAIL_TAG, ""),
                PreferenceManager.getDefaultSharedPreferences(context).getString(User.IMAGE_TAG, "")
        );
        return user;
    }
}
