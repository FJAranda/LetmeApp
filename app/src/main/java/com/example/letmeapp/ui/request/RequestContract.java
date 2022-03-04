package com.example.letmeapp.ui.request;

import com.example.letmeapp.model.Request;
import com.example.letmeapp.ui.base.IProgressView;

import java.util.List;

public interface RequestContract {
    interface RequestListCallback{
        void onSuccess(List<Request> list);
        void onFailure(String message);
        void onDeleteSuccess(Request request);
    }

    interface RequestManageCallback{
        void onAddSuccess(Request request);
        void onAddFailure(Request request);
        void onEditSuccess(Request request);
        void onEditFailure(Request request);
    }

    interface ListView extends IProgressView, RequestListCallback {
        void showData(List<Request> list);
        void showDataOrder();
        void showDataInverseOrder();
    }

    interface RequestView extends IProgressView, RequestManageCallback {

    }

    interface ListPresenter{
        void onDestroy();
        void load(String applicant);
        void delete(Request request);
        void order();
    }

    interface RequestPresenter{
        void add(Request request);
        void edit(Request newRequest, Request oldRequest);
    }

    interface Repository{
        void getList(RequestListCallback callback);
        void delete(Request request, RequestListCallback callback);
        void add(Request request, RequestManageCallback callback);
        void edit(Request newRequest, Request oldRequest, RequestManageCallback callback);
    }
}
