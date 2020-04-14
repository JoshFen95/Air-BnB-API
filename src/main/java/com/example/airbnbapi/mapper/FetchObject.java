package com.example.airbnbapi.mapper;

import com.example.airbnbapi.model.Game;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class FetchObject {

    private Game[] cachedGames;


    public FetchObject() {
        this.cachedGames = returnGameList("games");
    }

    public Game[] getCachedGames() {
        return cachedGames;
    }

    private Game[] returnGameList(String jsonFileName) {


        if (jsonFileExists(jsonFileName)) {

                try {
                    ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


                    InputStream inputStream = getClass().getResourceAsStream("/" + jsonFileName + ".json");

                    return mapper.readValue(inputStream, Game[].class);



                } catch (Exception ex) {
                    ex.printStackTrace();
                }


            System.out.println("File could not be located");
            return null;
        }
        return null;

    }


    private boolean jsonFileExists(String fileName) {


        return getClass().getResourceAsStream("/" + fileName + ".json") != null;

    }
}

