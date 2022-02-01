package com.example.letmeapp.ui.user;

import com.example.letmeapp.model.User;
import com.example.letmeapp.ui.base.IProgressView;

import java.util.List;

public interface UserContract {
    interface ProfileCallback{
        void onAddSuccess(String message);
        void onAddFailure(String message);
        void onDeleteSuccess(String message);
        void onDeleteFailure(String message);
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

    interface ProfileView extends IProgressView, ProfileCallback{

    }

    interface ListView extends IProgressView, UserListCallback{

    }

    interface UserPresenter{
        void updateUser(User newUser);
        void loadUser(String email);
    }

    interface ProfilePresenter{
        void addFriend(User user);
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
