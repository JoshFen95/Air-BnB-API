package com.example.airbnbapi.mapper;

import com.example.airbnbapi.Model.Game;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class FetchObject {




    public Game[] returnGameList(String jsonFileName) {


        if (findJsonFile(jsonFileName)) {

            try {
                ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


                InputStream inputStream = getClass().getResourceAsStream("/" + jsonFileName + ".json");

                return mapper.readValue(inputStream, Game[].class);


            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return null;
    }

    private boolean findJsonFile(String fileName) {


        InputStream inputStream = getClass().getResourceAsStream("/" + fileName + ".json");

        if (inputStream != null) {
            return true;
        } else {
            System.out.println("Could not locate file");
            return false;
        }
    }
}

