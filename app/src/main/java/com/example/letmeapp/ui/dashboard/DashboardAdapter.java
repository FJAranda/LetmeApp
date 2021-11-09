package com.example.letmeapp.ui.dashboard;

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

import java.util.ArrayList;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ViewHolder> {

    public ArrayList<Item> list;

    public DashboardAdapter() {
        this.list = new ArrayList<Item>();
        list.add(new Item("Item1", "rutaimagenitem1", "Descripcion Item 1", "Tipo1", "Estado1"));
        list.add(new Item("Item2", "rutaimagenitem2", "Descripcion Item 2", "Tipo2", "Estado2"));
        list.add(new Item("Item3", "rutaimagenitem3", "Descripcion Item 3", "Tipo3", "Estado3"));
        list.add(new Item("Item4", "rutaimagenitem4", "Descripcion Item 4", "Tipo4", "Estado4"));
        list.add(new Item("Item5", "rutaimagenitem5", "Descripcion Item 5", "Tipo5", "Estado5"));
    }

    @NonNull
    @Override
    public DashboardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dashboard, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardAdapter.ViewHolder holder, int position) {
        holder.tvName.setText(list.get(position).getNombre());
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
    }
}
