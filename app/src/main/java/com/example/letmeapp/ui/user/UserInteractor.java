package com.example.letmeapp.ui.user;

import com.example.letmeapp.R;
import com.example.letmeapp.model.User;
import com.example.letmeapp.model.repository.UserRepository;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;

public class UserInteractor implements UserContract.UserListCallback {
    UserContract.UserCallback userCallback;
    UserContract.ProfileCallback profileCallback;
    UserContract.UserListCallback listCallback;

    public UserInteractor(UserContract.UserCallback callback) {
        this.userCallback = callback;
    }

    public UserInteractor(UserContract.ProfileCallback profileCallback) {
        this.profileCallback = profileCallback;
    }

    public UserInteractor(UserContract.UserListCallback listCallback) {
        this.listCallback = listCallback;
    }

    public void updateUser(User user){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        HashMap map = new HashMap<String, String>();
        map.put(User.NAME_FIRESTORE, user.getName());
        map.put(User.USERNAME_FIRESTORE, user.getUsername());
        db.collection(User.USERS_FIRESTORE).document(user.getEmail()).update(map)
                .addOnCompleteListener( w-> {
                    userCallback.onUpdateSuccess(user);
                })
                .addOnFailureListener( v-> {
                    userCallback.onUpdateFailure(user);
                });
    }

    public void loadUser(String email){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(User.USERS_FIRESTORE).document(email).get().addOnSuccessListener(v->{
            User user = new User(v.getString(User.USERNAME_FIRESTORE), v.getString(User.NAME_FIRESTORE),email ,v.getString(User.IMAGE_FIRESTORE));
            userCallback.userLoaded(user);
        })
        .addOnFailureListener(w->{
            userCallback.userLoaded(null);
        });
    }

    public void getUsersByNameOrEmail(String user){
        UserRepository.getInstance().getUsersByUsernameOrMail(user, listCallback);

    }

    public void getFriends(User user){
        UserRepository.getInstance().getFriendList(user, listCallback);
    }

    //region USERLISTCALLBACK
    @Override
    public void onSuccess(List<User> users) {
        listCallback.onSuccess(users);
    }

    @Override
    public void onFailure(int id) {
        listCallback.onFailure(id);
    }
    //endregion
}
