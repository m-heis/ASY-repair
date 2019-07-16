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

public class AddServiceExecActivity extends AppCompatActivity {

    EditText section, price, description;
    Button btnSave;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service_exec);

        section = findViewById(R.id.editTextBrandAddCarCust);
        price = findViewById(R.id.editTextModelAddCarCust);
        description = findViewById(R.id.editTextYearAddCarCust);
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
        String sectionText = section.getText().toString().trim();
        String priceText = price.getText().toString().trim();
        String descriptionText = description.getText().toString().trim();

        if(sectionText.isEmpty()){
            section.setError("PLease, fill the brand name");
            section.setFocusable(true);
        }else{
            progressDialog.show();

            ExecServices car = new ExecServices(
                    sectionText,
                    priceText,
                    descriptionText
            );

            FirebaseDatabase.getInstance().getReference("ServicesOfExec")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .setValue(car).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        Toast.makeText(AddServiceExecActivity.this, "Машина сохранена. Вы можете посмотреть список машин нажав кнопку Мои машины", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(AddServiceExecActivity.this, PersonAreaOfExecActivity.class));
                    }
                }
            });
        }
    }
}
