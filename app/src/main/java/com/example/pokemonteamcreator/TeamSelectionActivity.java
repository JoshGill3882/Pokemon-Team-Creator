package com.example.pokemonteamcreator;

import static com.example.pokemonteamcreator.MainActivity.context;
import static com.example.pokemonteamcreator.MainActivity.sharedPref;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pokemonteamcreator.adapters.TeamViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class TeamSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_selection);

        String teamNames = sharedPref.getString("teamNames", null);
        if (teamNames == null) {
            System.out.println("No Teams Present in Shared Preferences");
            TextView topTextView = findViewById(R.id.teamSelectionText);
            topTextView.setText(R.string.teamSelectionErrorText);
        } else {
            String[] teamNamesArray = teamNames.split(",");
            System.out.println(teamNames);
            System.out.println(teamNamesArray.length);

            // Populate ListView with buttons showing the text of Team Names
            // StackOverflow to reference - https://stackoverflow.com/questions/40862154/how-to-create-listview-items-button-in-each-row
            ListView teamDisplayList = findViewById(R.id.teamSelectionListView);
            teamDisplayList.setAdapter(new TeamViewAdapter(new ArrayList<>(Arrays.asList(teamNamesArray)), context));
        }
    }

    public void launchPokedexSelection(View view) {
        context.startActivity(new Intent(context, PokedexSelectionActivity.class));
    }
}