package com.example.ecommerceapp;

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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
EditText etEmail,etPassword;
Button signUpBtn,signInBtn;
    private FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth= FirebaseAuth.getInstance();
   signInBtn=findViewById(R.id.signInBtn);
   signUpBtn=findViewById(R.id.signUpBtn);
        etEmail=findViewById(R.id.email);
        etPassword=findViewById(R.id.password);
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }
        public void signUp()
        {
            Intent i=new Intent(LoginActivity.this,RegistrationActivity.class);
            startActivity(i);
        }
        public void signIn()
        {
            String userEmail=etEmail.getText().toString();
            String userPassword=etPassword.getText().toString();
            if(TextUtils.isEmpty(userEmail))
            {
                Toast.makeText(this,"Enter Email!",Toast.LENGTH_SHORT);
                return;
            }
            if(TextUtils.isEmpty(userPassword))
            {
                Toast.makeText(this,"Enter Password!",Toast.LENGTH_SHORT);
                return;
            }
            if(userPassword.length()<6)
            {
                Toast.makeText(this,"Password is too short,Enter minimum 6 character",Toast.LENGTH_SHORT);
                return;
            }
            auth.signInWithEmailAndPassword(userEmail,userPassword)
                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(LoginActivity.this,"Login succesful",Toast.LENGTH_SHORT);

                                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this,"Error"+task.getException(),Toast.LENGTH_SHORT);

                            }
                        }
                    });
        }
}