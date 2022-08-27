package com.example.pokemonteamcreator.data;

import static com.example.pokemonteamcreator.MainActivity.launchTeamSelector;
import static com.example.pokemonteamcreator.MainActivity.numOfRequests;
import static com.example.pokemonteamcreator.MainActivity.requestQueue;

import android.content.Intent;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.pokemonteamcreator.MainActivity;
import com.example.pokemonteamcreator.TeamCreationActivity;

import org.json.JSONException;

public class Pokemon {
    private final Integer nationalDexNumber;
    private String name;
    private final Type[] types = new Type[2];
    private final String[] abilities = new String[3];
    private String chosenAbility;
    private String imageURL;

    public Pokemon(Integer number) {
        this.nationalDexNumber = number;
        // Create a string with the correct get URL
        String pokemonGetURL = "https://pokeapi.co/api/v2/pokemon/" + this.nationalDexNumber;
        // Create the request
        JsonObjectRequest pokemonGetRequest = new JsonObjectRequest(
                // Tell the request it is a GET request
                Request.Method.GET,
                // Pass in the URL
                pokemonGetURL,
                // Pass in an empty JsonObject
                null,
                // On a correct response
                response -> {
                    try {
                        // Get the name of the pokemon
                        name = response.getJSONObject("species").getString("name");
                        // Get the types of the pokemon
                        for (int i = 0; i < response.getJSONArray("types").length(); i++) {
                            types[i] = Type.valueOf(
                                    response
                                            .getJSONArray("types")
                                            .getJSONObject(i)
                                            .getJSONObject("type")
                                            .getString("name")
                                            .toUpperCase()
                            );
                        }
                        // Get the abilities of the pokemon
                        for (int i = 0; i < response.getJSONArray("abilities").length(); i++) {
                            abilities[i] = response
                                    .getJSONArray("abilities")
                                    .getJSONObject(i)
                                    .getJSONObject("ability")
                                    .getString("name");
                        }
                        // Get the image for the pokemon
                        imageURL = response
                                .getJSONObject("sprites")
                                .getJSONObject("other")
                                .getJSONObject("official-artwork")
                                .getString("front_default");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    numOfRequests--;
                    System.out.println("In Pokemon Class: " + numOfRequests);
                    if (numOfRequests == 0) {
                        launchTeamSelector();
                    }
                },
                // On an error
                error -> System.out.println(error.networkResponse)
        );
        numOfRequests++;
        // Add the request to the queue
        requestQueue.add(pokemonGetRequest);
    }

    public Integer getNationalDexNumber() {
        return nationalDexNumber;
    }
    public String getName() {
        return name;
    }
    public Type[] getTypes() {
        return types;
    }
    public String[] getAbilities() {
        return abilities;
    }
    public String getChosenAbility() {
        return chosenAbility;
    }
    public String getImageURL() {
        return imageURL;
    }

    public void setChosenAbility(String chosenAbility) {
        this.chosenAbility = chosenAbility;
    }
}
