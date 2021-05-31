package com.example.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SecondActivity extends AppCompatActivity
{
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button b1, b2, b3, b4, logout;
        TextView welcome;

        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);

        logout = findViewById(R.id.logout);
        welcome = findViewById(R.id.welcome);

        auth = FirebaseAuth.getInstance();
        final FirebaseUser user = auth.getCurrentUser();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                if(user == null)
                {
                    startActivity(new Intent(SecondActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };

        String welcomeMessage = "Welcome, \n"+user.getEmail()+"!";
        welcome.setText(welcomeMessage);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
                startActivity(new Intent(SecondActivity.this, MainActivity.class));
            }
        });

        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openActivityA();
            }
        });

        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openActivityB();
            }
        });

        b3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openActivityC();
            }
        });

        b4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openActivityD();
            }
        });
    }

    public void openActivityA()
    {
        Intent intent = new Intent(this, BookList.class);
        startActivity(intent);
    }

    public void openActivityB()
    {
        Intent intent = new Intent(this, GameList.class);
        startActivity(intent);
    }

    public void openActivityC()
    {
        Intent intent = new Intent(this, ArtList.class);
        startActivity(intent);
    }

    public void openActivityD()
    {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }

    public void logout()
    {
        auth.signOut();
    }
}
