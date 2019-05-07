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

public class MakeOrderMainActivity extends AppCompatActivity {

    EditText city, section, car, description, phone;
    Button btnSave;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_order_main);

        city = findViewById(R.id.editTextCityMakeOrderMain);
        section = findViewById(R.id.editTextSectionMakeOrderMain);
        description = findViewById(R.id.editTextDescriptionMakeOrderMain);
        car = findViewById(R.id.editTextCarMakeOrderMain);
        phone = findViewById(R.id.editTextPhoneMakeOrderMain);
        btnSave = findViewById(R.id.btnSaveApplicationMain);

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
        String phoneText = phone.getText().toString().trim();

        if(sectionText.isEmpty()){
            section.setError("PLease, fill the section");
            section.setFocusable(true);
        }else{
            progressDialog.show();

            CommonOrders application = new CommonOrders(
                    cityText,
                    sectionText,
                    descriptionText,
                    carText,
                    phoneText
            );

            FirebaseDatabase.getInstance().getReference("CommonApplications")
                    .setValue(application).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        Toast.makeText(MakeOrderMainActivity.this, "Ваша заявка отправлена всем СТО и продавцам автозапчастей", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(MakeOrderMainActivity.this, MainActivity.class));
                    }
                }
            });
        }
    }
}
