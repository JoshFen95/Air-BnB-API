package com.example.airbnbapi.api;

import com.example.airbnbapi.model.Media;
import com.example.airbnbapi.model.MediaType;
import com.example.airbnbapi.service.ServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("api/v1/media")
@RestController
public class Controller {

    private final ServiceFactory serviceFactory;


    @Autowired
    public Controller(ServiceFactory serviceFactory) {

        this.serviceFactory = serviceFactory;

    }

    @GetMapping(path = "/{type}")
    public ResponseEntity<Media[]> getAllMedia(@PathVariable("type") MediaType type) {

        Media[] items = serviceFactory.getServiceByType(type).getItems();
        if (items != null) {
            return new ResponseEntity<>(items, HttpStatus.OK);

        } else {
            return new ResponseEntity<Media[]>(HttpStatus.BAD_REQUEST);

        }

    }

    @GetMapping(path = "/{type}/{id}")

    public ResponseEntity<Media> getGameById(@PathVariable("type") MediaType type, @PathVariable("id") int id) {

       Media item = serviceFactory.getServiceByType(type).getMediaById(id);

        if (item != null) {
            return new ResponseEntity<Media>(item, HttpStatus.OK);

        } else {
            return new ResponseEntity<Media>(HttpStatus.BAD_REQUEST);

        }
    }

}
