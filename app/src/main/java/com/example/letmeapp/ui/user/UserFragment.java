package com.example.letmeapp.ui.user;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.letmeapp.R;
import com.example.letmeapp.databinding.FragmentUserBinding;

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
}