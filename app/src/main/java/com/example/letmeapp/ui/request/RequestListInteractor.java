package com.example.letmeapp.ui.request;

import com.example.letmeapp.model.Request;
import com.example.letmeapp.model.repository.RequestRepository;

import java.util.List;

public class RequestListInteractor implements RequestContract.RequestListCallback{
    RequestContract.RequestListCallback callback;

    public RequestListInteractor(RequestContract.RequestListCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onSuccess(List<Request> list) {
        callback.onSuccess(list);
    }

    @Override
    public void onFailure(String message) {
        callback.onFailure(message);
    }

    @Override
    public void onDeleteSuccess(Request request) {
        callback.onDeleteSuccess(request);
    }

    public void load(){
        RequestRepository.getInstance().getList(this);
    }

    public void delete(Request request){
        RequestRepository.getInstance().delete(request, callback);
    }
}
