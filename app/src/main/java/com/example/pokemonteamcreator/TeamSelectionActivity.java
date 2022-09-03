package com.example.pokemonteamcreator;

import static com.example.pokemonteamcreator.MainActivity.sharedPref;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

public class TeamSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_selection);

        String teamNames = sharedPref.getString("teamNames", null);
        if (teamNames == null) {
            System.out.println("No Teams Present in Shared Preferences");
        } else {
            String[] teamNamesArray = teamNames.split(",");
            System.out.println(teamNames);
            System.out.println(teamNamesArray.length);
        }
    }
}