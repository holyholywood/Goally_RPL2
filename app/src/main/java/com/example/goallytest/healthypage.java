package com.example.goallytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.goallytest.Data;

public class healthypage extends AppCompatActivity {
    EditText Height, Weight, gender, Age;
    Button Inputdata, BTNBack;
    Double CalorieNeeded;
    Data data = new Data();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthypage);

        Height = findViewById(R.id.height);
        Weight = findViewById(R.id.weight);
        gender = findViewById(R.id.gender);
        Age = findViewById(R.id.Age);
        Inputdata = findViewById(R.id.BHealthdata);
        BTNBack = findViewById(R.id.BBack);
        Inputdata.setOnClickListener(c -> {
            SaveData();
            Toast.makeText(healthypage.this, "Save Succesful", Toast.LENGTH_LONG).show();
        });

        BTNBack.setOnClickListener(v -> {
            back();
        });



    }
    private void SaveData(){
            String TinggiM = Height.getText().toString();
            String BadanM = Weight.getText().toString();
            String Gender = gender.getText().toString();
            String umur = Age.getText().toString();

            double tinggiBadan = Double.parseDouble(TinggiM);
            double beratBadan = Double.parseDouble(BadanM);
            double Umur = Double.parseDouble(umur);
            if(Gender.equals("men")){
                CalorieNeeded = (66.5 + (13.8 * beratBadan) + (5 * tinggiBadan) / (6.8 * Umur))*1.3;
            }else{
                CalorieNeeded = (65.5 + (9.6 * beratBadan) + (1.9 * tinggiBadan) / (4.7 * Umur))*1.3;
            }
            int TempInt = (int) Math.round(CalorieNeeded);

            String Calorie = String.valueOf(TempInt) ;
            data.SetCalorie(getBaseContext() , Calorie);

    }
    private void back(){
            Intent intent = new Intent(healthypage.this, MainMenu.class);
            startActivity(intent);
    }
}