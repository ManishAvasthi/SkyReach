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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Sign_in extends AppCompatActivity {
    private TextView fp;
    private Button signin;
    EditText txtEmail,txtPassword;
    ProgressBar progressbar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        fp=(TextView)findViewById(R.id.forgot);
        fp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Sign_in.this,Forget_Password.class);
                startActivity(i);
                finish();
            }
        });

        progressbar=(ProgressBar)findViewById(R.id.progressbar);
        txtEmail=(EditText)findViewById(R.id.email);
        txtPassword=(EditText)findViewById(R.id.pass);
        signin=(Button)findViewById(R.id.login_in);
        mAuth=FirebaseAuth.getInstance();


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressbar.setVisibility(View.VISIBLE);
                final String email=txtEmail.getText().toString().trim();
                final String pass=txtPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(Sign_in.this,"Please Enter Email",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(pass))
                {
                    Toast.makeText(Sign_in.this,"Please Enter Password",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (pass.length()<6)
                {
                    Toast.makeText(Sign_in.this,"Incorrect Email and Password",Toast.LENGTH_LONG).show();

                }

                mAuth.signInWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(Sign_in.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressbar.setVisibility(View.GONE);
                                if (task.isSuccessful())
                                    {
                                        startActivity(new Intent(getApplicationContext(),Side_Navigation.class));
                                        finish();

                                    }
                                else
                                    {
                                     Toast.makeText(Sign_in.this,"Incorrect Email and Password",Toast.LENGTH_SHORT).show();
                                     return;
                                    }
                                finish();
                            }
                        });

            }

        });




    }
}
