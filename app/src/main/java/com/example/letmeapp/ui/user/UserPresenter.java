package com.example.letmeapp.ui.user;

import com.example.letmeapp.model.User;

public class UserPresenter implements UserContract.UserPresenter, UserContract.UserCallback {
    private UserContract.UserView view;
    private UserInteractor interactor;

    public UserPresenter(UserContract.UserView view) {
        this.view = view;
        interactor = new UserInteractor(this);
    }

    @Override
    public void updateUser(User newUser) {
        interactor.updateUser(newUser);
        view.showProgress();
    }

    @Override
    public void loadUser(String email) {
        interactor.loadUser(email);
    }

    @Override
    public void onUpdateSuccess(User user) {
        view.onUpdateSuccess(user);
        view.hideProgress();
    }

    @Override
    public void onUpdateFailure(User user) {
        view.hideProgress();
        view.onUpdateFailure(user);
    }

    @Override
    public void userLoaded(User user) {
        view.hideProgress();
        view.userLoaded(user);
    }
}
