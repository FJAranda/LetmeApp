package com.example.letmeapp.model.repository;

import com.example.letmeapp.model.Item;
import com.example.letmeapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private UserRepository repository;
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

    public UserRepository getInstance(){
        if (this.repository == null){
            repository = new UserRepository();
        }
        return repository;
    }

    public List<User> getList() {
        return list;
    }
}
