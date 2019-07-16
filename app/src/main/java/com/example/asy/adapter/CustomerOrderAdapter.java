package com.example.asy.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asy.CustomerOrders;
import com.example.asy.R;

import java.util.ArrayList;

public class CustomerOrderAdapter extends RecyclerView.Adapter<CustomerOrderAdapter.CustomerOrderViewHolder> {

    private ArrayList<CustomerOrders> customerOrders;
    private onCustomerOrderClickListener listener;

    public interface onCustomerOrderClickListener {
        void onCustomerOrderClick(int position);
    }

    public void setOnCustomerOrderClickListener(onCustomerOrderClickListener listener){
        this.listener = listener;
    }

    public CustomerOrderAdapter(ArrayList<CustomerOrders> customerOrders){
        this.customerOrders = customerOrders;
    }

    @NonNull
    @Override
    public CustomerOrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.customer_order_item, viewGroup, false);
        CustomerOrderViewHolder viewHolder = new CustomerOrderViewHolder(view, listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerOrderViewHolder customerOrderViewHolder, int i) {
        CustomerOrders currentCustomerOrder = customerOrders.get(i);
        customerOrderViewHolder.carTextView.setText(currentCustomerOrder.getCar());
        customerOrderViewHolder.cityTextView.setText(currentCustomerOrder.getCity());
        customerOrderViewHolder.descriptionTextView.setText(currentCustomerOrder.getDescription());
        customerOrderViewHolder.sectionTextView.setText(currentCustomerOrder.getSection());
    }

    @Override
    public int getItemCount() {
        return customerOrders.size();
    }

    public static class CustomerOrderViewHolder extends RecyclerView.ViewHolder {

        public TextView carTextView;
        public TextView cityTextView;
        public TextView descriptionTextView;
        public TextView sectionTextView;

        public CustomerOrderViewHolder(@NonNull View itemView, final onCustomerOrderClickListener listener) {
            super(itemView);
            carTextView = itemView.findViewById(R.id.carTextView);
            cityTextView = itemView.findViewById(R.id.cityTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            sectionTextView = itemView.findViewById(R.id.sectionTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onCustomerOrderClick(position);
                        }
                    }
                }
            });
        }
    }
}
