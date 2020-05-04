package com.example.gameManager.domains;


import com.example.gameManager.utils.PointsInGameSorter;

import java.util.*;

public class Game {

    private final String id;
    private Set<Player> players;
    private Map<Player, Integer> pointsInGame;
    private Set<Question> questions;

    public Game() {
        id = UUID.randomUUID().toString();
        players = new HashSet<>();
        pointsInGame = new HashMap<>();
        questions = new HashSet<>();
    }

    public void updatePointsInGame(Player player, int points) {
        addPlayer(player);
        player.setPoints(player.getPoints() + points);
        pointsInGame.put(player, player.getPoints());
    }

    public void addPlayer(Player p) {
        players.add(p);
    }

    public String getId() {
        return id;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public void setPointsInGame(Map<Player, Integer> pointsInGame) {
        this.pointsInGame = pointsInGame;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public Map<Player, Integer> getPointsInGame() {
        return PointsInGameSorter.sortByValue(pointsInGame);
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return id.equals(game.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
