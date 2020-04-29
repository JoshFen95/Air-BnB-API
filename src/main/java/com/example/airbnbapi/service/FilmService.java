package com.example.airbnbapi.service;


import com.example.airbnbapi.repository.FilmRepository;
import com.example.airbnbapi.model.Film;
import com.example.airbnbapi.model.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class FilmService implements ServiceInterface<Film> {



    private MediaType type = MediaType.FILM;

    @Autowired
    private FilmRepository filmRepository;


    @Override
    public List<Film> getItems() {
       return filmRepository.findAll();

    }



    @Override
    public Optional<Film> getItemById(String id) {


        return filmRepository.findById(id);
    }

    @Override
    public MediaType getType() {
        return type;
    }

    @Override
    public void deleteById(String id) {
        filmRepository.deleteById(id);
    }
}
