package com.example.ecommerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {
    EditText name,email,passsowrd;
    Button signUpBtn,signInbtn;

  private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        auth= FirebaseAuth.getInstance();
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        passsowrd=findViewById(R.id.password);
        signUpBtn=findViewById(R.id.signUpBtn);
        signInbtn=findViewById(R.id.signInBtn);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
        signInbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

    }

    public void signUp()
    {
        String userName=name.getText().toString();
        String Useremail=email.getText().toString();
        String userPassword=passsowrd.getText().toString();
        if(TextUtils.isEmpty(userName))
        {
            Toast.makeText(this,"Enter Name!",Toast.LENGTH_SHORT);
            return;
        }
        if(TextUtils.isEmpty(Useremail))
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
        auth.createUserWithEmailAndPassword(Useremail,userPassword)
                .addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(RegistrationActivity.this,"Successfully Register",Toast.LENGTH_SHORT);
                            startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
                        }
                        else
                        {
                            Toast.makeText(RegistrationActivity.this,"Registration Failed"+task.getException(),Toast.LENGTH_SHORT);
                        }
                    }
                });
        Intent i=new Intent(RegistrationActivity.this,MainActivity.class);
        startActivity(i);
    }


    public void signIn()
    {
        Intent i=new Intent(RegistrationActivity.this,LoginActivity.class);
        startActivity(i);
    }
}