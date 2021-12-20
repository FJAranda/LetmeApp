package com.example.letmeapp.ui.login;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;

import com.example.letmeapp.R;
import com.example.letmeapp.databinding.ActivityLoginBinding;
import com.example.letmeapp.model.User;
import com.example.letmeapp.ui.MainActivity;
import com.example.letmeapp.ui.base.IProgressView;
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
    LoginContract.Presenter presenter;

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
        presenter = new LoginPresenter(this);

        //textwatchers
        binding.tiledtUserLogin.addTextChangedListener(new LoginTextWatcher(binding.tiledtUserLogin));
        binding.tiledtPasswordLogin.addTextChangedListener(new LoginTextWatcher(binding.tiledtPasswordLogin));

        //Buttons
        binding.btnSignUp.setOnClickListener(v -> {
            showSignUp();
        });

        binding.btnSignIn.setOnClickListener(v -> {
            //TODO: LOGIN FIREBASE
            presenter.login(binding.tiledtUserLogin.getText().toString(), binding.tiledtPasswordLogin.getText().toString());

        });

        binding.ibSignInFacebook.setOnClickListener( v-> {
            //TODO: LOGIN FACEBOOK
        });

        binding.ibSignInGoole.setOnClickListener(v->{
            //TODO: LOGIN GOOGLE
        });
    }

    private void showSignUp() {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }

    public void showHome() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showProgress() {
        binding.pbLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        binding.pbLogin.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess(String email) {
        //TODO: SAVE EMAIL TO SHARED PREFERENCES AND GO TO MAINACTIVITY
        SharedPreferences.Editor sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this).edit();
        sharedPreferences.putString(User.EMAIL_TAG, email);
        sharedPreferences.apply();
        showHome();
    }

    @Override
    public void onFailure(String message) {
        Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onFacebookFailure() {
        Snackbar.make(binding.getRoot(), "Facebook login error", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setUserEmptyError() {
        binding.tilUserLogin.setError(getString(R.string.strEmptyString));
    }

    @Override
    public void setPasswordEmptyError() {
        binding.tilPasswordLogin.setError(getString(R.string.strEmptyString));
    }

    //region textWatcher
    class LoginTextWatcher implements TextWatcher{
        private View view;

        LoginTextWatcher(View view){
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (view.getId()){
                case R.id.tiledtUserLogin:
                    if (TextUtils.isEmpty(s.toString())){
                        binding.tilUserLogin.setError(getString(R.string.userEmptyError));
                    }else{
                        binding.tilUserLogin.setError(null);
                    }
                    break;
                case R.id.tiledtPasswordLogin:
                    if (TextUtils.isEmpty(s.toString())){
                        binding.tilPasswordLogin.setError(getString(R.string.passwordEmptyError));
                    }else {
                        binding.tilPasswordLogin.setError(null);
                    }
                    break;
            }
        }
    }
    //endregion
}