package com.example.letmeapp.model.repository;

import android.util.Log;

import com.example.letmeapp.R;
import com.example.letmeapp.model.Item;
import com.example.letmeapp.model.User;
import com.example.letmeapp.ui.user.UserContract;
import com.example.letmeapp.utils.MyUtils;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class UserRepository implements UserContract.Repository {
    private static UserRepository repository;
    private List<User> list;

    private UserRepository(){
        list = new ArrayList<>();
        initialice();
    }

    private void initialice() {
        list.add(new User("User1", "User Uno", "user@1.com", "user1imagesrc"));
        list.add(new User("User2", "User Dos", "user@2.com", "user2imagesrc"));
        list.add(new User("User3", "User Tres", "user@3.com", "user3imagesrc"));
        list.add(new User("User4", "User Cuatro", "user@4.com", "user4imagesrc"));
        list.add(new User("User5", "User Cinco", "user@5.com", "user5imagesrc"));
    }

    public static UserRepository getInstance(){
        if (repository == null){
            repository = new UserRepository();
        }
        return repository;
    }

    public List<User> getList() {
        return list;
    }

    //region FRIENDLISTFRAGMENT METHODS
    @Override
    public void getUsersByUsernameOrMail(String user, UserContract.UserListCallback callback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(User.USERS_FIRESTORE).get().addOnSuccessListener( v-> {
            list.clear();
           v.forEach( qds -> {
               Log.d("QDS", qds.getId());
               if (qds.getId().contains(user)){
                   list.add(new User(qds.getString(User.USERNAME_FIRESTORE), qds.getString(User.NAME_FIRESTORE), qds.getId(), qds.getString(User.IMAGE_FIRESTORE)));
               }
           });
            Log.d("USER LIST SIZE", String.valueOf(list.size()));
            callback.onSuccess(list);
        }).addOnFailureListener(w -> {
            callback.onSuccess(null);
        });
    }

    @Override
    public void getFriendList(User user, UserContract.UserListCallback callback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(User.USERS_FIRESTORE).document(user.getEmail()).collection(User.FRIENDSHIP_FIRESTORE).get().addOnSuccessListener(v -> {
            list.clear();
            v.forEach(qds ->{
                if (qds.getString(User.FRIENDSHIP_STATUS_FIRESTORE).equals(User.STATUS_FRIENDS_FIRESTORE)){
                    DocumentReference dr = qds.getDocumentReference(User.FRIEND_FIRESTORE);
                    dr.get().addOnSuccessListener(x ->{
                       list.add(new User(x.getString(User.USERNAME_FIRESTORE), x.getString(User.NAME_FIRESTORE), x.getId(), x.getString(User.IMAGE_FIRESTORE)));
                       //TODO: ESPERAR A OBTENER LOS AMIGOS PARA HACER EL CALLBACK
                       callback.onSuccess(list);
                    });
                }
            });
        }).addOnFailureListener( w ->{
            callback.onFailure(R.string.strErrorUpdating);
        });
    }

    public boolean areFriends(User user, User friend){
        //TODO: COMPROBAR AMISTAD EN FIRESTORE, PARA LA TAREA 8, SIMULAREMOS QUE TODOS SON AMIGOS SIEMPRE
        return true;
    }

    @Override
    public void deleteFriend(User user, UserContract.UserListCallback callback) {

    }
    //endregion
    //region PROFILEFRAGMENT METHODS
    @Override
    public void addFriend(User user, UserContract.ProfileCallback callback) {

    }
    //endregion
}
