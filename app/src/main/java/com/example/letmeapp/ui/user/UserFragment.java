package com.example.letmeapp.ui.user;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.letmeapp.R;
import com.example.letmeapp.databinding.FragmentUserBinding;
import com.example.letmeapp.model.User;
import com.example.letmeapp.utils.MyUtils;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class UserFragment extends Fragment {
    FragmentUserBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUserBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUser();
        binding.btnEditUser.setOnClickListener(v ->{
            if (binding.tilNombreApellidos.getEditText().getText().toString().trim().isEmpty() ||
                    binding.tilCorreoElectronico.getEditText().getText().toString().trim().isEmpty()){
                //TODO: TEXTWATCHERS Y ACTUALIZAR SHAREDPREFERENCES.
            }else{
                 FirebaseFirestore db = FirebaseFirestore.getInstance();
                 HashMap map = new HashMap<String, String>();
                 map.put(User.NAME_FIRESTORE, binding.tilNombreApellidos.getEditText().getText().toString());
                 db.collection(User.USERS_FIRESTORE).document(binding.tilCorreoElectronico.getEditText().getText().toString()).update(map)
                         .addOnCompleteListener( w-> {
                     Snackbar.make(getView(), getString(R.string.strUserEdited), BaseTransientBottomBar.LENGTH_SHORT).show();
                 });
            }
        });
    }

    private void setUser() {
        User user = MyUtils.getUserData(getContext());
        if (user.isCompleted()){
            try {
                Picasso.get()
                        .load(user.getImage())
                        .placeholder(R.drawable.letmeapp)
                        .error(R.drawable.letmeapp)
                        .into(binding.ivUserimage);
            }catch (Exception e){
                //Si algo falla picasso pone la imagen de error.
            }
            binding.tilNombreApellidos.getEditText().setText(user.getName());
            binding.tvUserName.setText(user.getUsername());
            binding.tilCorreoElectronico.getEditText().setText(user.getEmail());
            binding.tvRecoverPassword.setOnClickListener( v-> {
                FirebaseAuth.getInstance().sendPasswordResetEmail(user.getEmail()).addOnCompleteListener(w->{
                    Snackbar.make(getView(), getString(R.string.strPasswordMailRecoverSent), BaseTransientBottomBar.LENGTH_SHORT).show();
                });
            });
        }
    }
}