package com.example.letmeapp.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.letmeapp.R;
import com.example.letmeapp.databinding.ActivityLoginBinding;
import com.example.letmeapp.model.User;
import com.example.letmeapp.ui.MainActivity;
import com.example.letmeapp.ui.signup.SignUpActivity;
import com.example.letmeapp.utils.MyUtils;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.snackbar.Snackbar;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity implements LoginContract.View{
    private ActivityLoginBinding binding;
    LoginContract.Presenter presenter;

    private final int GOOGLE_SIGN_IN = 111111;

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
        binding.btnSignUp.setOnClickListener(v -> showSignUp());

        binding.btnSignIn.setOnClickListener(v -> presenter.login(binding.tiledtUserLogin.getText().toString(), binding.tiledtPasswordLogin.getText().toString()));

        binding.ibSignInGoole.setOnClickListener(v->{
            //LOGIN GOOGLE
            GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.requestIdTokenWeb))
                    .requestEmail()
                    .build();
            GoogleSignInClient client = GoogleSignIn.getClient(this, googleSignInOptions);
            //TODO: MEJORAR USANDO REGISTERFORACTIVITYRESULT
            startActivityForResult(client.getSignInIntent(), GOOGLE_SIGN_IN);
        });

        //Comprobar autologin
        if (PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean(getString(R.string.account_key), true)) {
            //Comprobar si hay datos del usuario en sharedpreferences y en firestore
            String email = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(User.EMAIL_TAG, null);
            if (email != null) {
                presenter.getUserData(email);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GOOGLE_SIGN_IN){
            presenter.gmailLogin(data);
        }
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
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onSuccess(String email) {
        SharedPreferences.Editor sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this).edit();
        sharedPreferences.putString(User.EMAIL_TAG, email);
        sharedPreferences.apply();
        presenter.getUserData(email);
    }

    @Override
    public void onFailure(String message) {
        Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onUserDataSuccess(User user) {
        MyUtils.setUserData(this, user);
        showHome();
    }

    @Override
    public void onUserDataEmpty() {
        SharedPreferences.Editor sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this).edit();
        sharedPreferences.putBoolean(User.USER_COMPLETED_PREFERENCE, false);
        sharedPreferences.apply();
        showHome();
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