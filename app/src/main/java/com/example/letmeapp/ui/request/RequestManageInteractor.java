package com.example.letmeapp.ui.request;

import com.example.letmeapp.model.Item;
import com.example.letmeapp.model.Request;
import com.example.letmeapp.model.repository.ItemRepository;
import com.example.letmeapp.model.repository.RequestRepository;

public class RequestManageInteractor implements RequestContract.RequestManageCallback{
    RequestContract.RequestManageCallback listener;

    public RequestManageInteractor(RequestContract.RequestManageCallback listener) {
        this.listener = listener;
    }

    @Override
    public void onAddSuccess(Request request) {
        listener.onAddSuccess(request);
    }

    @Override
    public void onAddFailure(Request request) {
        listener.onAddFailure(request);
    }

    @Override
    public void onEditSuccess(Request request) {
        listener.onEditSuccess(request);
    }

    @Override
    public void onEditFailure(Request request) {
        listener.onEditFailure(request);
    }

    public void add(Request request){
        RequestRepository.getInstance().add(request, this);
    }

    public void edit(Request newRequest, Request oldRequest){
        RequestRepository.getInstance().edit(newRequest, oldRequest, this);
    }
}
