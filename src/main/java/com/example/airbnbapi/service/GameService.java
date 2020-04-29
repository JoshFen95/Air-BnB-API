package com.example.airbnbapi.service;

import com.example.airbnbapi.repository.GameRepository;
import com.example.airbnbapi.model.Game;
import com.example.airbnbapi.model.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class GameService implements ServiceInterface<Game>{


    private MediaType type = MediaType.GAME;

    @Autowired
    private GameRepository gameRepository;


    @Override
    public List<Game> getItems() {

       return gameRepository.findAll();
    }


    @Override
    public Optional<Game> getItemById(String id) {

       return gameRepository.findById(id);
    }


    public MediaType getType() {
        return type;
    }

    @Override
    public void deleteById(String id) {
        gameRepository.deleteById(id);
    }
}


