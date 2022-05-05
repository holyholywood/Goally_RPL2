package com.example.goallytest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

public class articles extends AppCompatActivity {
    Button Barticle1, Barticle2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
        Barticle1 = findViewById(R.id.Barticle1);
        Barticle1.setOnClickListener(v ->{
            String url ="https://www.helpguide.org/articles/healthy-eating/healthy-eating.htm";
            Uri uri = Uri.parse(url);
            Intent intent= new Intent(Intent.ACTION_VIEW,uri);
            startActivity(intent);
        });
        Barticle2 = findViewById(R.id.Barticle2);
        Barticle2.setOnClickListener(v ->{
            String url ="https://www.helpguide.org/articles/healthy-eating/healthy-eating.htm";
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            startActivity(intent);
        });
    }
}