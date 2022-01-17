package com.example.letmeapp.model;

import java.io.Serializable;

public class User implements Serializable {
    public static final String UID_TAG = "USER_UID";
    public static final String USERNAME_TAG = "USER_USERNAME";
    public static final String NAME_TAG = "USER_NAME";
    public static final String EMAIL_TAG = "USER_EMAIL";
    public static final String IMAGE_TAG = "USER_IMAGE";
    public static final String USER_COMPLETED_PREFERENCE = "USER_COMPLETED";

    //FIRESTORE COLLECTION NAMES
    public static final String USERS_FIRESTORE = "users";
    public static final String FAVORITES_FIRESTORE = "favorites";
    public static final String FRIENDSHIP_FIRESTORE = "friendship";
    public static final String NAME_FIRESTORE = "name";
    public static final String INTERESTS_FIRESTORE = "interests";
    public static final String IMAGE_FIRESTORE = "image";
    public static final String USERNAME_FIRESTORE = "username";


    String UID;
    String username;
    String name;
    String email;
    String password;
    String image;

    public User(String UID, String username, String name, String email, String password, String image) {
        this.UID = UID;
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.image = image;
    }

    public User(String username, String name, String email, String password, String image) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.image = image;
    }

    public User(String username, String name, String email, String image){
        this.username = username;
        this.name = name;
        this.email = email;
        this.image = image;
    }

    public User() {
    }

    public boolean isCompleted(){
        if (this.username != null && this.name != null && this.email != null){
            if (this.username.isEmpty() || this.name.isEmpty() || this.email.isEmpty()) {
                return false;
            }
            return true;
        }
        return false;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
