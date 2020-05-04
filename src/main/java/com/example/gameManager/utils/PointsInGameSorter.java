package com.example.gameManager.utils;

import com.example.gameManager.domains.Player;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PointsInGameSorter {
    public static Map<Player, Integer> sortByValue(final Map<Player, Integer> pointsInGame) {
        return pointsInGame.entrySet()
                .stream()
                .sorted((Map.Entry.<Player, Integer>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

}
