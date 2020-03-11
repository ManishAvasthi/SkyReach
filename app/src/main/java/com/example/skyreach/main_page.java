package com.example.skyreach;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class main_page extends AppCompatActivity {
    private Button create_acc;
    private Button login;
    private FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    @Override
    protected void onStart() {
        super.onStart();
        mAuth= FirebaseAuth.getInstance();
        firebaseUser= mAuth.getCurrentUser();
        if (firebaseUser != null)
        {
            Intent i=new Intent(this,Side_Navigation.class);
            startActivity(i);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        create_acc=(Button)findViewById(R.id.student);
        create_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(main_page.this, signup.class);
                startActivity(in);
                finish();
            }
        });
        login=(Button)findViewById(R.id.admin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(main_page.this,Sign_in.class);
                startActivity(i);
                finish();
            }
        });
       
    }
}