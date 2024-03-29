package com.example.pokemonteamcreator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

import com.example.pokemonteamcreator.data.Pokemon;
import com.example.pokemonteamcreator.data.Team;
import com.example.pokemonteamcreator.fragments.PokemonSelectionFragment;
import com.google.gson.Gson;

public class TeamCreationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_creation);
    }

    @SuppressLint("MutatingSharedPrefs")
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void saveTeamButton(View view) {
        EditText teamNameEntry = findViewById(R.id.teamCreationTeamNameEntry);
        String teamName = teamNameEntry.getText().toString();
        TextView errorTextView = findViewById(R.id.teamCreationTeamNameErrorDisplay);
        if (teamName.isEmpty()) {
            errorTextView.setText(R.string.teamNameErrorMessage);
            return;
        }
        errorTextView.setText("");

        Pokemon[] pokemon = new Pokemon[6];
        boolean pokemonAdded = false;

        FragmentContainerView fcv = findViewById(R.id.fragmentPokemonSelection1);
        PokemonSelectionFragment psv = fcv.getFragment();
        if (psv.getPokemonSelected() != null) {
            pokemonAdded = true;
            pokemon[0] = psv.getPokemonSelected();
        }

        fcv = findViewById(R.id.fragmentPokemonSelection2);
        psv = fcv.getFragment();
        if (psv.getPokemonSelected() != null) {
            pokemonAdded = true;
            pokemon[1] = psv.getPokemonSelected();
        }

        fcv = findViewById(R.id.fragmentPokemonSelection3);
        psv = fcv.getFragment();
        if (psv.getPokemonSelected() != null) {
            pokemonAdded = true;
            pokemon[2] = psv.getPokemonSelected();
        }

        fcv = findViewById(R.id.fragmentPokemonSelection4);
        psv = fcv.getFragment();
        if (psv.getPokemonSelected() != null) {
            pokemonAdded = true;
            pokemon[3] = psv.getPokemonSelected();
        }

        fcv = findViewById(R.id.fragmentPokemonSelection5);
        psv = fcv.getFragment();
        if (psv.getPokemonSelected() != null) {
            pokemonAdded = true;
            pokemon[4] = psv.getPokemonSelected();
        }

        fcv = findViewById(R.id.fragmentPokemonSelection6);
        psv = fcv.getFragment();
        if (psv.getPokemonSelected() != null) {
            pokemonAdded = true;
            pokemon[5] = psv.getPokemonSelected();
        }

        TextView noPokemonErrorText = findViewById(R.id.teamCreationPokemonErrorDisplay);
        if (!pokemonAdded) {
            noPokemonErrorText.setText(R.string.noPokemonErrorMessage);
            return;
        }
        noPokemonErrorText.setText("");

        Team team = new Team(teamName, pokemon);
        // GSON Usage found at - https://stackoverflow.com/questions/7145606/how-do-you-save-store-objects-in-sharedpreferences-on-android
        Gson gson = new Gson();
        String json = gson.toJson(team);
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);

        SharedPreferences.Editor sharedPrefEditor = sharedPref.edit();
        sharedPrefEditor.putString(teamName, json).apply();
    }
}
