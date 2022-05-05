package com.example.goallytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.goallytest.Data;

public class nutrientcounter extends AppCompatActivity {
    EditText Input;
    FrameLayout BTN_input;
    TextView FoodName,SugarData, FiberData, SodiumData, FatData, CaloriesData, CholesterolData, ProteinData, CarbohydratesData;
    LinearLayout resultCard;
    Button InputEat;
    String name, sugar, fiber, sodium, total_fat, calories, cholesterol, protein, carbohydrates;

    Data data = new Data();

    //Access DB

    Data dataPref = new Data();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrientcounter);

        Input = findViewById(R.id.InputKey);
        BTN_input = findViewById(R.id.BInputkey);
        FoodName = findViewById(R.id.OutputName);
        SugarData = findViewById(R.id.OutputSugar);
        FiberData = findViewById(R.id.OutputFiber);
        SodiumData = findViewById(R.id.OutputSodium);
        FatData = findViewById(R.id.OutputFat);
        CaloriesData = findViewById(R.id.OutputCalories);
        CholesterolData = findViewById(R.id.OutputCholesterol);
        ProteinData = findViewById(R.id.OutputProtein);
        CarbohydratesData = findViewById(R.id.OutputCarboydrates);
        InputEat = findViewById(R.id.eatThis);
        boolean cancel = false;
        BTN_input.setOnClickListener(v -> {
                    if(Input.getText().toString().equals("")){
                        Input.setError("This field is required");
                        Toast.makeText(nutrientcounter.this, "Please Insert a Keyword",Toast.LENGTH_LONG).show();
                    }else {
                        String Keyword = Input.getText().toString();
                        resultCard = findViewById(R.id.result);
                        resultCard.setVisibility(View.VISIBLE);
                        InputEat.setVisibility(View.VISIBLE);
                        getdata(Keyword);
                    }
           });
        InputEat.setOnClickListener(v -> {
            double CaloCount = Double.parseDouble(CaloriesData.getText().toString());
            double CaloDb = Double.parseDouble(dataPref.getCalorie(getBaseContext()));
            double CaloOutput = CaloDb - CaloCount;
            int TempCalo = (int) Math.round(CaloOutput);
            String CaloString = String.valueOf(TempCalo);
            Data.SetCalorie(getBaseContext(), CaloString);
            Toast.makeText(nutrientcounter.this, "Succes", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(nutrientcounter.this, MainMenu.class);
            startActivity(intent);
        });



    }

    private void getdata(String Keyword) {
        String url = "https://calorieninjas.p.rapidapi.com/v1/nutrition?query="+Keyword;
        AndroidNetworking.get(url)
//                .addPathParameter("query", Keyword)
                .addHeaders("x-rapidapi-key", "80ffcf46b2mshff94566155cd519p1c2352jsn55bf18fa1b22")
                .addHeaders("x-rapidapi-host", "calorieninjas.p.rapidapi.com")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray ja_data = response.getJSONArray("items");
                            for (int i = 0; i < ja_data.length(); i++) {
                                JSONObject jObj = ja_data.getJSONObject(i);
                                name = jObj.getString("name");
                                sugar = jObj.getString("sugar_g");
                                fiber = jObj.getString("fiber_g");
                                sodium = jObj.getString("sodium_mg");
                                total_fat = jObj.getString("fat_total_g");
                                calories = jObj.getString("calories");
                                cholesterol = jObj.getString("cholesterol_mg");
                                protein = jObj.getString("protein_g");
                                carbohydrates = jObj.getString("carbohydrates_total_g");
//                                Output
                                FoodName.setText(name);
                                SugarData.setText(sugar);
                                FiberData.setText(fiber);
                                SodiumData.setText(sodium);
                                FatData.setText(total_fat);
                                CaloriesData.setText(calories);
                                CholesterolData.setText(cholesterol);
                                ProteinData.setText(protein);
                                CarbohydratesData.setText(carbohydrates);
                            }

//                            FoodName.setText("error  ");
                        }
                        catch (JSONException e){
                            Toast.makeText(nutrientcounter.this,e.getMessage() , Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }

                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }
}