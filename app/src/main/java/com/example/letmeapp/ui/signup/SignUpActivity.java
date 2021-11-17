package com.example.letmeapp.ui.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Toast;

import com.example.letmeapp.R;
import com.example.letmeapp.databinding.ActivitySignUpBinding;
import com.example.letmeapp.ui.login.LoginActivity;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {
    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnNewSignUp.setOnClickListener(v -> {
            validarDatos();
        });

    }

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
}