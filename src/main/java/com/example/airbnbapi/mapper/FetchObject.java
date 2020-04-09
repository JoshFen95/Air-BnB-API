package com.example.airbnbapi.mapper;

import com.example.airbnbapi.Model.Game;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;


public class FetchObject {


    public Game[] returnGameList(String jsonFileName) {


        if (jsonFileExists(jsonFileName)) {

            try {
                ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


                InputStream inputStream = getClass().getResourceAsStream("/" + jsonFileName + ".json");

                return mapper.readValue(inputStream, Game[].class);


            } catch (Exception ex) {
                ex.printStackTrace();
            } else{

                System.out.println("File could not be located");
                return null;
            }
            return null;

        }
    }

        return null;
}

    private boolean jsonFileExists(String fileName) {


        return getClass().getResourceAsStream("/" + fileName + ".json") != null;

    }
}

