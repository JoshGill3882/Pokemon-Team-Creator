package com.example.pokemonteamcreator.data;

public class Team {
    private String teamName;
    private Pokemon[] pokemon;
    private String pokedexChosen;

    public Team(String teamName, Pokemon[] pokemon, String pokedexChosen) {
        this.teamName = teamName;
        this.pokemon = pokemon;
        this.pokedexChosen = pokedexChosen;
    }

    public String getTeamName() {
        return teamName;
    }
    public Pokemon[] getPokemon() {
        return pokemon;
    }
    public String getPokedexChosen() {
        return pokedexChosen;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public void setPokemon(Pokemon[] pokemon) {
        this.pokemon = pokemon;
    }
    public void setPokedexChosen(String pokedexChosen) {
        this.pokedexChosen = pokedexChosen;
    }
}
