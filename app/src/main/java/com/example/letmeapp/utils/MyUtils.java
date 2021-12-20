package com.example.letmeapp.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyUtils {
    public static boolean isValidPassword(String password){
        final String password_pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&?;\\-_])(?!.*\\s).{8,20}$";
        Pattern pattern = Pattern.compile(password_pattern);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
