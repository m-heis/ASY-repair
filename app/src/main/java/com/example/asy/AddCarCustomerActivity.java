package com.example.asy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class AddCarCustomerActivity extends AppCompatActivity {

    EditText brand, model, year, engineCapacity, vinCode, mileage;
    Button btnSave;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car_customer);

        brand = findViewById(R.id.editTextBrandAddCarCust);
        model = findViewById(R.id.editTextModelAddCarCust);
        year = findViewById(R.id.editTextYearAddCarCust);
        engineCapacity = findViewById(R.id.editTextEngineCapacityAddCarCust);
        vinCode = findViewById(R.id.editTextVinCodeAddCarCust);
        mileage = findViewById(R.id.editTextMileageAddCarCust);
        btnSave = findViewById(R.id.btnSaveAddCarCust);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Сохраняем машину...");

        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                saveCar();
            }
        });
    }

    private void saveCar() {
        String brandText = brand.getText().toString().trim();
        String modelText = model.getText().toString().trim();
        String yearText = year.getText().toString().trim();
        String engineCapacityText = engineCapacity.getText().toString().trim();
        String vinCodeText = mileage.getText().toString().trim();
        String mileageText = brand.getText().toString().trim();

        if(brandText.isEmpty()){
            brand.setError("PLease, fill the brand name");
            brand.setFocusable(true);
        }else{
            progressDialog.show();

            CustomerCars car = new CustomerCars(
                    brandText,
                    modelText,
                    yearText,
                    engineCapacityText,
                    vinCodeText,
                    mileageText
            );

            FirebaseDatabase.getInstance().getReference("CustomerCars")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .setValue(car).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        Toast.makeText(AddCarCustomerActivity.this, "Машина сохранена. Вы можете посмотреть список машин нажав кнопку Мои машины", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(AddCarCustomerActivity.this, MyGarageCustomerActivity.class));
                    }
                }
            });
        }
    }
}
