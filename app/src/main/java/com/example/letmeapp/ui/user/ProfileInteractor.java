package com.example.letmeapp.ui.user;

import com.example.letmeapp.model.Item;
import com.example.letmeapp.model.repository.ItemRepositoryRoom;
import com.example.letmeapp.ui.dashboard.DashboardContract;

import java.util.List;

public class ProfileInteractor implements UserContract.ProfileCallback, DashboardContract.ItemListCallback {

    DashboardContract.ItemListCallback listCallback;
    UserContract.ProfileCallback profileCallback;

    public ProfileInteractor(DashboardContract.ItemListCallback listCallback, UserContract.ProfileCallback profileCallback) {
        this.listCallback = listCallback;
        this.profileCallback = profileCallback;
    }

    public void loadFriendItems(String user){
        ItemRepositoryRoom.getInstance().getItemsByOwner(user, this);
    }

    @Override
    public void onAddFriendSuccess() {

    }

    @Override
    public void onAddFriendFailure() {

    }

    @Override
    public void onSuccess(List<Item> list) {
        listCallback.onSuccess(list);
    }

    @Override
    public void onFailure(String message) {
        listCallback.onFailure(message);
    }

    @Override
    public void onDeleteSuccess(Item item) {

    }
}
