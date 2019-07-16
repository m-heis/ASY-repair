package com.example.asy.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asy.CustomerOrders;
import com.example.asy.ExecServices;
import com.example.asy.R;

import java.util.ArrayList;

public class ExecAllOrdersAdapter extends RecyclerView.Adapter<ExecAllOrdersAdapter.ExecAllOrdersViewHolder> {

    private ArrayList<ExecServices> execServices;
    private onExecAllOrdersClickListener listener;

    public interface onExecAllOrdersClickListener {
        void onExecAllOrderClick(int position);
    }

    public ExecAllOrdersAdapter(ArrayList<ExecServices> execServices){
        this.execServices = execServices;
    }

    @NonNull
    @Override
    public ExecAllOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.exec_all_order_item, viewGroup, false);
        ExecAllOrdersViewHolder viewHolder = new ExecAllOrdersViewHolder(view, listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExecAllOrdersViewHolder execAllOrdersViewHolder, int i) {
        ExecServices current = execServices.get(i);
        execAllOrdersViewHolder.serviceTextView.setText(current.getService());
        execAllOrdersViewHolder.priceTextView.setText(current.getPrice());
        execAllOrdersViewHolder.descriptionTextView.setText(current.getDescription());
    }

    @Override
    public int getItemCount() {
        return execServices.size();
    }

    public class ExecAllOrdersViewHolder extends RecyclerView.ViewHolder{

        public TextView serviceTextView;
        public TextView priceTextView;
        public TextView descriptionTextView;

        public ExecAllOrdersViewHolder(@NonNull View itemView, onExecAllOrdersClickListener listener) {
            super(itemView);
            serviceTextView = itemView.findViewById(R.id.serviceTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(ExecAllOrdersAdapter.this.listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            ExecAllOrdersAdapter.this.listener.onExecAllOrderClick(position);
                        }
                    }
                }
            });
        }
    }
}
