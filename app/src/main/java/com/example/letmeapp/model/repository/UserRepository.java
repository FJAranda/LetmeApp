package com.example.letmeapp.model.repository;

import com.example.letmeapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private UserRepository repository;
    private List<User> list;

    private UserRepository(){
        list = new ArrayList<>();
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
