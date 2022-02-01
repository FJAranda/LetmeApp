package com.example.letmeapp.ui.user;

import com.example.letmeapp.model.User;
import com.example.letmeapp.model.repository.UserRepository;

import java.util.List;

public class FriendListPresenter implements UserContract.ListPresenter, UserContract.UserListCallback {
    UserContract.ListView view;
    UserInteractor interactor;

    public FriendListPresenter(UserContract.ListView view) {
        this.view = view;
        this.interactor = new UserInteractor(this);
    }

    //region PRESENTER METHODS
    @Override
    public void deleteFriend(User user) {

    }

    @Override
    public void loadFriends(User user) {
        interactor.getFriends(user);
    }

    @Override
    public void loadUsers(String user) {
        interactor.getUsersByNameOrEmail(user);
    }

    //region Callbacks
    @Override
    public void onSuccess(List<User> users) {
        view.onSuccess(users);
    }

    @Override
    public void onFailure(int id) {
        view.onFailure(id);
    }
}
