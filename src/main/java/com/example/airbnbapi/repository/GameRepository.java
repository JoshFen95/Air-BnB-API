package com.example.airbnbapi.repository;

import com.example.airbnbapi.model.Game;

import com.example.airbnbapi.model.MediaType;
import org.springframework.stereotype.Repository;


@Repository

public interface GameRepository extends MediaRepository<Game> {

    @Override
    default MediaType getMediaType() {
        return MediaType.GAME;
    }


}

