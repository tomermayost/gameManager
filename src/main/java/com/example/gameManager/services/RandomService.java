package com.example.gameManager.services;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomService {

    private final Random random = new Random();

    public long nextLong(){
        return random.nextLong();
    }

    public int nextInt(int bound){
        return random.nextInt(bound);
    }
}
