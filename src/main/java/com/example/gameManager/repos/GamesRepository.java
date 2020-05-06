package com.example.gameManager.repos;

import com.example.gameManager.domains.Game;
import com.example.gameManager.domains.GamesContainer;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class GamesRepository {
    //db init
    private GamesContainer gamesContainer = new GamesContainer();

    public void saveGame(Game g) {
        gamesContainer.addGame(g);
    }

    public Game getGame(String gameId) {
        return gamesContainer.getGame(gameId);
    }

    public Collection<Game> getGames(){
        return gamesContainer.getGames();
    }
}
