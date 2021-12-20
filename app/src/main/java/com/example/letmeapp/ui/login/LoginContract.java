package com.example.letmeapp.ui.login;

public interface LoginContract {

    interface View{
        void showSnackbarError(String message);
        void showHome();
    }

    interface Presenter{
        void showHome(String email, LoginActivity.ProviderType facebook);
        void showFirebaseError();
        void showFacebookError(String message);
    }

    interface Interactor{
        void facebookLogin();
        void gmailLogin();
        void login();
    }
}
