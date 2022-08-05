package com.example.pokemonteamcreator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pokemonteamcreator.data.Pokemon;

import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static String pokedexString;
    public static ArrayList<Pokemon> pokedexArrayList = new ArrayList<>();
    public static RequestQueue requestQueue;
    public static int numOfRequests = 0;
    @SuppressLint("StaticFieldLeak")
    public static Context context;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        requestQueue = Volley.newRequestQueue(context);
    }

    @SuppressLint("NonConstantResourceId")
    public void buttonPress(final View view) {
        // Switch method depending on the button pressed
        // Found at - https://stackoverflow.com/questions/13032333/droid-how-to-get-button-id-from-onclick-method-described-in-xml
        // With bug fixing aid from - https://stackoverflow.com/questions/11335871/why-is-the-last-case-of-my-switch-statement-getting-hit
        switch (view.getId()) {
            case R.id.activityMainRedBlueButton:
            case R.id.activityMainYellowButton:
                pokedexString = "kanto";
                break;
            case R.id.activityMainGoldSilverButton:
            case R.id.activityMainCrystalButton:
                pokedexString = "original-johto";
                break;
            case R.id.activityMainRubySapphireButton:
            case R.id.activityMainEmeraldButton:
                pokedexString = "hoenn";
                break;
            case R.id.activityMainDiamondPearlButton:
                pokedexString = "original-sinnoh";
                break;
            case R.id.activityMainPlatinumButton:
                pokedexString = "extended-sinnoh";
                break;
            case R.id.activityMainBlackWhiteButton:
                pokedexString = "original-unova";
                break;
            case R.id.activityMainBlack2White2Button:
                pokedexString = "updated-unova";
                break;
            case R.id.activityMainOmegaRubyAlphaSapphireButton:
                pokedexString = "updated-hoenn";
                break;
            case R.id.activityMainSunMoonButton:
                pokedexString = "original-alola";
                break;
            case R.id.activityMainUltraSunUltraMoonButton:
                pokedexString = "updated-alola";
                break;
            case R.id.activityMainSwordShieldButton:
                pokedexString = "galar";
                break;
            case R.id.activityMainNationalDexButton:
                pokedexString = "national";
                break;
            default:
                break;
        }

        if (!pokedexString.isEmpty()) {
            System.out.println(pokedexString);
            // Get the pokedex
            getPokedex();
        }
    }

    public void getPokedex() {
        // Clear the values to make sure the correct pokedex size is gathered
        pokedexArrayList.clear();

        // Set the string for the second GET Request
        String pokedexGetURL = "https://pokeapi.co/api/v2/pokedex/" + pokedexString;
        // Perform the GET Request
        JsonObjectRequest pokedexRequest = new JsonObjectRequest(
                // Tell the request it is a GET request
                Request.Method.GET,
                // Pass in the URL
                pokedexGetURL,
                // Pass in a null JSONObject
                null,
                // On a Correct Response
                response -> {
                    try {
                        // For each pokemon in the pokedex
                        for (int i=0; i<response.getJSONArray("pokemon_entries").length(); i++) {
                            String urlSubstring = response
                                    .getJSONArray("pokemon_entries")
                                    .getJSONObject(i)
                                    .getJSONObject("pokemon_species")
                                    .getString("url")
                                    .substring(42);
                            // Add it to the Pokedex
                            pokedexArrayList.add(
                                    new Pokemon(
                                            Integer.parseInt(
                                                    urlSubstring
                                                    .substring(0, urlSubstring.length() - 1)
                                            )
                                    )
                            );
                        }
                        System.out.println("Pokedex Size: " + pokedexArrayList.size());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    numOfRequests--;
                    System.out.println("In MainActivity: " + numOfRequests);
                },
                // On an Erroneous Response
                error -> System.out.println(error.networkResponse)
        );
        numOfRequests++;
        // Add the request to the queue
        requestQueue.add(pokedexRequest);
        // Alert the user that the pokedex is being gathered
        Toast.makeText(context, "POKEDEX LOADING", Toast.LENGTH_LONG).show();
    }

    public static void launchTeamSelector() {
        // Alert the user that the pokedex is complete
        Toast.makeText(context, "POKEDEX COMPLETE", Toast.LENGTH_LONG).show();
        // Create an intent for changing activity
        Intent redirect = new Intent(context, TeamCreationActivity.class);
        // Start the new activity
        context.startActivity(redirect);
    }

    // Method for selecting a given pokemon (by name) from the ArrayList
    public static Pokemon selectPokemon(String pokemonName) {
        for (Pokemon pokemon: pokedexArrayList) {
            if (pokemon.getName().equals(pokemonName)) {
                return pokemon;
            }
        }
        return null;
    }
}
