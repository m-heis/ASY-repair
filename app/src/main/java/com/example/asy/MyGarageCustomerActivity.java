package com.example.asy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyGarageCustomerActivity extends AppCompatActivity {

    Button btnMyCars, btnAddCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_garage_customer);

        btnMyCars = findViewById(R.id.btnMyCarsCustomer);
        btnAddCar = findViewById(R.id.btnAddCarCustomer);

        btnMyCars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyGarageCustomerActivity.this, MyCarsCustomerActivity.class));
            }
        });

        btnAddCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyGarageCustomerActivity.this, AddCarCustomerActivity.class));
            }
        });
    }
}
