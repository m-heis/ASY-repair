package com.example.asy;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.asy.adapter.CustomerOrderAdapter;
import com.example.asy.adapter.ExecAllOrdersAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AllOrdersExecActivity extends AppCompatActivity {

    private DatabaseReference execServices;
    private ChildEventListener execServicesChildEventListener;

    private ArrayList<ExecServices> ExecServicesArrayList;
    private RecyclerView ExecServicesRecyclerView;
    private ExecAllOrdersAdapter ExecServicesAdapter;
    private RecyclerView.LayoutManager ExecServicesLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_orders_exec);

        ExecServicesArrayList = new ArrayList<>();

        buildRecyclerView();
        attachCustomerOrdersReferenceListener();
    }

    private void attachCustomerOrdersReferenceListener() {
        execServices = FirebaseDatabase.getInstance().getReference().child("ServicesOfExec");
        if(execServicesChildEventListener == null){
            execServicesChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    ExecServices execServices = dataSnapshot.getValue(ExecServices.class);
                    ExecServicesArrayList.add(execServices);
                    ExecServicesAdapter.notifyDataSetChanged();
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

            execServices.addChildEventListener(execServicesChildEventListener);
        }
    }

    private void buildRecyclerView() {
        ExecServicesRecyclerView = findViewById(R.id.execServicesListRecyclerView);
        ExecServicesRecyclerView.setHasFixedSize(true);
        ExecServicesLayoutManager = new LinearLayoutManager(this);
        ExecServicesAdapter = new ExecAllOrdersAdapter(ExecServicesArrayList);

        ExecServicesRecyclerView.setLayoutManager(ExecServicesLayoutManager);
        ExecServicesRecyclerView.setAdapter(ExecServicesAdapter);
    }
}
