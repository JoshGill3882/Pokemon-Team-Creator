package com.example.pokemonteamcreator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    public static RequestQueue requestQueue;
    @SuppressLint("StaticFieldLeak")
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        requestQueue = Volley.newRequestQueue(context);
    }

    public void pokedexSelectorActivityLaunch(View view) {
        Intent pokedexSelectionRedirect = new Intent(context, PokedexSelectionActivity.class);
        context.startActivity(pokedexSelectionRedirect);
    }

    public void teamSelectionActivityLaunch(View view) {
        Intent teamSelectionRedirect = new Intent(context, TeamSelectionActivity.class);
        context.startActivity(teamSelectionRedirect);
    }
}