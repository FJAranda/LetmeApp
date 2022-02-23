package com.example.letmeapp.ui.dashboard;

import com.example.letmeapp.model.Item;

import java.util.List;

public class DashboardPresenter implements DashboardContract.Presenter, DashboardContract.ItemListCallback {
    private DashboardContract.View view;
    private DashboardInteractor interactor;
    private boolean order = false;

    public DashboardPresenter(DashboardContract.View view) {
        this.view = view;
        this.interactor = new DashboardInteractor(this);
    }

    @Override
    public void onSuccess(List<Item> list) {
        view.showData(list);
        view.hideProgress();
    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onDeleteSuccess(Item item) {
        view.hideProgress();
        view.onDeleteSuccess(item);
    }

    @Override
    public void onDestroy() {
        this.view = null;
        this.interactor = null;
    }

    @Override
    public void load(String owner) {
        view.showProgress();
        interactor.load(owner);
    }

    @Override
    public void delete(Item item) {
        view.showProgress();
        interactor.delete(item);
    }

    @Override
    public void order() {
        if (order){
            order = false;
            view.showDataInverseOrder();
        }else{
            order=true;
            view.showDataOrder();
        }
    }
}
