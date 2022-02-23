package com.example.letmeapp.ui.user;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.letmeapp.R;
import com.example.letmeapp.databinding.FragmentProfileBinding;
import com.example.letmeapp.model.Item;
import com.example.letmeapp.model.Request;
import com.example.letmeapp.model.User;
import com.example.letmeapp.ui.dashboard.DashboardAdapter;
import com.example.letmeapp.ui.dashboard.DashboardFragmentDirections;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment implements UserContract.ProfileView, ProfileAdapter.OnManageItemListener{
    FragmentProfileBinding binding;
    ProfilePresenter presenter;
    ProfileAdapter adapter;
    //Simulacion de que siempre son amigos para poder realizar peticiones
    boolean friends = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ProfilePresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        User user = ProfileFragmentArgs.fromBundle(getArguments()).getUser();
        setView(user);
    }

    private void setView(User user) {
        //TODO: SET IMAGE
        //binding.ivProfileImage.setImageDrawable(user.getImage());
        binding.tvProfileUsername.setText(user.getUsername());
        setItems();
    }

    private void setItems() {
        adapter = new ProfileAdapter(new ArrayList<>(), this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, RecyclerView.VERTICAL, false);
        binding.rvProfileItems.setLayoutManager(gridLayoutManager);
        binding.rvProfileItems.setAdapter(adapter);
        presenter.getItemList(ProfileFragmentArgs.fromBundle(getArguments()).getUser().getEmail());
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onAddFriendSuccess() {

    }

    @Override
    public void onAddFriendFailure() {

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

    }

    @Override
    public void onViewItem(Item item) {
        //TODO: ABRIR REQUEST PARA EL ITEM
        ProfileFragmentDirections.ActionProfileFragmentToObjectFragment action =
                ProfileFragmentDirections.actionProfileFragmentToObjectFragment(item);
        NavHostFragment.findNavController(this).navigate(action);
    }

    @Override
    public void onDeleteItem(Item item) {

    }
}