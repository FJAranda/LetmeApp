package com.example.letmeapp.model.repository;

import android.util.Log;

import com.example.letmeapp.R;
import com.example.letmeapp.model.Item;
import com.example.letmeapp.model.Request;
import com.example.letmeapp.ui.request.RequestContract;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class RequestRepository implements RequestContract.Repository {
    private static RequestRepository repository;
    private ArrayList<Request> list;

    private RequestRepository(){
        list = new ArrayList<>();
        initialice();
    }

    public static RequestRepository getInstance(){
        if (repository == null){
            repository = new RequestRepository();
        }
        return repository;
    }

    private void initialice() {
        list.add(new Request("Item2", "mi amigo", "Illo emprestamelo", Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), "estado"));
        
    }

    @Override
    public void getList(RequestContract.RequestListCallback callback) {
        Log.d("List size", String.valueOf(list.size()));
        Collections.sort(list);
        callback.onSuccess(list);
    }

    @Override
    public void delete(Request request, RequestContract.RequestListCallback callback) {
        list.remove(request);
        callback.onDeleteSuccess(request);
    }

    @Override
    public void add(Request request, RequestContract.RequestManageCallback callback) {
        Log.d("Repository", "request added");
        list.add(request);
        callback.onAddSuccess(request);
    }

    @Override
    public void edit(Request newRequest, Request oldRequest, RequestContract.RequestManageCallback callback) {
        for(Request i : list){
            if (i.equals(oldRequest)){
                i.setMessage(newRequest.getMessage());
                i.setEndDate(newRequest.getEndDate());
                i.setStartDate(newRequest.getStartDate());
                i.setStatus(newRequest.getStatus());
            }
        }
        callback.onEditSuccess(newRequest);
    }
}
