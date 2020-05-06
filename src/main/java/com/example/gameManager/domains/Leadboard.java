package com.example.gameManager.domains;

import java.util.*;

public class Leadboard {

    private Map<String, Game> games;

    public Leadboard() {
        this.games = new HashMap<>();
    }

    public Game getGame(String id) {
        return games.get(id);
    }

    public Collection<Game> getGames() {
        return  games.values();
    }

    public void addGame(Game g) {
        games.put(g.getId(), g);
    }

    public void setGames(Map<String, Game> games) {
        this.games = games;
    }
}
