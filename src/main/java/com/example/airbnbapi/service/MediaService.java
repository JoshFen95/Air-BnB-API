package com.example.airbnbapi.service;

import com.example.airbnbapi.model.Media;
import com.example.airbnbapi.model.MediaType;
import com.example.airbnbapi.repository.RepositoryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MediaService {



    @Autowired
    private RepositoryFactory<Media> repositoryFactory;


    public Media insertOrUpdateItem(MediaType type, Media item) {

       return repositoryFactory.getRepositoryByType(type).save(item);
    }

    public List<Media> getItems(MediaType type) {

       return repositoryFactory.getRepositoryByType(type).findAll();
    }


    public Optional<Media> getItemById(MediaType type, String id) {

       return repositoryFactory.getRepositoryByType(type).findById(id);
    }


    public void deleteById(MediaType type, String id) {
        repositoryFactory.getRepositoryByType(type).deleteById(id);
    }
}


