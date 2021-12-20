package com.example.letmeapp.ui.signup;

import com.example.letmeapp.model.User;

public class SignUpPresenter implements SignUpContract.Presenter, SignUpContract.Interactor{
    private SignUpContract.View view;
    private SignUpInteractor interactor;

    public SignUpPresenter(SignUpContract.View view) {
        this.view = view;
        this.interactor = new SignUpInteractor(this);
    }

    @Override
    public void validate(User user, String password) {
        view.showProgress();
        interactor.validate(user, password);
    }

    @Override
    public void onSuccess(User user) {
        view.hideProgress();
        view.onSuccess(user);
    }

    @Override
    public void onFailure(String message) {
        view.hideProgress();
        view.onFailure(message);
    }

    @Override
    public void onUserEmptyError() {
        view.hideProgress();
        view.setUserEmptyError();
    }

    @Override
    public void onUserShortError() {
        view.hideProgress();
        view.setUserShortError();
    }

    @Override
    public void onEmailEmptyError() {
        view.hideProgress();
        view.setEmailEmptyError();
    }

    @Override
    public void onEmailError() {
        view.hideProgress();
        view.setEmailError();
    }

    @Override
    public void onPasswordEmptyError() {
        view.hideProgress();
        view.setPasswordEmptyError();
    }

    @Override
    public void onPasswordError() {
        view.hideProgress();
        view.setPasswordError();
    }

    @Override
    public void onConfirmPasswordError() {
        view.hideProgress();
        view.setConfirmPasswordError();
    }
}
