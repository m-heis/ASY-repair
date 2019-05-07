package com.example.asy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText regNameEditText, regSurnameEditText, regPhoneEditText, regEmailEditText, regPasswordEditText, regConfirmPassEditText;
    Button regPageRegBtn;
    ProgressDialog progressDialog;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regNameEditText = findViewById(R.id.regPageNameEditText);
        regSurnameEditText = findViewById(R.id.regPageSurnameEditText);
        regPhoneEditText = findViewById(R.id.regPagePhoneEditText);
        regEmailEditText = findViewById(R.id.regPageEmailEditText);
        regPasswordEditText = findViewById(R.id.regPagePasswordEditText);
        regConfirmPassEditText = findViewById(R.id.regPageConfirmPassEditText);
        regPageRegBtn = findViewById(R.id.regPageRegisterBtn);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registering User...");

        mAuth = FirebaseAuth.getInstance();

        regPageRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        final String name = regNameEditText.getText().toString().trim();
        final String surname = regSurnameEditText.getText().toString().trim();
        final String phone = regPhoneEditText.getText().toString().trim();
        final String email = regEmailEditText.getText().toString().trim();
        String password = regPasswordEditText.getText().toString().trim();
        String confirmPass = regConfirmPassEditText.getText().toString().trim();
        if(name.isEmpty()){
            regNameEditText.setError("PLease, fill your name");
            regNameEditText.setFocusable(true);
        }
        else if(surname.isEmpty()){
            regSurnameEditText.setError("PLease, fill your surname");
            regSurnameEditText.setFocusable(true);
        }
        else if(phone.isEmpty()){
            regPhoneEditText.setError("PLease, fill your phone number");
            regPhoneEditText.setFocusable(true);
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            regEmailEditText.setError("Email address is invalid");
            regEmailEditText.setFocusable(true);
        }
        else if(password.length() < 6){
            regPasswordEditText.setError("Password must be at least 6 characters");
            regPasswordEditText.setFocusable(true);
        }
        else if(!password.matches(confirmPass)){
            regConfirmPassEditText.setError("Passwords does not match");
            regConfirmPassEditText.setFocusable(true);
        }else{
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                Users users = new Users(
                                        name,
                                        surname,
                                        phone,
                                        email
                                );
                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            progressDialog.dismiss();
                                            FirebaseUser user = mAuth.getCurrentUser();
                                            Toast.makeText(RegisterActivity.this, "Registered...\n" + user.getEmail(), Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(RegisterActivity.this, WhoAmIActivity.class));
                                            finish();
                                        }else{
                                            progressDialog.dismiss();
                                            Toast.makeText(RegisterActivity.this, "Database failed...", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(RegisterActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();;
                }
            });
        }
    }
}