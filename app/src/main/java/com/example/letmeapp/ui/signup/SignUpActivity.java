package com.example.letmeapp.ui.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.letmeapp.R;
import com.example.letmeapp.databinding.ActivitySignUpBinding;
import com.example.letmeapp.model.User;
import com.example.letmeapp.ui.MainActivity;
import com.example.letmeapp.ui.login.LoginActivity;
import com.example.letmeapp.utils.MyUtils;
import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity implements SignUpContract.View{
    private ActivitySignUpBinding binding;
    private SignUpContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new SignUpPresenter(this);

        binding.tiledtUserSignUp.addTextChangedListener(new SignUpTextWatcher(binding.tiledtUserSignUp));
        binding.tiledtMailSignUp.addTextChangedListener(new SignUpTextWatcher(binding.tiledtMailSignUp));
        binding.tiledtPasswordSignUp.addTextChangedListener(new SignUpTextWatcher(binding.tiledtPasswordSignUp));
        binding.tiledtConfirmPasswordSignUp.addTextChangedListener(new SignUpTextWatcher(binding.tiledtConfirmPasswordSignUp));

        binding.btnNewSignUp.setOnClickListener(v -> {
            presenter.validate(new User(binding.tiledtUserSignUp.getText().toString(),
                    binding.tiledtNombreSignUp.getText().toString(),
                    binding.tiledtMailSignUp.getText().toString(),
                    binding.tiledtPasswordSignUp.getText().toString(),
                    ""), binding.tiledtConfirmPasswordSignUp.getText().toString());
        });
    }

    void showMainActivity(){
        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    //region Contract methods
    @Override
    public void showProgress() {
        binding.pbSignUp.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        binding.pbSignUp.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess(User user) {
        SharedPreferences.Editor sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this).edit();
        sharedPreferences.putString(User.UID_TAG, user.getUID());
        sharedPreferences.putString(User.USERNAME_TAG, user.getUsername());
        sharedPreferences.putString(User.NAME_TAG, user.getName());
        sharedPreferences.putString(User.EMAIL_TAG, user.getEmail());
        sharedPreferences.putString(User.IMAGE_TAG, user.getImage());
        sharedPreferences.apply();
        showMainActivity();
    }

    @Override
    public void onFailure(String message) {
        Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_SHORT).show();
    }
    @Override
    public void setUserEmptyError() {
        binding.tilUsuario.setError(getString(R.string.strEmptyString));
    }

    @Override
    public void setUserShortError() {
        binding.tilUsuario.setError(getString(R.string.userTooShortError));
    }

    @Override
    public void setEmailEmptyError() {
        binding.tilCorreoElectronico.setError(getString(R.string.strEmptyString));
    }

    @Override
    public void setEmailError() {
        binding.tilCorreoElectronico.setError(getString(R.string.emailFormatError));
    }

    @Override
    public void setPasswordEmptyError() {
        binding.tilPassword.setError(getString(R.string.strEmptyString));
    }

    @Override
    public void setPasswordError() {
        binding.tilPassword.setError(getString(R.string.strPasswordTooEasy));
    }

    @Override
    public void setConfirmPasswordError() {
        binding.tilConfirmPassword.setError(getString(R.string.strPasswordsNotMatch));
    }
    //endregion

    //region TextWatcher
    class SignUpTextWatcher implements TextWatcher{
        private View view;

        SignUpTextWatcher(View view){
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
                case R.id.tiledtUserSignUp:
                    if (s.toString().isEmpty()){
                        binding.tilUsuario.setError(getString(R.string.userEmptyError));
                    }else if(s.toString().length() < 4){
                        binding.tilUsuario.setError(getString(R.string.userTooShortError));
                    }else{
                        binding.tilUsuario.setError(null);
                    }
                    break;
                case R.id.tiledtMailSignUp:
                    if (s.toString().isEmpty()){
                        binding.tilCorreoElectronico.setError(getString(R.string.strEmptyString));
                    }else if(!Patterns.EMAIL_ADDRESS.matcher(s.toString()).matches()){
                        binding.tilCorreoElectronico.setError(getString(R.string.emailFormatError));
                    }else{
                        binding.tilCorreoElectronico.setError(null);
                    }
                    break;
                case R.id.tiledtPasswordSignUp:
                    if (s.toString().isEmpty()){
                        binding.tilPassword.setError(getString(R.string.strEmptyString));
                    }else if(!MyUtils.isValidPassword(s.toString())){
                        binding.tilPassword.setError(getString(R.string.strPasswordTooEasy));
                    }else{
                        binding.tilPassword.setError(null);
                    }
                    break;
                case R.id.tiledtConfirmPasswordSignUp:
                    if (s.toString().isEmpty()){
                        binding.tilConfirmPassword.setError(getString(R.string.strEmptyString));
                    }else{
                        binding.tilConfirmPassword.setError(null);
                    }
                    break;
            }
        }
    }
    //endregion

    //region old validation methods
    private boolean esNombreValido(String nombre) {
        Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
        if (!patron.matcher(nombre).matches() || nombre.length() > 30) {
            binding.tilNombreApellidos.setError("Nombre inválido");
            return false;
        } else {
            binding.tilNombreApellidos.setError(null);
        }

        return true;
    }

    private boolean esTelefonoValido(String telefono) {
        if (!Patterns.PHONE.matcher(telefono).matches()) {
            binding.tilTelefono.setError("Teléfono inválido");
            return false;
        } else {
            binding.tilTelefono.setError(null);
        }

        return true;
    }

    private boolean esCorreoValido(String correo) {
        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            binding.tilCorreoElectronico.setError("Correo electrónico inválido");
            return false;
        } else {
            binding.tilCorreoElectronico.setError(null);
        }

        return true;
    }

    private void validarDatos() {
        String nombre = binding.tilNombreApellidos.getEditText().getText().toString();
        String telefono = binding.tilTelefono.getEditText().getText().toString();
        String correo = binding.tilCorreoElectronico.getEditText().getText().toString();

        if (esNombreValido(nombre) && esCorreoValido(correo) && esTelefonoValido(telefono)) {
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
    //endregion
}