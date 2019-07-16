package com.example.asy;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.asy.adapter.CustomerOrderAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyOrdersCustomerActivity extends AppCompatActivity {

    private DatabaseReference customerOrders;
    private ChildEventListener customerOrdersChildEventListener;

    private ArrayList<CustomerOrders> customerOrdersArrayList;
    private RecyclerView customerOrdersRecyclerView;
    private CustomerOrderAdapter customerOrderAdapter;
    private RecyclerView.LayoutManager customerOrdersLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders_customer);

        customerOrdersArrayList = new ArrayList<>();

        buildRecyclerView();
        attachCustomerOrdersReferenceListener();
    }

    private void attachCustomerOrdersReferenceListener() {
        customerOrders = FirebaseDatabase.getInstance().getReference().child("Applications");
        if(customerOrdersChildEventListener == null){
            customerOrdersChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    CustomerOrders customerOrders = dataSnapshot.getValue(CustomerOrders.class);
                    customerOrdersArrayList.add(customerOrders);
                    customerOrderAdapter.notifyDataSetChanged();
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };

            customerOrders.addChildEventListener(customerOrdersChildEventListener);
        }
    }

    private void buildRecyclerView() {
        customerOrdersRecyclerView = findViewById(R.id.customerOrdersListRecyclerView);
        customerOrdersRecyclerView.setHasFixedSize(true);
        customerOrdersLayoutManager = new LinearLayoutManager(this);
        customerOrderAdapter = new CustomerOrderAdapter(customerOrdersArrayList);

        customerOrdersRecyclerView.setLayoutManager(customerOrdersLayoutManager);
        customerOrdersRecyclerView.setAdapter(customerOrderAdapter);
    }
}
