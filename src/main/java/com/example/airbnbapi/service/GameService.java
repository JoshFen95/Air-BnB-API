package com.example.airbnbapi.service;

import com.example.airbnbapi.mapper.FetchObject;
import com.example.airbnbapi.model.Game;
import com.example.airbnbapi.model.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GameService implements ServiceInterface<Game> {


    private Game[] cachedGames;
    private MediaType type = MediaType.GAME;

    @Autowired
    public GameService(FetchObject<Game> fetchObject) {

        this.cachedGames = fetchObject.getObjectFromJsonFile("games", Game[].class);

    }


    @Override
    public Game[] getItems() {
        return cachedGames;
    }

    @Override
    public int getAction() {
        return 1;
    }

    @Override
    public Game getMediaById(int id) {

        for (int i = 0; i < cachedGames.length; i++) {
            if (cachedGames[i].getId() == (id)) {


                return cachedGames[id-1];
            }
        }

        return null;
    }

    public MediaType getType() {
        return type;
    }
}


