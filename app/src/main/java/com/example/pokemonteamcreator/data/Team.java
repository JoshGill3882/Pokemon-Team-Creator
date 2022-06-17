package com.example.pokemonteamcreator.data;

public class Team {
    private final String teamName;
    private final Pokemon[] pokemon;

    public Team(String aTeamName, Pokemon[] pokemonInTeam) {
        this.teamName = aTeamName;
        this.pokemon = pokemonInTeam;
    }

    public String getTeamName() {
        return teamName;
    }
    public Pokemon[] getPokemon() {
        return pokemon;
    }
}
