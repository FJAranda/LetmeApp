package com.example.letmeapp.ui.dashboard;

import com.example.letmeapp.model.Item;
import com.example.letmeapp.ui.base.IProgressView;

import java.util.List;

public interface DashboardContract {
    interface ItemListCallback{
        void onSuccess(List<Item> list);
        void onFailure(String message);
        void onDeleteSuccess(Item item);
    }

    interface View extends IProgressView, ItemListCallback{
        void showData(List<Item> list);
        void showDataOrder();
        void showDataInverseOrder();
    }

    interface Presenter{
        void onDestroy();
        void load();
        void delete(Item item);
        void order();
    }

    interface OnInteractorListener extends ItemListCallback {

    }

    interface Repository{
        //Carga datos
        void getList(ItemListCallback callback);
        //Eliminar
        void delete(Item item, ItemListCallback callback);
    }
}
