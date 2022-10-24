package com.example.letmeapp.ui.login;

import android.content.Intent;

import com.example.letmeapp.model.User;
import com.example.letmeapp.ui.base.IProgressView;

public interface LoginContract {
    interface LoginCallback{
        void onSuccess(String email);
        void onFailure(String message);
        void onUserDataSuccess(User user);
        void onUserDataEmpty();
    }

    interface View extends IProgressView, LoginCallback{
        void setUserEmptyError();
        void setPasswordEmptyError();
    }

    interface Presenter{
        void onDestroy();
        void login(String email, String password);
        void gmailLogin(Intent data);
        void getUserData(String email);
    }

    interface Interactor extends LoginCallback{
        void onUserEmptyError();
        void onPasswordEmptyError();
    }
}
