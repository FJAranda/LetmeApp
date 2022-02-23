package com.example.letmeapp.ui.object;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.letmeapp.R;
import com.example.letmeapp.databinding.FragmentObjectBinding;
import com.example.letmeapp.model.Item;
import com.example.letmeapp.ui.dashboard.DashboardFragmentDirections;
import com.example.letmeapp.utils.MyUtils;
import com.google.android.material.snackbar.Snackbar;

public class ObjectFragment extends Fragment implements ObjectContract.View{

    FragmentObjectBinding binding;
    ObjectPresenter presenter;
    Item oldItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ObjectPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentObjectBinding.inflate(inflater);
        binding.tietNombreObjeto.addTextChangedListener(new ObjectTextWatcher(binding.tietNombreObjeto));
        binding.tietDisponibilidadObjeto.addTextChangedListener(new ObjectTextWatcher(binding.tietDisponibilidadObjeto));
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //TODO: If object do not belong to me, view mode with request option
        if (ObjectFragmentArgs.fromBundle(getArguments()).getItem() != null){
            oldItem = ObjectFragmentArgs.fromBundle(getArguments()).getItem();
            if (oldItem.getOwner() != MyUtils.getUserData(getContext()).getEmail()){
                getActivity().setTitle(getString(R.string.strViewItem));
                binding.tvObject.setText(R.string.strViewItem);
                setButtonRequestMode();
                setView(oldItem);
            }else {
                getActivity().setTitle(getString(R.string.strEditItem));
                binding.tvObject.setText(R.string.strEditItem);
                setButtonEditMode();
                setView(oldItem);
            }
        }//Add Mode
        else{
            getActivity().setTitle(getString(R.string.strAddObject));
            setButtonAddMode();
        }
    }

    private void setButtonRequestMode() {
        ObjectFragmentDirections.ActionObjectFragmentToRequestFragment action =
                ObjectFragmentDirections.actionObjectFragmentToRequestFragment(oldItem, null);
        NavHostFragment.findNavController(this).navigate(action);
    }

    private void setButtonAddMode() {
        binding.btnObjeto.setOnClickListener(v->{
            presenter.add(getItem());
        });
    }

    private void setButtonEditMode() {
        binding.btnObjeto.setText(getString(R.string.strEditItem));
        //Using object name until DB implemented
        binding.tietNombreObjeto.setEnabled(false);
        binding.btnObjeto.setOnClickListener(v->{
            presenter.edit(getItem(), oldItem);
        });
    }

    private void setView(Item item) {
        //TODO: Set image
        binding.tietNombreObjeto.setText(item.getNombre());
        binding.tietDisponibilidadObjeto.setText(item.getEstado());
        binding.tietDescripcionObjeto.setText(item.getDescripcion());
    }

    private Item getItem() {
        Item item = new Item(binding.tietNombreObjeto.getText().toString(), "imagenDesarrollo",
                binding.tietDescripcionObjeto.getText().toString(), binding.tilTipo.getEditText().getText().toString(), binding.tietDisponibilidadObjeto.getText().toString());
        item.setOwner(MyUtils.getUserData(getContext()).getEmail());
        if (oldItem != null){
            item.setId(oldItem.getId());
        }
        return item;
    }

    @Override
    public void showProgress() {
        binding.pbObjectFragment.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        binding.pbObjectFragment.setVisibility(View.GONE);
    }

    @Override
    public void onAddSuccess(String message) {
        Snackbar.make(getView(), message + " " + getString(R.string.strWasAdded), Snackbar.LENGTH_SHORT).show();
        NavHostFragment.findNavController(this).navigateUp();
    }

    @Override
    public void onAddFailure(String message) {

    }

    @Override
    public void onEditSuccess(String message) {
        Snackbar.make(getView(), message + " " + getString(R.string.strEdited), Snackbar.LENGTH_SHORT).show();
        NavHostFragment.findNavController(this).navigateUp();
    }

    @Override
    public void onEditFailure(String message) {

    }

    class ObjectTextWatcher implements TextWatcher{
        private View view;

        ObjectTextWatcher (View view){
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
                case R.id.tietNombreObjeto:
                    validateNombreObjeto(s.toString());
                    break;
                case R.id.tietDisponibilidadObjeto:
                    validateDisponibilidadObjeto(s.toString());
                    break;
            }
        }
    }

    private void validateDisponibilidadObjeto(String str) {
        if (TextUtils.isEmpty(str)){
            binding.tilDisponibilidadObjeto.setError(getString(R.string.strEmptyString));
        }else{
            binding.tilDisponibilidadObjeto.setError(null);
        }
    }

    private void validateNombreObjeto(String str) {
        if (TextUtils.isEmpty(str)){
            binding.tilNombreObjeto.setError(getString(R.string.strEmptyString));
        }else{
            binding.tilNombreObjeto.setError(null);
        }
    }
}