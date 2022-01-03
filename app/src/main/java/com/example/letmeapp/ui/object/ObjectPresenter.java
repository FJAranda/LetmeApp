package com.example.letmeapp.ui.object;

import com.example.letmeapp.model.Item;

public class ObjectPresenter implements ObjectContract.Presenter, ObjectContract.OnInteractorListener{
    private ObjectContract.View view;
    private ObjectInteractor interactor;

    public ObjectPresenter(ObjectContract.View view) {
        this.view = view;
        this.interactor = new ObjectInteractor(this);
    }

    //Interactor
    @Override
    public void add(Item item) {
        interactor.add(item);
    }

    @Override
    public void edit(Item newItem, Item oldItem) {
        interactor.edit(newItem, oldItem);
    }

    //Vista
    @Override
    public void onAddSuccess(String message) {
        view.onAddSuccess(message);
    }

    @Override
    public void onAddFailure(String message) {
        view.onAddFailure(message);
    }

    @Override
    public void onEditSuccess(String message) {
        view.onEditSuccess(message);
    }

    @Override
    public void onEditFailure(String message) {
        view.onEditFailure(message);
    }
}
