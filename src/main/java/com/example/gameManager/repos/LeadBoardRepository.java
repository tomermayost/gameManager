package com.example.gameManager.repos;

import com.example.gameManager.domains.Game;
import com.example.gameManager.domains.Leadboard;
import org.springframework.stereotype.Repository;

@Repository
public class LeadBoardRepository {
    //db init
    Leadboard leadboard = new Leadboard();

    public Leadboard getLeadboard() {
        return leadboard;
    }

    public void saveGame(Game g) {
        leadboard.addGame(g);
    }

    public Game getGame(String gameId) {
        for (Game game : leadboard.getGames()) {
            if (game.getId().equals(gameId)) {
                return game;
            }
        }
        return null;
    }
}
