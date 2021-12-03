package com.example.letmeapp.ui.object;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.letmeapp.R;
import com.example.letmeapp.databinding.FragmentObjectBinding;

public class ObjectFragment extends Fragment {

    FragmentObjectBinding binding;

    public String getTAG() {
        return TAG;
    }

    private final String TAG = "ObjectFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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