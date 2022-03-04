package com.example.letmeapp.ui.object;

import com.example.letmeapp.model.Item;
import com.example.letmeapp.ui.base.IProgressView;

public interface ObjectContract {
    interface ObjectCallback{
        void onAddSuccess(String message);
        void onAddFailure(String message);
        void onEditSuccess(String message);
        void onEditFailure(String message);
    }

    interface Presenter{
        void add(Item item);
        void edit(Item newItem, Item oldItem);
    }

    interface OnInteractorListener extends ObjectContract.ObjectCallback {

    }

    interface Repository{
        void add(Item item, ObjectCallback callback);
        void edit(Item newItem, Item oldItem, ObjectCallback callback);
    }

    interface View extends IProgressView, ObjectCallback{

    }

}
