package com.example.letmeapp.ui.login;

import android.content.Intent;

import com.example.letmeapp.model.User;
import com.facebook.AccessToken;

public class LoginPresenter implements LoginContract.Presenter, LoginContract.Interactor{
    LoginContract.View view;
    LoginInteractor interactor;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        this.interactor = new LoginInteractor(this);
    }

    @Override
    public void onSuccess(String email) {
        view.hideProgress();
        view.onSuccess(email);
    }

    @Override
    public void onFailure(String message) {
        view.hideProgress();
        view.onFailure(message);
    }

    @Override
    public void onUserDataSuccess(User user) {
        view.hideProgress();
        view.onUserDataSuccess(user);
    }

    @Override
    public void onUserDataEmpty() {
        view.hideProgress();
        view.onUserDataEmpty();
    }

    @Override
    public void onDestroy() {
        this.view = null;
        this.interactor = null;
    }

    @Override
    public void login(String email, String password) {
        view.showProgress();
        interactor.login(email, password);
    }

    @Override
    public void facebookLogin(AccessToken accessToken) {
        view.showProgress();
        interactor.facebookLogin(accessToken);
    }

    @Override
    public void gmailLogin(Intent data) {
        view.showProgress();
        interactor.gmailLogin(data);
    }

    @Override
    public void getUserData(String email) {
        view.showProgress();
        interactor.getUserData(email);
    }

    @Override
    public void onUserEmptyError() {
        view.hideProgress();
        view.setUserEmptyError();
    }

    @Override
    public void onPasswordEmptyError() {
        view.hideProgress();
        view.setPasswordEmptyError();
    }
}
