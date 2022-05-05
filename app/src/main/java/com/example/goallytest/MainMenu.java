package com.example.goallytest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.goallytest.Data;

public class MainMenu extends AppCompatActivity {
    CardView Btn_healthy, BTN_article, BTN_nutrientcounter;
    TextView DisplayCal;
    Data data = new Data();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_menu);
        Btn_healthy = findViewById(R.id.Bhealthy);
        BTN_article = findViewById(R.id.articleBTN);
        BTN_nutrientcounter= findViewById(R.id.Bnutrientcounter);
        DisplayCal = findViewById(R.id.DisplayCalorie);
        CalCheck();
        Btn_healthy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, healthypage.class);
                startActivity(intent);
            }
        });
        BTN_article.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, articles.class);
                startActivity(intent);
            }
        });
        BTN_nutrientcounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, nutrientcounter.class);
                startActivity(intent);
            }
        });

        
    }
    private void CalCheck(){
        if(data.getCalorie(getBaseContext()).isEmpty()){
            DisplayCal.setText("2600");
        }else{
            DisplayCal.setText(data.getCalorie(getBaseContext()));
        }
    }
}