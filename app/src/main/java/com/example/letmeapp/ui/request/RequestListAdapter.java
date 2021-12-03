package com.example.letmeapp.ui.request;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letmeapp.R;
import com.example.letmeapp.model.Request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RequestListAdapter extends RecyclerView.Adapter<RequestListAdapter.ViewHolder> {

    public ArrayList<Request> list;
    private OnManageRequestListener listener;

    interface OnManageRequestListener{
        void onViewRequest(Request request);
    }

    public RequestListAdapter(OnManageRequestListener listener) {
        list = new ArrayList<>();
        list.add(new Request("Item 1", "Jose123","Emprestamelo", new Date(), new Date(), "Pending"));
        list.add(new Request("Item 2", "Juan12","Emprestamelo a mi mejon", new Date(), new Date(), "Rejected"));
        list.add(new Request("Item 3", "Javivi","Illo, emprestamelo", new Date(), new Date(), "Accepted"));
        this.listener = listener;
    }

    public void update(List<Request> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_request, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //TODO: SETEAR LOS OBJETOS REQUEST
        holder.tvNombreobjeto.setText(list.get(position).getItem());
        holder.bind(list.get(position), listener);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImagenObjeto;
        TextView tvNombreobjeto;
        TextView tvFechaFinRequest;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImagenObjeto = itemView.findViewById(R.id.ivRequestItem);
            tvNombreobjeto = itemView.findViewById(R.id.tvRequestItemName);
            tvFechaFinRequest = itemView.findViewById(R.id.tvEndRequestDate);
        }

        public void bind(Request request, OnManageRequestListener listener){
            itemView.setOnClickListener(v->{
                listener.onViewRequest(request);
            });
        }
    }
}
