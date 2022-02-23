package com.example.letmeapp.ui.user;

import com.example.letmeapp.model.Item;
import com.example.letmeapp.model.User;
import com.example.letmeapp.ui.dashboard.DashboardContract;

import java.util.List;

public class ProfilePresenter implements UserContract.ProfilePresenter, UserContract.ProfileCallback, DashboardContract.ItemListCallback {
    UserContract.ProfileView view;
    ProfileInteractor interactor;

    public ProfilePresenter(UserContract.ProfileView view) {
        this.view = view;
        this.interactor = new ProfileInteractor(this, this);
    }

    @Override
    public void addFriend(User user) {
        interactor.loadFriendItems(user.getEmail());
    }

    @Override
    public void getItemList(String owner) {
        interactor.loadFriendItems(owner);
    }

    @Override
    public void onSuccess(List<Item> list) {
        view.onSuccess(list);
    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onDeleteSuccess(Item item) {

    }

    @Override
    public void onAddFriendSuccess() {

    }

    @Override
    public void onAddFriendFailure() {

    }
}
