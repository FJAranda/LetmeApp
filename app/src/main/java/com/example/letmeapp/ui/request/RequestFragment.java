package com.example.letmeapp.ui.request;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.letmeapp.R;
import com.example.letmeapp.databinding.FragmentRequestBinding;
import com.example.letmeapp.model.Request;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

public class RequestFragment extends Fragment implements RequestContract.RequestView {
    FragmentRequestBinding binding;
    RequestManagePresenter presenter;
    Request oldRequest;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new RequestManagePresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRequestBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Si recibe un item, nueva request (Simulado con el Item 1)
        if(RequestFragmentArgs.fromBundle(getArguments()).getItem() != null){
            binding.ivObject.setImageResource(R.drawable.img_objeto);
            binding.tvObjectName.setText(RequestFragmentArgs.fromBundle(getArguments()).getItem().getNombre());
            setButtonNewRequestMode();
        }
        else if (RequestFragmentArgs.fromBundle(getArguments()).getRequest() != null){
            //Si recibe un request, modify request(Simulado en la lista de requests)
            oldRequest = RequestFragmentArgs.fromBundle(getArguments()).getRequest();
            setModifyRequest(oldRequest);
        }
    }

    private void setModifyRequest(Request request) {
        binding.ivObject.setImageResource(R.drawable.img_objeto);
        binding.tvObjectName.setText(request.getItem());
        binding.tilRequestMessage.getEditText().setText(request.getMessage());
        binding.tilLendDuration.getEditText().setText(request.getStartDate().toString() + " - " + request.getEndDate().toString());
        setButtonModifyRequestMode();
    }

    private void setButtonModifyRequestMode() {
        binding.btnEnviar.setOnClickListener( v ->{
            presenter.edit(new Request(
                    binding.tvObjectName.getText().toString(),
                    //getUserFromPreferences
                    "applicant",
                    binding.tilRequestMessage.getEditText().getText().toString(),
                    Calendar.getInstance().getTime(),
                    Calendar.getInstance().getTime(),
                    "estado"), oldRequest);
        });
    }

    private void setButtonNewRequestMode() {
        binding.btnEnviar.setOnClickListener(v -> {
            presenter.add(new Request(
                    binding.tvObjectName.getText().toString(),
                    //getUserFromPreferences
                    "applicant",
                    binding.tilRequestMessage.getEditText().getText().toString(),
                    Calendar.getInstance().getTime(),
                    Calendar.getInstance().getTime(),
                    "estado"));
        });
    }

    @Override
    public void showProgress() {
        //Futuro
    }

    @Override
    public void hideProgress() {
        //Futuro
    }

    @Override
    public void onAddSuccess(Request request) {
        Snackbar.make(getView(), request.getItem() + ": Peticion enviada con exito.", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onAddFailure(Request request) {

    }

    @Override
    public void onEditSuccess(Request request) {
        Snackbar.make(getView(), request.getItem() + ": Peticion modificada enviada con exito.", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onEditFailure(Request request) {

    }
}