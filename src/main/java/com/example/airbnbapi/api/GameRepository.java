package com.example.airbnbapi.api;

import com.example.airbnbapi.model.Game;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository

public interface GameRepository extends MongoRepository<Game, String> {


}

