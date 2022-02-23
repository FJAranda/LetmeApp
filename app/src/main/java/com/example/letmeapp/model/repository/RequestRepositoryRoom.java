package com.example.letmeapp.model.repository;

import com.example.letmeapp.model.Item;
import com.example.letmeapp.model.Request;
import com.example.letmeapp.model.dao.RequestDAO;
import com.example.letmeapp.model.database.MyDatabase;
import com.example.letmeapp.ui.request.RequestContract;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class RequestRepositoryRoom implements RequestContract.Repository {
    private static RequestRepositoryRoom repository;
    private ArrayList<Request> list;
    private RequestDAO requestDAO;

    private RequestRepositoryRoom() {
        list = new ArrayList<>();
        requestDAO = MyDatabase.getDatabase().requestDAO();
    }

    public static RequestRepositoryRoom getInstance(){
        if (repository == null){
            repository = new RequestRepositoryRoom();
        }
        return repository;
    }

    public List<Request> getList(){
        try {
            list = (ArrayList<Request>) MyDatabase.databaseWriteExecutor.submit(() -> requestDAO.selectAll()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void getList(RequestContract.RequestListCallback callback) {
        try {
            list = (ArrayList<Request>) MyDatabase.databaseWriteExecutor.submit(() -> requestDAO.selectAll()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        callback.onSuccess(list);
    }

    public void getRequestsByApplicant(String applicant, RequestContract.RequestListCallback callback){
        try {
            list = (ArrayList<Request>) MyDatabase.databaseWriteExecutor.submit(() -> requestDAO.selectByApplicant(applicant)).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        callback.onSuccess(list);
    }

    @Override
    public void delete(Request request, RequestContract.RequestListCallback callback) {
        MyDatabase.databaseWriteExecutor.submit(() -> requestDAO.delete(request));
        callback.onDeleteSuccess(request);
    }

    @Override
    public void add(Request request, RequestContract.RequestManageCallback callback) {
        MyDatabase.databaseWriteExecutor.submit(() -> requestDAO.insert(request));
        callback.onAddSuccess(request);
    }

    @Override
    public void edit(Request newRequest, Request oldRequest, RequestContract.RequestManageCallback callback) {
        MyDatabase.databaseWriteExecutor.submit(() -> requestDAO.update(newRequest));
        callback.onEditSuccess(newRequest);
    }
}
