package com.example.letmeapp.ui.login;

import com.example.letmeapp.model.User;
import com.example.letmeapp.ui.base.IProgressView;

public interface LoginContract {
    interface LoginCallback{
        void onSuccess(String email);
        void onFailure(String message);
        void onFacebookFailure();
    }

    interface View extends IProgressView, LoginCallback{
        void setUserEmptyError();
        void setPasswordEmptyError();
    }

    interface Presenter{
        void onDestroy();
        void login(String email, String password);
        void facebookLogin();
        void gmailLogin();
    }

    interface Interactor extends LoginCallback{
        void onUserEmptyError();
        void onPasswordEmptyError();
    }
}
