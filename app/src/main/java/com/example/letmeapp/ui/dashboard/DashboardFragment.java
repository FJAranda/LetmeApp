package com.example.letmeapp.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letmeapp.R;
import com.example.letmeapp.databinding.FragmentDashboardBinding;
import com.example.letmeapp.model.Item;
import com.example.letmeapp.ui.base.BaseDialog;
import com.example.letmeapp.utils.MyUtils;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment implements DashboardContract.View, DashboardAdapter.OnManageItemListener {

    FragmentDashboardBinding binding;
    private DashboardAdapter adapter;
    private DashboardContract.Presenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        presenter = new DashboardPresenter(this);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.dashboard_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_order_tipo:
                Snackbar.make(getView(), "Items ordenados por tipo", BaseTransientBottomBar.LENGTH_SHORT).show();
                adapter.orderByTipo();
                return true;
            case R.id.action_order_name:
                Snackbar.make(getView(), "Items ordenados por nombre", BaseTransientBottomBar.LENGTH_SHORT).show();
                presenter.order();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater);

        binding.fab.setOnClickListener( v  ->{
            DashboardFragmentDirections.ActionDashboardFragmentToObjectFragment action =
                    DashboardFragmentDirections.actionDashboardFragmentToObjectFragment(null);
            NavHostFragment.findNavController(this).navigate(action);
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRVDashboard();
        populateSpinner();
        //TODO: LOAD ITEMS FROM FRIENDS OR MY ITEMS
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.load(MyUtils.getUserData(getContext()).getEmail());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    private void populateSpinner() {
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getContext(), R.array.categories_array, android.R.layout.simple_spinner_dropdown_item);
        binding.spCategorias.setAdapter(arrayAdapter);
    }

    private void initRVDashboard() {
        adapter = new DashboardAdapter(new ArrayList<>(), this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, RecyclerView.VERTICAL, false);
        binding.rvDashboard.setLayoutManager(gridLayoutManager);
        binding.rvDashboard.setAdapter(adapter);
    }

    @Override
    public void onViewItem(Item item) {
        //Tarea 7-1
        Snackbar.make(getView(), item.getNombre(), BaseTransientBottomBar.LENGTH_SHORT).show();
        //TODO: Controlar si es para añadir o visualizar un objeto y pasar el objeto
        DashboardFragmentDirections.ActionDashboardFragmentToObjectFragment action =
                DashboardFragmentDirections.actionDashboardFragmentToObjectFragment(item);
        NavHostFragment.findNavController(this).navigate(action);
    }

    @Override
    public void onDeleteItem(Item item) {
        Bundle bundle = new Bundle();
        bundle.putString(BaseDialog.TITLE, getString(R.string.strDeleteItem));
        bundle.putString(BaseDialog.MESSAGE, item.getNombre());
        getActivity().getSupportFragmentManager().setFragmentResultListener(BaseDialog.REQUEST, this, (requestKey, result) -> {
            if (result.getBoolean(BaseDialog.RESULT)){
                presenter.delete(item);
            }
        });
        NavHostFragment.findNavController(this).navigate(R.id.action_dashboardFragment_to_baseDialog, bundle);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onSuccess(List<Item> list) {
        adapter.update(list);
    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onDeleteSuccess(Item item) {
        adapter.delete(item);
        Snackbar.make(getView(), item.getNombre() + " " + getString(R.string.strWasDeleted), BaseTransientBottomBar.LENGTH_SHORT).show();
    }

    @Override
    public void showData(List<Item> list) {
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