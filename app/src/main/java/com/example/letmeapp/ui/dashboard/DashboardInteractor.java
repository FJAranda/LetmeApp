package com.example.letmeapp.ui.dashboard;

import com.example.letmeapp.model.Item;
import com.example.letmeapp.model.repository.ItemRepositoryRoom;

import java.util.List;

public class DashboardInteractor implements DashboardContract.ItemListCallback{
    DashboardContract.ItemListCallback listener;

    public DashboardInteractor(DashboardContract.ItemListCallback listener) {
        this.listener = listener;
    }

    @Override
    public void onSuccess(List<Item> list) {
        listener.onSuccess(list);
    }

    @Override
    public void onFailure(String message) {
        listener.onFailure(message);
    }

    @Override
    public void onDeleteSuccess(Item item) {
        listener.onDeleteSuccess(item);
    }

    public void load(String owner){
        ItemRepositoryRoom.getInstance().getItemsByOwner(owner,this);
    }

    public void delete(Item item){
        ItemRepositoryRoom.getInstance().delete(item, this);
    }
}
