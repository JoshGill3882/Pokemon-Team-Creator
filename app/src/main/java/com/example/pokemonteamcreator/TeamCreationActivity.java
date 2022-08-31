package com.example.pokemonteamcreator;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

import com.example.pokemonteamcreator.data.Pokemon;
import com.example.pokemonteamcreator.fragments.PokemonSelectionFragment;

public class TeamCreationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_creation);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void saveTeamButton(View view) {
        EditText teamNameEntry = findViewById(R.id.teamCreationTeamNameEntry);
        String teamName = teamNameEntry.getText().toString();

        Pokemon[] pokemon = new Pokemon[6];

        FragmentContainerView fcv = findViewById(R.id.fragmentPokemonSelection1);
        PokemonSelectionFragment psv = fcv.getFragment();
        if (psv.getPokemonSelected() != null) {
            pokemon[0] = psv.getPokemonSelected();
        }

        fcv = findViewById(R.id.fragmentPokemonSelection2);
        psv = fcv.getFragment();
        if (psv.getPokemonSelected() != null) {
            pokemon[1] = psv.getPokemonSelected();
        }

        fcv = findViewById(R.id.fragmentPokemonSelection3);
        psv = fcv.getFragment();
        if (psv.getPokemonSelected() != null) {
            pokemon[2] = psv.getPokemonSelected();
        }

        fcv = findViewById(R.id.fragmentPokemonSelection4);
        psv = fcv.getFragment();
        if (psv.getPokemonSelected() != null) {
            pokemon[3] = psv.getPokemonSelected();
        }

        fcv = findViewById(R.id.fragmentPokemonSelection5);
        psv = fcv.getFragment();
        if (psv.getPokemonSelected() != null) {
            pokemon[4] = psv.getPokemonSelected();
        }

        fcv = findViewById(R.id.fragmentPokemonSelection6);
        psv = fcv.getFragment();
        if (psv.getPokemonSelected() != null) {
            pokemon[5] = psv.getPokemonSelected();
        }
    }
}
