package com.example.letmeapp.ui.user;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letmeapp.R;
import com.example.letmeapp.model.Request;
import com.example.letmeapp.model.User;
import com.example.letmeapp.ui.request.RequestListAdapter;

import java.util.ArrayList;
import java.util.List;

public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.ViewHolder> {
    public ArrayList<User> list;
    public OnManageUserListener listener;

    interface OnManageUserListener{
        void onViewUser(User user);
    }

    public FriendListAdapter(OnManageUserListener listener){
        list = new ArrayList<>();
        this.listener = listener;
    }

    public void update(List<User> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend, parent, false);
        return new FriendListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //TODO: SETEAR IMAGEN
        holder.tvUsername.setText(list.get(position).getUsername());
        holder.tvUserEmail.setText(list.get(position).getEmail());
        holder.bind(list.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivUserImage;
        TextView tvUsername;
        TextView tvUserEmail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivUserImage = itemView.findViewById(R.id.ivFriendItem);
            tvUsername = itemView.findViewById(R.id.tvUsernameFriendItem);
            tvUserEmail = itemView.findViewById(R.id.tvEmailFriendItem);
        }

        public void bind(User user, FriendListAdapter.OnManageUserListener listener){
            itemView.setOnClickListener(v->{
                listener.onViewUser(user);
            });
        }
    }
}
