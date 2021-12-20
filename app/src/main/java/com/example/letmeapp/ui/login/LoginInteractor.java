package com.example.letmeapp.ui.login;

import androidx.annotation.NonNull;

import com.example.letmeapp.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

public class LoginInteractor{
    private LoginContract.Presenter presenter;

    public LoginInteractor(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public void facebookLogin(CallbackManager callbackManager){
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                String accessToken = loginResult.getAccessToken().getToken();
                AuthCredential authCredential = FacebookAuthProvider.getCredential(accessToken);
                FirebaseAuth.getInstance().signInWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            presenter.showHome(task.getResult().getUser().getEmail(), LoginActivity.ProviderType.FACEBOOK);
                        }else{
                            presenter.showFirebaseError();
                        }
                    }
                });
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(@NonNull FacebookException e) {
                presenter.showFacebookError(e.getCause().toString());
            }
        });
    }
}
