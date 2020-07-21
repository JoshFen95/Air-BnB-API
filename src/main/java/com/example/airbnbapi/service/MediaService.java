package com.example.airbnbapi.service;


import com.example.airbnbapi.authentication.OauthUser;
import com.example.airbnbapi.authentication.User;
import com.example.airbnbapi.controller.exception.DataBaseException;
import com.example.airbnbapi.model.Media;
import com.example.airbnbapi.model.MediaType;
import com.example.airbnbapi.repository.OauthUserRepository;
import com.example.airbnbapi.repository.RepositoryFactory;
import com.example.airbnbapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class MediaService {

    private static final Logger logger = LoggerFactory.getLogger(MediaService.class);

    @Autowired
    private RepositoryFactory<Media> repositoryFactory;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OauthUserRepository oauthUserRepository;


    public Media insertOrUpdateItem(MediaType type, Media item) {

        try {
            return repositoryFactory.getRepositoryByType(type).save(item);
        } catch (RuntimeException e) {

            logger.error("An error occurred when trying to request data from the database, RuntimeException: " + e);
            throw new DataBaseException("Sorry, couldn't establish connection to the database", e);
        }
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

    //// authentication ////

    // add an authenticated user to the authenticated db
    public OauthUser authenticateUser(User user) {

        if (authenticatedUserExists(user.getEmailAddress())) {
            return null;
        }


        try {

            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

            OauthUser oauthUser = new OauthUser();
            oauthUser.setOauthCode(UUID.randomUUID());
            oauthUser.setEmailAddress(user.getEmailAddress());
            oauthUser.setPassword(hashedPassword);

            return oauthUserRepository.save(oauthUser);
        } catch (RuntimeException e) {

            logger.error("An error occurred when trying to request data from the database, RuntimeException: " + e);
            throw new DataBaseException("Sorry, couldn't establish connection to the database", e);
        }

    }

    // adds a user to the user db.
    public User addUser(User user) {

        List<User> allUsers = userRepository.findAll();

        try {
            for (int i = 0; i <= allUsers.size(); i++) {

                if (allUsers.size() == 0 || !allUsers.get(i).getEmailAddress().equals(user.getEmailAddress())) {

                    return userRepository.save(user);
                }
            }
        } catch (RuntimeException e) {

            logger.error("An error occurred when trying to request data from the database, RuntimeException: " + e);
            throw new DataBaseException("Sorry, couldn't establish connection to the database", e);
        }
        return null;
    }

    // checks that users email and password match what is stored in the user db
    public boolean userExistsAndPasswordMatches(String emailAddress, String password) {
        List<User> allUsers = userRepository.findAll();

        try {
            for (int i = 0; i <= allUsers.size(); i++) {

                if (allUsers.get(i).getEmailAddress().equals(emailAddress) && allUsers.get(i).getPassword().equals(password)) {
                    return true;
                }
            }
        } catch (RuntimeException e) {
            return false;
        }
        return false;
    }

    // checks if the users email address is in the authenticated db
    public boolean authenticatedUserExists(String emailAddress) {
        List<OauthUser> allOauthUsers = oauthUserRepository.findAll();

        try {
            for (int i = 0; i <= allOauthUsers.size(); i++) {

                if (allOauthUsers.get(i).getEmailAddress().equals(emailAddress)) {

                    return true;
                }
            }
        } catch (RuntimeException e) {
            return false;
        }
        return false;
    }

    // checks if a users email exists in the db
    public boolean UserExists(String emailAddress) {
        List<User> allUsers = userRepository.findAll();

        try {
            for (int i = 0; i <= allUsers.size(); i++) {

                if (allUsers.get(i).getEmailAddress().equals(emailAddress)) {

                    return true;
                }
            }
        } catch (RuntimeException e) {
            return false;
        }
        return false;
    }

}



