package com.example.goallytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    EditText inEmail, inPassword;
    Button btsubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

//        get data
        inEmail = findViewById(R.id.inEmail);
        inPassword = findViewById(R.id.inPassword);
        btsubmit = findViewById(R.id.BLogin);

        btsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inEmail.getText().toString().equals("test@gmail.com") &&
                inPassword.getText().toString().equals("test123")){
                    Intent intent = new Intent(login.this, MainMenu.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Invalid Username and Password = "+ inEmail, Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    }
