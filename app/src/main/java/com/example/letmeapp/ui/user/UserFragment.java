package com.example.letmeapp.ui.user;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.letmeapp.R;
import com.example.letmeapp.databinding.FragmentUserBinding;
import com.example.letmeapp.model.User;
import com.example.letmeapp.ui.MainActivity;
import com.example.letmeapp.utils.MyUtils;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class UserFragment extends Fragment implements UserContract.UserView {
    FragmentUserBinding binding;
    UserContract.UserPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new UserPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUserBinding.inflate(inflater);
        binding.tiledtNameUserFragment.addTextChangedListener(new UserTextWatcher(binding.tiledtNameUserFragment));
        binding.tiledtUsernameUserFragment.addTextChangedListener(new UserTextWatcher(binding.tiledtUsernameUserFragment));
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUser();
        //TODO: Change image clicking on imageview
        binding.btnEditUser.setOnClickListener(v ->{
            if (binding.tilNombreApellidos.getError() == null && binding.tilUsername.getError() == null){
            presenter.updateUser(new User(binding.tiledtUsernameUserFragment.getText().toString(), binding.tiledtNameUserFragment.getText().toString(),
                    binding.tilCorreoElectronico.getEditText().getText().toString(), "String image"));
            }else{
                Snackbar.make(getView(), getString(R.string.strUpdatingError), BaseTransientBottomBar.LENGTH_SHORT).show();
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
            binding.tiledtUsernameUserFragment.setText(user.getUsername());
            binding.tilCorreoElectronico.getEditText().setText(user.getEmail());
            binding.tvRecoverPassword.setOnClickListener( v-> {
                FirebaseAuth.getInstance().sendPasswordResetEmail(user.getEmail()).addOnCompleteListener(w->{
                    Snackbar.make(getView(), getString(R.string.strPasswordMailRecoverSent), BaseTransientBottomBar.LENGTH_SHORT).show();
                });
            });
        }else{
            binding.tilCorreoElectronico.getEditText().setText(user.getEmail());
        }
    }

    @Override
    public void showProgress() {
        binding.pbUserFragment.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        binding.pbUserFragment.setVisibility(View.GONE);
    }

    @Override
    public void onUpdateSuccess(User user) {
        Snackbar.make(getView(), getString(R.string.strUserEdited), BaseTransientBottomBar.LENGTH_SHORT).show();
        MyUtils.setUserData(getContext(), user);
    }

    @Override
    public void onUpdateFailure(User user) {
        Snackbar.make(getView(), getString(R.string.strErrorUpdating), BaseTransientBottomBar.LENGTH_SHORT).show();
    }

    @Override
    public void userLoaded(User user) {
        MyUtils.setUserData(getContext(), user);
        setUser();
    }

    //region TextWatcher
    class UserTextWatcher implements TextWatcher {
        private View view;

        UserTextWatcher(View view){
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
                case R.id.tilUsername:
                    if (s.toString().isEmpty()){
                        binding.tilUsername.setError(getString(R.string.userEmptyError));
                    }else if(s.toString().length() < 4){
                        binding.tilUsername.setError(getString(R.string.userTooShortError));
                    }else{
                        binding.tilUsername.setError(null);
                    }
                    break;
                case R.id.tilNombreApellidos:
                    if (s.toString().isEmpty()){
                        binding.tilNombreApellidos.setError(getString(R.string.strEmptyString));
                    }else if(s.toString().length() < 4){
                        binding.tilNombreApellidos.setError(getString(R.string.userTooShortError));
                    }else{
                        binding.tilNombreApellidos.setError(null);
                    }
                    break;
            }
        }
    }
    //endregion
}