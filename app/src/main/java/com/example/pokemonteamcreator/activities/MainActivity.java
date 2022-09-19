package com.example.pokemonteamcreator.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.pokemonteamcreator.R;

public class MainActivity extends AppCompatActivity {
    public static RequestQueue requestQueue;
    @SuppressLint("StaticFieldLeak")
    public static Context context;
    public static SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        requestQueue = Volley.newRequestQueue(context);
        sharedPref = getPreferences(Context.MODE_PRIVATE);
    }

    public void pokedexSelectorActivityLaunch(View view) {
        context.startActivity(new Intent(context, PokedexSelectionActivity.class));
    }

    public void teamSelectionActivityLaunch(View view) {
        context.startActivity(new Intent(context, TeamSelectionActivity.class));
    }
}