package com.example.skyreach;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forget_Password extends AppCompatActivity {
    private EditText userEmail;
    private Button sendEmail;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget__password);
        userEmail=(EditText)findViewById(R.id.forgot_password);
        sendEmail=(Button)findViewById(R.id.Reset);
        mAuth=FirebaseAuth.getInstance();

        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=userEmail.getText().toString().trim();
                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(Forget_Password.this,"Please Enter valid Email",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(Forget_Password.this,"Please visit your email to Reset your Password",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),Sign_in.class));
                            }
                            else
                            {
                                String message=task.getException().getMessage();
                                Toast.makeText(Forget_Password.this,"Error"+message,Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
