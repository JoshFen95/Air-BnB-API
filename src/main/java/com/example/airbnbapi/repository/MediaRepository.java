package com.example.airbnbapi.repository;

import com.example.airbnbapi.model.Media;
import com.example.airbnbapi.model.MediaType;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MediaRepository<M extends Media> extends MongoRepository<M, String> {

    default MediaType getMediaType() { return null; }
}
