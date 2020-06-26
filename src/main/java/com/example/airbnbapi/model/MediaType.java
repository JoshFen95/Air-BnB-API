package com.example.airbnbapi.model;

public enum MediaType {

    GAME("game"),
    BOOK("book"),
    FILM("film");
    private String type;

    MediaType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
