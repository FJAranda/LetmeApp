package com.example.letmeapp.ui.request;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.letmeapp.R;
import com.example.letmeapp.databinding.FragmentRequestListBinding;
import com.example.letmeapp.model.Request;
import com.example.letmeapp.ui.base.BaseDialog;

import java.util.List;

public class RequestListFragment extends Fragment implements RequestListAdapter.OnManageRequestListener, RequestContract.ListView{
    FragmentRequestListBinding binding;
    private RequestListAdapter adapter;
    RequestContract.ListPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        presenter = new RequestListPresenter(this);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.request_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_order_name:
                presenter.order();
                return true;
            case R.id.action_order_applicant:
                adapter.orderByApplicant();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

    @Override
    public void onStart() {
        super.onStart();
        presenter.load();
        Log.d("LIST FRAGMENT", "on start");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter = null;
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
        RequestListFragmentDirections.ActionNavRequestsToRequestFragment action =
                RequestListFragmentDirections.actionNavRequestsToRequestFragment(null, request);
        NavHostFragment.findNavController(this).navigate(action);
    }

    @Override
    public void onDeleteRequest(Request request) {
        Bundle bundle = new Bundle();
        bundle.putString(BaseDialog.TITLE, "Elimminar Petición");
        bundle.putString(BaseDialog.MESSAGE, "¿Desea eliminar la petición: " + request.getItem() + "?");
        getActivity().getSupportFragmentManager().setFragmentResultListener(BaseDialog.REQUEST, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                if (result.getBoolean(BaseDialog.RESULT)){
                    presenter.delete(request);
                }
            }
        });
        NavHostFragment.findNavController(this).navigate(R.id.action_nav_requests_to_baseDialog, bundle);
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
    public void onSuccess(List<Request> list) {

    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onDeleteSuccess(Request request) {
        adapter.delete(request);
    }

    @Override
    public void showData(List<Request> list) {
        adapter.update(list);
    }

    @Override
    public void showDataOrder() {
        adapter.order();
    }

    @Override
    public void showDataInverseOrder() {
        adapter.inverseOrder();
    }
}