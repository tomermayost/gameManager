package com.example.gameManager;

import com.example.gameManager.domains.AnswerStatus;
import com.example.gameManager.domains.Player;
import com.example.gameManager.dtos.AnswerRequest;
import com.example.gameManager.services.GameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Map;

@SpringBootTest
class GameManagerApplicationTests {


    @Autowired
    GameService gameService;

    @Test
    void answerCorrect() {
        var game = gameService.getGames().iterator().next();
        var answer = new AnswerRequest();
        answer.setAnswer("YES");
        answer.setGameId(game.getId());
        Player player = game.getPlayers().iterator().next();
        var pointsBefore = player.getPoints();
        answer.setUserName(player.getDisplayName());
        var response = gameService.answerQuestion(answer);
        Assert.isTrue(response.getStatus().equals(AnswerStatus.CORRECT), "bad response");
        Assert.isTrue(response.getPointsEarned() > 0, "points earned is 0");
        Assert.isTrue(pointsBefore < player.getPoints(), "points did not update");
    }

    @Test
    void answerInCorrect() {
        var game = gameService.getGames().iterator().next();
        var answer = new AnswerRequest();
        answer.setAnswer("blabla");
        answer.setGameId(game.getId());
        Player player = game.getPlayers().iterator().next();
        var pointsBefore = player.getPoints();
        answer.setUserName(player.getDisplayName());
        var response = gameService.answerQuestion(answer);
        Assert.isTrue(response.getStatus().equals(AnswerStatus.INCORRECT), "bad response");
        Assert.isTrue(response.getPointsEarned() == 0, "points earned is not 0");
        Assert.isTrue(pointsBefore == player.getPoints(), "points changed for no reason");
    }

    @Test
    void pointsInGame() {
        var game = gameService.getGames().iterator().next();
        Map<Player, Integer> pointsInGame = game.getPointsInGame();
        var player = game.getPlayers().iterator().next();

        Assert.isTrue(player.getPoints() == pointsInGame.get(player), "points mismatch");

    }

    @Test
    void answerCorrectNewPlayer() {
        var game = gameService.getGames().iterator().next();
        var answer = new AnswerRequest();
        answer.setAnswer("YES");
        answer.setGameId(game.getId());
        answer.setUserName("TomerMayost");

        var response = gameService.answerQuestion(answer);
        Assert.isTrue(response.getStatus().equals(AnswerStatus.CORRECT), "bad response");
        Assert.isTrue(response.getPointsEarned() > 0, "points earned is 0");
    }

    @Test
    void answerInCorrectNewPlayer() {
        var game = gameService.getGames().iterator().next();
        var answer = new AnswerRequest();
        answer.setAnswer("blabla");
        answer.setGameId(game.getId());
        answer.setUserName("TomerMayost");

        var response = gameService.answerQuestion(answer);
        Assert.isTrue(response.getStatus().equals(AnswerStatus.INCORRECT), "bad response");
        Assert.isTrue(response.getPointsEarned() == 0, "points earned is not 0");
    }

}
