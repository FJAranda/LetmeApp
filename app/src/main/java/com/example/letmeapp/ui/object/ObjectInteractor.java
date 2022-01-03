package com.example.letmeapp.ui.object;

import com.example.letmeapp.model.Item;
import com.example.letmeapp.model.repository.ItemRepository;

public class ObjectInteractor implements ObjectContract.ObjectCallback{
    ObjectContract.OnInteractorListener listener;

    public ObjectInteractor(ObjectContract.OnInteractorListener listener) {
        this.listener = listener;
    }

    @Override
    public void onAddSuccess(String message) {
        listener.onAddSuccess(message);
    }

    @Override
    public void onAddFailure(String message) {
        listener.onAddFailure(message);
    }

    @Override
    public void onEditSuccess(String message) {
        listener.onEditSuccess(message);
    }

    @Override
    public void onEditFailure(String message) {
        listener.onEditFailure(message);
    }

    public void add(Item item){
        ItemRepository.getInstance().add(item, this);
    }

    public void edit(Item newItem, Item oldItem){
        ItemRepository.getInstance().edit(newItem, oldItem, this);
    }
}
