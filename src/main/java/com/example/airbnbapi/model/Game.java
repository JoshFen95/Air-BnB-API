package com.example.airbnbapi.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "games")

public class Game extends Media {

}
