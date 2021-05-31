package com.example.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity
{
    private EditText email, password;
    private FirebaseAuth auth;
    private Button login, signup;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                finish();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputEmail = email.getText().toString();
                String inputPassword = password.getText().toString();

                if(TextUtils.isEmpty(inputEmail))
                {
                    Toast.makeText(SignupActivity.this, "Enter email!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(inputPassword))
                {
                    Toast.makeText(SignupActivity.this, "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(inputPassword.length()<6)
                {
                    Toast.makeText(SignupActivity.this, "Password needs to be at least 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.createUserWithEmailAndPassword(inputEmail, inputPassword)
                        .addOnCompleteListener(SignupActivity.this, task -> {
                           if(!task.isSuccessful())
                           {
                               Toast.makeText(SignupActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                           }
                           else
                           {
                               startActivity(new Intent(SignupActivity.this, SecondActivity.class));
                               finish();
                           }
                        });
            }
        });
    }
}
