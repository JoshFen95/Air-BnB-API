package com.example.airbnbapi.service;

import com.example.airbnbapi.mapper.FetchObject;
import com.example.airbnbapi.model.Film;
import com.example.airbnbapi.model.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class FilmService implements ServiceInterface<Film> {


    private Film[] cachedFilms;
    private MediaType type = MediaType.FILM;

    @Autowired
    public FilmService(FetchObject<Film> fetchObject) {

        this.cachedFilms = fetchObject.getObjectFromJsonFile("films", Film[].class);

    }


    @Override
    public Film[] getItems() {
        return cachedFilms;
    }


    @Override
    public int getAction() {
        return 3;
    }

    @Override
    public Film getMediaById(int id) {
        for (int i = 0; i < cachedFilms.length; i++) {
            if (cachedFilms[i].getId() == (id)) {


                return cachedFilms[id-1];
            }
        }

        return null;
    }

    @Override
    public MediaType getType() {
        return type;
    }
}
