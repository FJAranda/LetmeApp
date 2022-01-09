package com.example.letmeapp.ui.user;

import com.example.letmeapp.model.Item;
import com.example.letmeapp.model.User;
import com.example.letmeapp.ui.dashboard.DashboardContract;
import com.example.letmeapp.ui.object.ObjectContract;

public interface UserContract {
    interface Repository{
        void getFriendList(User user, DashboardContract.ItemListCallback callback);
        //Eliminar
        void deleteFriend(Item item, DashboardContract.ItemListCallback callback);
        void addFriend(Item item, ObjectContract.ObjectCallback callback);
    }
}
