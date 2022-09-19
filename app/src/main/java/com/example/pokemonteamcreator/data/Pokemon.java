package com.example.pokemonteamcreator.data;

import static com.example.pokemonteamcreator.activities.MainActivity.requestQueue;
import static com.example.pokemonteamcreator.activities.PokedexSelectionActivity.launchTeamSelector;
import static com.example.pokemonteamcreator.activities.PokedexSelectionActivity.numOfRequests;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

import org.apache.commons.lang3.text.WordUtils;
import org.json.JSONException;

import java.util.ArrayList;

public class Pokemon {
    private final Integer nationalDexNumber;
    private String name;
    private String types = "";
    private final ArrayList<String> abilities = new ArrayList<>();
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
                            types += response
                                    .getJSONArray("types")
                                    .getJSONObject(i)
                                    .getJSONObject("type")
                                    .getString("name")
                                    .toUpperCase();
                            types += ",";
                        }
                        // Remove the last comma from the String
                        types = types.substring(0, (types.length() - 1));
                        // Get the abilities of the pokemon
                        for (int i = 0; i < response.getJSONArray("abilities").length(); i++) {
                            abilities.add(
                                    WordUtils.capitalize(
                                            response
                                                    .getJSONArray("abilities")
                                                    .getJSONObject(i)
                                                    .getJSONObject("ability")
                                                    .getString("name")
                                                    .replace("-", " ")
                                )
                            );
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
    public String getTypes() {
        return types;
    }
    public ArrayList<String> getAbilities() {
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
