package com.example.letmeapp.model.repository;

import com.example.letmeapp.model.Item;
import com.example.letmeapp.model.dao.ItemDAO;
import com.example.letmeapp.model.database.MyDatabase;
import com.example.letmeapp.ui.dashboard.DashboardContract;
import com.example.letmeapp.ui.object.ObjectContract;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ItemRepositoryRoom implements DashboardContract.Repository, ObjectContract.Repository {
    private static ItemRepositoryRoom repository;
    private ArrayList<Item> list;
    private ItemDAO itemDAO;

    private ItemRepositoryRoom() {
        list = new ArrayList<>();
        itemDAO = MyDatabase.getDatabase().itemDAO();
    }

    public static ItemRepositoryRoom getInstance(){
        if (repository == null){
            repository = new ItemRepositoryRoom();
        }
        return repository;
    }

    @Override
    public void getList(DashboardContract.ItemListCallback callback) {
        try {
            list = (ArrayList<Item>) MyDatabase.databaseWriteExecutor.submit(() -> itemDAO.selectAll()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        callback.onSuccess(list);
    }

    public void getItemsByOwner(String owner, DashboardContract.ItemListCallback callback){
        try {
            list = (ArrayList<Item>) MyDatabase.databaseWriteExecutor.submit(() -> itemDAO.selectByOwner(owner)).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        callback.onSuccess(list);
    }

    @Override
    public void delete(Item item, DashboardContract.ItemListCallback callback) {
        MyDatabase.databaseWriteExecutor.submit(() -> itemDAO.delete(item));
        callback.onDeleteSuccess(item);
    }

    @Override
    public void add(Item item, ObjectContract.ObjectCallback callback) {
        MyDatabase.databaseWriteExecutor.submit(() -> itemDAO.insert(item));
        callback.onAddSuccess(item.getNombre() + " añadido");
    }

    @Override
    public void edit(Item newItem, Item oldItem, ObjectContract.ObjectCallback callback) {
        MyDatabase.databaseWriteExecutor.submit(() -> itemDAO.update(newItem));
        callback.onEditSuccess(newItem.getNombre() + " editado");
    }
}
