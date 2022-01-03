package com.example.letmeapp.model.repository;

import com.example.letmeapp.R;
import com.example.letmeapp.model.Item;
import com.example.letmeapp.ui.dashboard.DashboardContract;
import com.example.letmeapp.ui.object.ObjectContract;
import com.example.letmeapp.ui.object.ObjectInteractor;

import java.util.ArrayList;
import java.util.Collections;

public class ItemRepository implements DashboardContract.Repository {
    private static ItemRepository repository;
    private ArrayList<Item> list;

    private ItemRepository(){
        list = new ArrayList<>();
        initialice();
    }

    public static ItemRepository getInstance(){
        if (repository == null){
            repository = new ItemRepository();
        }
        return repository;
    }

    private void initialice() {
        list.add(new Item("Item1", "rutaimagenitem1", "Descripcion Item 1", "Tipo1", "Estado1"));
        list.add(new Item("Item2", "rutaimagenitem2", "Descripcion Item 2", "Tipo3", "Estado2"));
        list.add(new Item("Item3", "rutaimagenitem3", "Descripcion Item 3", "Tipo2", "Estado3"));
        list.add(new Item("Item4", "rutaimagenitem4", "Descripcion Item 4", "Tipo2", "Estado4"));
        list.add(new Item("Item5", "rutaimagenitem5", "Descripcion Item 5", "Tipo5", "Estado5"));
    }

    @Override
    public void getList(DashboardContract.ItemListCallback callback) {
        Collections.sort(list);
        callback.onSuccess(list);
    }

    @Override
    public void delete(Item item, DashboardContract.ItemListCallback callback) {
        list.remove(item);
        callback.onDeleteSuccess(item);
    }

    public void add(Item item, ObjectContract.ObjectCallback callback) {
        list.add(item);
        callback.onAddSuccess(item.getNombre());
    }

    public void edit(Item newItem, Item oldItem, ObjectContract.ObjectCallback callback) {
        for(Item i : list){
            if (i.equals(oldItem)){
                i.setNombre(newItem.getNombre());
                i.setDescripcion(newItem.getDescripcion());
                i.setTipo(newItem.getTipo());
                i.setEstado(newItem.getEstado());
                i.setSrcImagen(newItem.getSrcImagen());
            }
        }
        callback.onEditSuccess(newItem.getNombre());
    }
}
