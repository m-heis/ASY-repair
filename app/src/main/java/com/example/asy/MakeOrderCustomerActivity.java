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

public class MakeOrderCustomerActivity extends AppCompatActivity {

    EditText city, section, car, description;
    Button btnSave;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_order_customer);

        city = findViewById(R.id.editTextCityMakeOrderCust);
        section = findViewById(R.id.editTextSectionMakeOrderCust);
        description = findViewById(R.id.editTextDescriptionMakeOrderCust);
        car = findViewById(R.id.editTextCarMakeOrderCust);
        btnSave = findViewById(R.id.btnSaveApplicationCust);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Обрабатываем вашу заявку...");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveApplication();
            }
        });
    }

    private void saveApplication() {
        String sectionText = section.getText().toString().trim();
        String descriptionText = description.getText().toString().trim();
        String carText = car.getText().toString().trim();
        String cityText = city.getText().toString().trim();

        if(sectionText.isEmpty()){
            section.setError("PLease, fill the section");
            section.setFocusable(true);
        }else{
            progressDialog.show();

            CustomerOrders application = new CustomerOrders(
                    cityText,
                    sectionText,
                    descriptionText,
                    carText
            );

            FirebaseDatabase.getInstance().getReference("Applications")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .setValue(application).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        Toast.makeText(MakeOrderCustomerActivity.this, "Ваша заявка отправлена всем СТО и продавцам автозапчастей", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(MakeOrderCustomerActivity.this, PersonAreaOfCustActivity.class));
                    }
                }
            });
        }
    }
}
