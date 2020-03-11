package com.example.skyreach;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class signup extends AppCompatActivity {
    private Button create;
    EditText uname;
    EditText upassword;
    EditText uemail;
    ProgressBar progressBar;
    private FirebaseAuth auth;
    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth=FirebaseAuth.getInstance();
        db=FirebaseDatabase.getInstance().getReference();
        create=(Button)findViewById(R.id.login);
        uname=(EditText) findViewById(R.id.name);
        uemail=(EditText) findViewById(R.id.email);
        upassword=(EditText) findViewById(R.id.password);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                final String name=uname.getText().toString().trim();
                final String email=uemail.getText().toString().trim();
                final String pass=upassword.getText().toString().trim();

                if(TextUtils.isEmpty(name))
                {
                    Toast.makeText(signup.this,"Please Enter Name",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(signup.this,"Please Enter Email",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(pass))
                {
                    Toast.makeText(signup.this,"Please Enter Password",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (pass.length()<6)
                {
                    Toast.makeText(signup.this,"That Password is too short(or too long).Please make sure your Password is between 6 and 512 characters.",
                            Toast.LENGTH_LONG).show();

                }


                auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if(task.isSuccessful())
                        {
                            String id=FirebaseAuth.getInstance().getUid();
                            Map<String,String> map=new HashMap<>();
                            map.put("username",name);
                            map.put("email",email);
                            map.put("password",pass);
                            db.child("USERS").child(id).setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void Void) {
                                    Toast.makeText(signup.this,"SUCCESSFULLY REGISTERED",Toast.LENGTH_LONG).show();
                                }
                            });
                            Intent i=new Intent(signup.this,Side_Navigation.class);
                            startActivity(i);
                            finish();
                        }

                    }
                });

            }
        });

    }
    /*void createusers(final String name, final String email, final String pass)
    {
        auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

            }
        });
    }*/
}
