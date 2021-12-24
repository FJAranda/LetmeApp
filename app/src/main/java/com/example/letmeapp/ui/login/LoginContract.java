package com.example.letmeapp.ui.login;

import android.content.Intent;

import com.example.letmeapp.model.User;
import com.example.letmeapp.ui.base.IProgressView;
import com.facebook.AccessToken;

public interface LoginContract {
    interface LoginCallback{
        void onSuccess(String email);
        void onFailure(String message);
    }

    interface View extends IProgressView, LoginCallback{
        void setUserEmptyError();
        void setPasswordEmptyError();
    }

    interface Presenter{
        void onDestroy();
        void login(String email, String password);
        void facebookLogin(AccessToken accessToken);
        void gmailLogin(Intent data);
    }

    interface Interactor extends LoginCallback{
        void onUserEmptyError();
        void onPasswordEmptyError();
    }
}
