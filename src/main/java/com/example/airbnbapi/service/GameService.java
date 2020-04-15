package com.example.airbnbapi.service;

import com.example.airbnbapi.mapper.FetchObject;
import com.example.airbnbapi.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {


    private Game[] cachedGames;

    @Autowired
    public GameService(FetchObject<Game> fetchObject) {

        this.cachedGames = fetchObject.getObjectFromJsonFile("games", Game[].class);

    }

    public Game[] getGames() {
        return cachedGames;
    }
}
