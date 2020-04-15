package com.example.airbnbapi.service;

import com.example.airbnbapi.mapper.FetchObject;
import com.example.airbnbapi.model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmService implements ServiceInterface<Film> {


    private Film[] cachedFilms;

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
}
