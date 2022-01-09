package com.example.letmeapp.ui.request;

import com.example.letmeapp.model.Request;

import java.util.List;

public class RequestListPresenter implements RequestContract.ListPresenter, RequestContract.RequestListCallback{
    RequestContract.ListView view;
    RequestListInteractor interactor;
    boolean order = false;

    public RequestListPresenter(RequestContract.ListView view) {
        this.view = view;
        this.interactor = new RequestListInteractor(this);
    }

    @Override
    public void onDestroy() {
        this.view = null;
        this.interactor = null;
    }

    @Override
    public void load() {
        view.showProgress();
        interactor.load();
    }

    @Override
    public void delete(Request request) {
        view.showProgress();
        interactor.delete(request);
    }

    @Override
    public void order() {
        if (order){
            order = false;
            view.showDataInverseOrder();
        }else{
            order = true;
            view.showDataOrder();
        }
    }

    @Override
    public void onSuccess(List<Request> list) {
        view.showData(list);
        view.hideProgress();
    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onDeleteSuccess(Request request) {
        view.onDeleteSuccess(request);
        view.hideProgress();
    }
}
