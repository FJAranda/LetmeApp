package com.example.letmeapp.ui.user;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letmeapp.R;
import com.example.letmeapp.model.Item;
import com.example.letmeapp.model.ItemComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder>{
    public ArrayList<Item> list;
    private ProfileAdapter.OnManageItemListener listener;

    public ProfileAdapter(ArrayList<Item> list, ProfileAdapter.OnManageItemListener listener) {
        this.list = list;
        this.listener = listener;
    }

    public void update(List<Item> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void orderByTipo() {
        Collections.sort(list, new ItemComparator());
        notifyDataSetChanged();
    }

    public void order(){
        Collections.sort(list);
        notifyDataSetChanged();
    }

    public void inverseOrder(){
        Collections.reverse(list);
        notifyDataSetChanged();
    }

    public void delete(Item item) {
        this.list.remove(item);
        notifyDataSetChanged();
    }

    public interface OnManageItemListener{
        void onViewItem(Item item);
        void onDeleteItem(Item item);
    }

    @NonNull
    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dashboard, parent, false);
        return new ProfileAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileAdapter.ViewHolder holder, int position) {
        holder.tvName.setText(list.get(position).getNombre());
        holder.bind(list.get(position), listener);
        //TODO: Programar me gusta e imagen del objeto
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivImage;
        ImageButton ibLike;
        TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivObjetoDashboard);
            ibLike = itemView.findViewById(R.id.ibDashboardLike);
            tvName = itemView.findViewById(R.id.tvNombreDashboardItem);
        }

        public void bind(Item item, ProfileAdapter.OnManageItemListener listener){
            itemView.setOnClickListener(v->{
                listener.onViewItem(item);
            });
        }
    }
}
