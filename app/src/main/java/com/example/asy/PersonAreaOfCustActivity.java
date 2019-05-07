package com.example.asy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PersonAreaOfCustActivity extends AppCompatActivity {

    Button btnGarage, btnOrders, btnMakeAnOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_area_of_cust);

        btnGarage = findViewById(R.id.btnMyGarageCust);
        btnMakeAnOrder = findViewById(R.id.btnMakeOrderCust);
        btnOrders = findViewById(R.id.btnMyOrdersCust);

        btnMakeAnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PersonAreaOfCustActivity.this, MakeOrderCustomerActivity.class));
            }
        });

        btnGarage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PersonAreaOfCustActivity.this, MyGarageCustomerActivity.class));
            }
        });

        btnOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PersonAreaOfCustActivity.this, MyOrdersCustomerActivity.class));
            }
        });
    }
}
