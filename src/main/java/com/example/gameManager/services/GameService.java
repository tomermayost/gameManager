package com.example.gameManager.services;

import com.example.gameManager.domains.*;
import com.example.gameManager.dtos.AnswerRequest;
import com.example.gameManager.dtos.AnswerResponse;
import com.example.gameManager.repos.GamesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class GameService {

    private static final Logger logger = LoggerFactory.getLogger(GameService.class);

    @Autowired
    GamesRepository gamesRepository;

    @Autowired
    RandomService randomService;

    public List<HashMap<String, Map<Player, Integer>>> getGameToPointsTable() {
        return gamesRepository.getGames().stream()
                .map(g -> {
                    var map = new HashMap<String, Map<Player, Integer>>();
                    map.put(g.getId(), g.getPointsInGame());
                    return map;
                })
                .collect(Collectors.toList());
    }

    public void addPoints(int points, Game game, Player player) {
        game.updatePointsInGame(player, points);
    }


    public Game getGame(String gameId) {
        return gamesRepository.getGame(gameId);
    }

    public Collection<Game> getGames() {
        return gamesRepository.getGames();
    }

    public AnswerResponse answerQuestion(AnswerRequest request) {
        var game = gamesRepository.getGame(request.getGameId());
        if (game == null) {
            logger.error("could not find game with id " + request.getGameId());
            return null;
        }
        for (Question q : game.getQuestions()) {
            if (q.getPossibleAnswers().contains(request.getAnswer())) {

                int pointsEarned = randomService.nextInt(20) + 1;
                addPointsToUser(game.getId(), request.getUserName(), pointsEarned);
                return new AnswerResponse(AnswerStatus.CORRECT, pointsEarned);
            }
        }

        return new AnswerResponse(AnswerStatus.INCORRECT, 0);
    }


    public void addPointsToUser(String gameId, String userName, int pointsEarned) {
        var game = getGame(gameId);
        for (Player p : game.getPlayers()) {
            if (p.getDisplayName().equals(userName)) {
                p.setPoints(p.getPoints() + pointsEarned);
                game.getPointsInGameForUpdate().put(p, p.getPoints());
                return;
            }
        }
        Player player = new Player();
        player.setDisplayName(userName);
        game.updatePointsInGame(player, pointsEarned);
        logger.info("created new player " + player.getDisplayName());
    }

    @PostConstruct
    public void createGames() {
        for (int i = 0; i < randomService.nextInt(10) + 1; i++) {
            Game g = new Game();

            Question question1 = new Question();
            question1.setQuestionBody("are you human?");
            question1.setPossibleAnswers(Arrays.asList("YES", "NO", "I'M A WATCHDOG"));

            Question question2 = new Question();
            question2.setQuestionBody("are you a machine?");
            question2.setPossibleAnswers(Arrays.asList("YES", "NO", "MAYBE"));

            g.setQuestions(Set.of(question1, question2));
            for (int j = 0; j < randomService.nextInt(10) + 1; j++) {
                Player p = new Player();
                p.setDisplayName("joe-" + p.getId());
                addPoints(randomService.nextInt(10), g, p);
            }
            gamesRepository.saveGame(g);
        }
    }
}
