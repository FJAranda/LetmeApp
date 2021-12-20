package com.example.letmeapp.ui.login;

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
    public void onFacebookFailure() {

    }

    @Override
    public void onDestroy() {
        this.view = null;
        this.interactor = null;
    }

    @Override
    public void login(String email, String password) {

    }

    @Override
    public void facebookLogin() {

    }

    @Override
    public void gmailLogin() {

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
