package com.example.asy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class WhoAmIActivity extends AppCompatActivity {

    Button btnCustomer, btnExecutor;
    FirebaseAuth firebaseAuth;
    TextView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_who_am_i);

        btnCustomer = findViewById(R.id.btnIAmCustomer);
        btnExecutor = findViewById(R.id.btnIAmExecutor);
        profile = findViewById(R.id.profileWhoAmI);

        firebaseAuth = FirebaseAuth.getInstance();

        btnCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WhoAmIActivity.this, PersonAreaOfCustActivity.class));
            }
        });

        btnExecutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WhoAmIActivity.this, PersonAreaOfExecActivity.class));
            }
        });
    }

    private void checkUserStatus(){
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null){
            profile.setText(user.getEmail());
        }
        else{
            startActivity(new Intent(WhoAmIActivity.this, MainActivity.class));
            finish();
        }
    }

    protected void onStart(){
        checkUserStatus();
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.action_logout){
            firebaseAuth.signOut();
            checkUserStatus();
        }
        return super.onOptionsItemSelected(item);
    }

}
