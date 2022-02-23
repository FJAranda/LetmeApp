package com.example.letmeapp.ui.user;

import com.example.letmeapp.model.Item;
import com.example.letmeapp.model.User;
import com.example.letmeapp.ui.base.IProgressView;
import com.example.letmeapp.ui.dashboard.DashboardContract;

import java.util.List;

public interface UserContract {
    interface ProfileCallback{
        void onAddFriendSuccess();
        void onAddFriendFailure();
    }

    interface UserListCallback{
        void onSuccess(List<User> users);
        void onFailure(int id);
    }

    interface UserCallback{
        void onUpdateSuccess(User user);
        void onUpdateFailure(User user);
        void userLoaded(User user);
    }

    interface UserView extends IProgressView, UserCallback {

    }

    interface ProfileView extends IProgressView, ProfileCallback, DashboardContract.ItemListCallback {

    }

    interface ListView extends IProgressView, UserListCallback{

    }

    interface UserPresenter{
        void updateUser(User newUser);
        void loadUser(String email);
    }

    interface ProfilePresenter{
        void addFriend(User user);
        void getItemList(String owner);
    }

    interface ListPresenter{
        void deleteFriend(User user);
        void loadFriends(User user);
        void loadUsers(String user);
    }

    interface Repository{
        void getUsersByUsernameOrMail(String user, UserContract.UserListCallback callback);
        void getFriendList(User user, UserContract.UserListCallback callback);
        //Eliminar
        void deleteFriend(User user, UserContract.UserListCallback callback);
        void addFriend(User user, UserContract.ProfileCallback callback);
    }
}
