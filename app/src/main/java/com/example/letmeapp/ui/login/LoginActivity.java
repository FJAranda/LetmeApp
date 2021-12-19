package com.example.letmeapp.ui.login;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Button;

import com.example.letmeapp.R;
import com.example.letmeapp.databinding.ActivityLoginBinding;
import com.example.letmeapp.ui.MainActivity;
import com.example.letmeapp.ui.signup.SignUpActivity;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Facebook Login
        callbackManager = CallbackManager.Factory.create();
        binding.loginButton.setReadPermissions("email");
        binding.loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("LOGINBUTTON", "ONSUCCESS");
                String accessToken = loginResult.getAccessToken().getToken();
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try{
                            String id = object.getString("id");
                            String email = object.getString("email");
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                });
                Bundle bundle = new Bundle();
                bundle.putString("fields", "id, email");
                request.setParameters(bundle);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Log.d("LOGINBUTTON", "");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("LOGINBUTTON", "");
            }
        });

        binding.btnSignIn.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        binding.btnSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
            finish();
        });

        binding.ibSignInFacebook.setOnClickListener( v-> {
            binding.loginButton.performClick();
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(checkLogin()){
            Log.d("ONSTART", "checklogin = true");
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private boolean checkLogin() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        //Facebook
        if (accessToken != null && !accessToken.isExpired()){
            Log.d("CHECKLOGIN", "facebook logged");
            return true;
        //Gmail
        }else if(true){
            Log.d("CHECKLOGIN", "gmail logged");
        //Correo
        }else if(true){
            Log.d("CHECKLOGIN", "email logged");

        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d("ONATIVITYRESULT", "");
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}