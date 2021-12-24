package com.example.letmeapp.ui.signup;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;

import androidx.annotation.NonNull;

import com.example.letmeapp.model.User;
import com.example.letmeapp.utils.MyUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpInteractor {
    SignUpContract.Interactor presenter;

    public SignUpInteractor(SignUpContract.Interactor presenter) {
        this.presenter = presenter;
    }

    public void validate(User user, String password) {
        if (TextUtils.isEmpty(user.getUsername())){
            presenter.onUserEmptyError();
            return;
        }
        if (TextUtils.isEmpty(user.getEmail())){
            presenter.onEmailEmptyError();
            return;
        }
        if (TextUtils.isEmpty(user.getPassword())){
            presenter.onPasswordEmptyError();
            return;
        }
        if (TextUtils.isEmpty(password)){
            presenter.onConfirmPasswordError();
            return;
        }
        if (user.getUsername().length() < 8){
            presenter.onUserShortError();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(user.getEmail()).matches()){
            presenter.onEmailError();
            return;
        }
        if (!MyUtils.isValidPassword(user.getPassword())){
            presenter.onPasswordError();
            return;
        }
        if (!user.getPassword().equals(password)){
            presenter.onConfirmPasswordError();
            return;
        }

        signUp(user);
    }

    private void signUp(User user) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                           presenter.onSuccess(user);
                        } else {
                            presenter.onFailure(task.getException().toString());
                        }
                    }
                });
    }
}
