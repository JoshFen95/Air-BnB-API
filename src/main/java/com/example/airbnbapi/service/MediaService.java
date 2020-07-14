package com.example.airbnbapi.service;


import com.example.airbnbapi.controller.exception.DataBaseException;
import com.example.airbnbapi.model.Media;
import com.example.airbnbapi.model.MediaType;
import com.example.airbnbapi.repository.RepositoryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MediaService {

    private static final Logger logger = LoggerFactory.getLogger(MediaService.class);

    @Autowired
    private RepositoryFactory<Media> repositoryFactory;


    public Media insertOrUpdateItem(MediaType type, Media item) {

//        if (itemExists(type, item.getTitle())) {
//
//            return null;
//        } else {

        try {
            return repositoryFactory.getRepositoryByType(type).save(item);
        } catch (RuntimeException e) {

            logger.error("An error occurred when trying to request data from the database, RuntimeException: " + e);
            throw new DataBaseException("Sorry, couldn't establish connection to the database", e);
        }
//        }
    }

    public List<Media> getItems(MediaType type) {
        try {
            return repositoryFactory.getRepositoryByType(type).findAll();
        } catch (RuntimeException e) {

            logger.error("An error occurred when trying to request data from the database, RuntimeException: " + e);

            throw new DataBaseException("Sorry, couldn't establish connection to the database");
        }
    }


    public Optional<Media> getItemById(MediaType type, String id) {
        try {
            return repositoryFactory.getRepositoryByType(type).findById(id);
        } catch (RuntimeException e) {

            logger.error("An error occurred when trying to request data from the database, RuntimeException: " + e);

            throw new DataBaseException("Sorry, couldn't establish connection to the database", e);
        }
    }


    public void deleteById(MediaType type, String id) {
        try {
            repositoryFactory.getRepositoryByType(type).deleteById(id);
        } catch (RuntimeException e) {

            logger.error("An error occurred when trying to request data from the database, RuntimeException: " + e);

            throw new DataBaseException("Sorry, couldn't establish connection to the database");
        }
    }


    public boolean itemExists(MediaType type, String title) {

        List<Media> allItems = repositoryFactory.getRepositoryByType(type).findAll();

        try {
            for (int i = 0; i <= allItems.size(); i++) {
                if (allItems.get(i).getTitle().equals(title)) {
                    return true;
                }
            }
        } catch (RuntimeException e) {
            return false;
        }
        return false;
    }
}



