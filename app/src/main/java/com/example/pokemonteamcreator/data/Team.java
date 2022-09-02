package com.example.pokemonteamcreator.data;

public class Team {
    private String teamName;
    private Pokemon[] pokemon;

    public Team(String teamName, Pokemon[] pokemon) {
        this.teamName = teamName;
        this.pokemon = pokemon;
    }

    public String getTeamName() {
        return teamName;
    }
    public Pokemon[] getPokemon() {
        return pokemon;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public void setPokemon(Pokemon[] pokemon) {
        this.pokemon = pokemon;
    }
}
