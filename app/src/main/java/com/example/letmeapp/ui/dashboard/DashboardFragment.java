package com.example.letmeapp.ui.dashboard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.letmeapp.R;
import com.example.letmeapp.databinding.FragmentDashboardBinding;
import com.example.letmeapp.model.Item;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class DashboardFragment extends Fragment implements DashboardAdapter.OnManageItemListener {

    FragmentDashboardBinding binding;
    private DashboardAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater);

        binding.fab.setOnClickListener( v  ->{
            NavHostFragment.findNavController(this).navigate(R.id.action_dashboardFragment_to_objectFragment);
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRVDashboard();
        populateSpinner();
    }

    private void populateSpinner() {
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getContext(), R.array.categories_array, android.R.layout.simple_spinner_dropdown_item);
        binding.spCategorias.setAdapter(arrayAdapter);
    }

    private void initRVDashboard() {
        adapter = new DashboardAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, RecyclerView.VERTICAL, false);
        binding.rvDashboard.setLayoutManager(gridLayoutManager);
        binding.rvDashboard.setAdapter(adapter);
    }

    @Override
    public void onViewItem(Item item) {
        //Tarea 7-1
        Snackbar.make(getView(), item.getNombre(), BaseTransientBottomBar.LENGTH_SHORT).show();
        //TODO: Controlar si es para añadir o visualizar un objeto y pasar el objeto
        NavHostFragment.findNavController(this).navigate(R.id.action_dashboardFragment_to_objectFragment);
    }
}