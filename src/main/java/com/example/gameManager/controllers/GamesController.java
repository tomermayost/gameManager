package com.example.gameManager.controllers;

import com.example.gameManager.domains.*;
import com.example.gameManager.dtos.AnswerRequest;
import com.example.gameManager.dtos.AnswerResponse;
import com.example.gameManager.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/v1/games")
@RestController
public class GamesController {

    @Autowired
    GameService gameService;

    @GetMapping("/board")
    public Leadboard getAllGames() {
        return gameService.getLeadboard();
    }

    @GetMapping("/board/table")
    public List<HashMap<String, Map<Player, Integer>>> getGamesTable() {
        return gameService.getGameToPointsTable();
    }

    @GetMapping("/board/{gameId}")
    public Game getGame(@PathVariable("gameId") String gameId) {
        return gameService.getGame(gameId);
    }

    @PostMapping("/board")
    public AnswerResponse answerQuestion(@RequestBody AnswerRequest request) {
        return gameService.answerQuestion(request);
    }
}
