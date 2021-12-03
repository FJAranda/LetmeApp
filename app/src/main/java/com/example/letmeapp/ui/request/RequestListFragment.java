package com.example.letmeapp.ui.request;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.letmeapp.R;
import com.example.letmeapp.databinding.FragmentRequestListBinding;
import com.example.letmeapp.model.Request;

public class RequestListFragment extends Fragment implements RequestListAdapter.OnManageRequestListener{
    FragmentRequestListBinding binding;
    private RequestListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRequestListBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRVRequests();
        populateSpinner();
    }

    private void initRVRequests() {
        adapter = new RequestListAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        binding.rvRequest.setLayoutManager(linearLayoutManager);
        binding.rvRequest.setAdapter(adapter);
    }

    private void populateSpinner() {
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getContext(), R.array.request_status_array, android.R.layout.simple_spinner_dropdown_item);
        binding.spTipoPeticion.setAdapter(arrayAdapter);
    }

    @Override
    public void onViewRequest(Request request) {
        //TODO: Pasar el objeto
        NavHostFragment.findNavController(this).navigate(R.id.action_nav_requests_to_requestFragment);
    }
}