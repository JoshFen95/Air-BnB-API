package com.example.airbnbapi.repository;

import com.example.airbnbapi.model.Film;
import com.example.airbnbapi.model.MediaType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FilmRepository extends MediaRepository<Film> {

    @Override
    default MediaType getMediaType() {
        return MediaType.FILM;
    }
}
