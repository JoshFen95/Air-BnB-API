package com.example.airbnbapi.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
public class Book extends Media {

    private MediaType type = MediaType.BOOK;
}
