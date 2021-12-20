package com.example.letmeapp.ui.login;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.letmeapp.R;
import com.example.letmeapp.databinding.ActivityLoginBinding;
import com.example.letmeapp.ui.MainActivity;
import com.example.letmeapp.ui.signup.SignUpActivity;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity implements LoginContract.View{
    private ActivityLoginBinding binding;
    private CallbackManager callbackManager;
    private LoginContract.Presenter presenter;

    public enum ProviderType{
        EMAIL,
        FACEBOOK,
        GMAIL
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Facebook Login
        callbackManager = CallbackManager.Factory.create();

        //User logged check
        checkLoggedIn();

        //Buttons
        binding.btnSignIn.setOnClickListener(v -> {
            //TODO: LOGIN FIREBASE
        });

        binding.btnSignUp.setOnClickListener(v -> {
            showSignUp();
        });

        binding.ibSignInFacebook.setOnClickListener( v-> {
            facebookLogin();
        });

        binding.ibSignInGoole.setOnClickListener(v->{
            gmailLogin();
        });
    }

    private void checkLoggedIn() {
        //TODO: COMPROBAR USUARIO EN SHAREDPREFERENCES Y SI EXISTE IR A SHOWHOME
    }

    private void showSignUp() {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d("ONATIVITYRESULT", "");
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void facebookLogin() {

    }

    public void gmailLogin() {

    }

    public void login() {

    }

    //Contract methods
    public void showHome() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void showSnackbarError(String error) {
        Snackbar.make(binding.getRoot(), error, Snackbar.LENGTH_SHORT).show();
    }
}