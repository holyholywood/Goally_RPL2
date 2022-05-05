package com.example.goallytest;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;

public class Data extends AppCompatActivity {
    String Key;
    String Calorie;
    public static final String key = "0";

    public static SharedPreferences getSharedPreference(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void SetCalorie(Context context, String Calorie){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        String temp = getSharedPreference(context).getString(key,"");
        if(temp == null){
            editor.putString(key, Calorie);
            editor.apply();
        }else{
            editor.remove(key);
            editor.apply();
            editor.putString(key, Calorie);
            editor.apply();
        }

    }

    public String getCalorie(Context context) {
        return getSharedPreference(context).getString(key,"");
    }
}
