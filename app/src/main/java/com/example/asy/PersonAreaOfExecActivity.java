package com.example.asy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PersonAreaOfExecActivity extends AppCompatActivity {

    Button addService, myServices, myOrders, allOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_area_of_exec);

        addService = findViewById(R.id.btnAddServiceExec);
        myServices = findViewById(R.id.btnMyServicesExec);
        myOrders = findViewById(R.id.btnMyOrdersExec);
        allOrders = findViewById(R.id.btnAllOrdersExec);

        addService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PersonAreaOfExecActivity.this, AddServiceExecActivity.class));
            }
        });

        myServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PersonAreaOfExecActivity.this, MyServicesExecActivity.class));
            }
        });

        myOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PersonAreaOfExecActivity.this, MyOrdersExecActivity.class));
            }
        });

        allOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PersonAreaOfExecActivity.this, AllOrdersExecActivity.class));
            }
        });
    }
}
