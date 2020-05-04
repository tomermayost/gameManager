package com.example.gameManager.domains;

import java.util.HashSet;
import java.util.Set;

public class Leadboard {

    Set<Game> games;

    public Leadboard() {
        this.games = new HashSet<>();
    }

    public Set<Game> getGames() {
        return games;
    }

    public void addGame(Game g){
        games.add(g);
    }
    public void setGames(Set<Game> games) {
        this.games = games;
    }
}
