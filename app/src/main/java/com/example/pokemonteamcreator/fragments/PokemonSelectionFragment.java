package com.example.pokemonteamcreator.fragments;

import static com.example.pokemonteamcreator.MainActivity.selectPokemon;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.pokemonteamcreator.R;
import com.example.pokemonteamcreator.data.Pokemon;
import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class PokemonSelectionFragment extends Fragment {

    public PokemonSelectionFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view1 = inflater.inflate(R.layout.fragment_pokemon_selection, container, false);

        EditText editText = view1.findViewById(R.id.fragmentPokemonNameEntry);
        // If the enter key is pressed on the edit text
        // Gained from - http://androidsbs.blogspot.com/2013/05/perform-action-after-enter-keypress-on.html
        editText.setOnKeyListener(
                (view, keyCode, keyEvent) -> {
                    if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                        Pokemon pokemonSelected = selectPokemon(editText.getText().toString().toLowerCase());

                        if (pokemonSelected == null) {
                            Toast.makeText(getContext(), "Pokemon not found", Toast.LENGTH_LONG).show();
                        } else {
                            System.out.println(pokemonSelected.getName());
                            System.out.println(Arrays.toString(pokemonSelected.getTypes()));
                            System.out.println(pokemonSelected.getImageURL());

                            String displayName =
                                    pokemonSelected.getName().substring(0,1).toUpperCase()
                                            + pokemonSelected.getName().substring(1);
                            editText.setText(displayName);

                            TextView typeText1 = view1.findViewById(R.id.fragmentTypeText1);
                            typeText1.setText(pokemonSelected.getTypes()[0].name());
                            if (pokemonSelected.getTypes()[1] != null) {
                                TextView typeText2 = view1.findViewById(R.id.fragmentTypeText2);
                                typeText2.setText(pokemonSelected.getTypes()[1].name());
                            }

                            ImageView imageView = view1.findViewById(R.id.fragmentPokemonImage);
                            Picasso.get().load(pokemonSelected.getImageURL()).into(imageView);

                            Spinner abilitySelectSpinner = view1.findViewById(R.id.fragmentPokemonAbilitySpinner);
                            ArrayAdapter<String> abilityAdapter = new ArrayAdapter<>(
                                    view1.getContext(),
                                    android.R.layout.simple_spinner_item
                            );
                            abilityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            for (String ability: pokemonSelected.getAbilities()) {
                                abilityAdapter.add(
                                        WordUtils.capitalize(
                                                ability.replaceAll("-", " ")
                                        )
                                );
                            }
                            abilitySelectSpinner.setAdapter(abilityAdapter);
                        }

                        return true;
                    }
                    return false;
                }
        );

        return view1;
    }
}
