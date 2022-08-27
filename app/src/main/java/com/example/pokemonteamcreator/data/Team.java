package com.example.pokemonteamcreator.data;

public class Team {
    private final int teamId;
    private String teamName;
    private Pokemon[] pokemon;

    public Team(int teamId, String teamName, Pokemon[] pokemon) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.pokemon = pokemon;
    }

    public int getTeamId() {
        return teamId;
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
