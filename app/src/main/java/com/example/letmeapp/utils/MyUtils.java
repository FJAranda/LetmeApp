package com.example.letmeapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.letmeapp.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

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

    public static boolean setUserData(Context context, User user){
        try {
            SharedPreferences.Editor sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context).edit();
            sharedPreferences.putString(User.USERNAME_TAG, user.getUsername());
            sharedPreferences.putString(User.NAME_TAG, user.getName());
            sharedPreferences.putString(User.EMAIL_TAG, user.getEmail());
            sharedPreferences.putString(User.IMAGE_TAG, user.getImage());
            sharedPreferences.putBoolean(User.USER_COMPLETED_PREFERENCE, true);
            sharedPreferences.apply();
        }catch (Exception e){
            return false;
        }
        return true;
    };
}
