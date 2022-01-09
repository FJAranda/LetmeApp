package com.example.letmeapp.ui.request;

import com.example.letmeapp.model.Request;

public class RequestManagePresenter implements RequestContract.RequestPresenter, RequestContract.RequestManageCallback{

    private RequestContract.RequestView view;
    private RequestManageInteractor interactor;

    public RequestManagePresenter(RequestContract.RequestView view) {
        this.view = view;
        this.interactor = new RequestManageInteractor(this);
    }

    @Override
    public void add(Request request) {
        view.showProgress();
        interactor.add(request);
    }

    @Override
    public void edit(Request newRequest, Request oldRequest) {
        view.showProgress();
        interactor.edit(newRequest, oldRequest);
    }

    @Override
    public void onAddSuccess(Request request) {
        view.onAddSuccess(request);
        view.hideProgress();
    }

    @Override
    public void onAddFailure(Request request) {
        view.onAddFailure(request);
        view.hideProgress();
    }

    @Override
    public void onEditSuccess(Request request) {
        view.onEditSuccess(request);
        view.hideProgress();
    }

    @Override
    public void onEditFailure(Request request) {
        view.onEditFailure(request);
        view.hideProgress();
    }
}
