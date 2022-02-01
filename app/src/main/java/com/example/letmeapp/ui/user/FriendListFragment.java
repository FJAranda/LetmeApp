package com.example.letmeapp.ui.user;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.letmeapp.R;
import com.example.letmeapp.databinding.FragmentFriendListBinding;
import com.example.letmeapp.model.Request;
import com.example.letmeapp.model.User;
import com.example.letmeapp.ui.request.RequestListAdapter;
import com.example.letmeapp.ui.request.RequestListFragmentDirections;
import com.example.letmeapp.utils.MyUtils;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class FriendListFragment extends Fragment implements UserContract.ListView, FriendListAdapter.OnManageUserListener{
    FragmentFriendListBinding binding;
    private FriendListAdapter adapter;
    UserContract.ListPresenter presenter;
    private User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new FriendListPresenter(this);
        user = MyUtils.getUserData(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFriendListBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRVUsers();
        binding.svUsers.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("SEARCHVIEW", "QUERYTEXTSUBMIT");
                presenter.loadUsers(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //TODO: FILTRAR LA LISTA DE AMIGOS
                return false;
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.loadFriends(user);
    }

    @Override
    public void onViewUser(User user) {
        FriendListFragmentDirections.ActionNavFriendsToProfileFragment action =
                FriendListFragmentDirections.actionNavFriendsToProfileFragment(user);
        NavHostFragment.findNavController(this).navigate(action);
    }

    private void initRVUsers() {
        adapter = new FriendListAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        binding.rvUsers.setLayoutManager(linearLayoutManager);
        binding.rvUsers.setAdapter(adapter);
    }

    @Override
    public void showProgress() {
        binding.pbFriendList.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        binding.pbFriendList.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess(List<User> users) {
        adapter.update(users);
    }

    @Override
    public void onFailure(int id) {
        Snackbar.make(getView(), getString(id), BaseTransientBottomBar.LENGTH_SHORT).show();
    }
}