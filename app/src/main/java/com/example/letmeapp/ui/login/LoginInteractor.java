package com.example.letmeapp.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginInteractor {
    LoginContract.Interactor presenter;
    public LoginInteractor(LoginContract.Interactor presenter) {
        this.presenter = presenter;
    }

    public void login(String email, String password){
        if (email.isEmpty()){
            presenter.onUserEmptyError();
        }else if (password.isEmpty()){
            presenter.onPasswordEmptyError();
        }else {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                presenter.onSuccess(email);
                            } else {
                                presenter.onFailure(task.getException().getCause().toString());
                            }
                        }
                    });
        }
    }

    public void gmailLogin(Intent data){
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            if (account != null) {
                FirebaseAuth.getInstance().signInWithCredential(GoogleAuthProvider.getCredential(account.getIdToken(), null))
                        .addOnCompleteListener( v ->{
                            if (v.isSuccessful()){
                                presenter.onSuccess(account.getEmail());
                            }else{
                                presenter.onFailure(task.getException().getCause().toString());
                            }
                        });
            }else{
                presenter.onFailure(task.getException().getCause().toString());
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public void facebookLogin(AccessToken accessToken){
        AuthCredential authCredential = FacebookAuthProvider.getCredential(accessToken.getToken());
        FirebaseAuth.getInstance().signInWithCredential(authCredential).addOnCompleteListener(v -> {
            if (v.isSuccessful()){
                presenter.onSuccess(v.getResult().getUser().getEmail());
            }else{
                presenter.onFailure(v.getException().getCause().toString());
            }
        });
    }
}
