package com.example.airbnbapi.controller;

import com.example.airbnbapi.model.Media;
import com.example.airbnbapi.model.MediaType;
import com.example.airbnbapi.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.status;


import java.util.List;
import java.util.Optional;


@RequestMapping("api/v1/media")
@RestController
public class Controller {

    @Autowired
    private MediaService mediaService;


    // Add new item
    @PostMapping(path = "/{type}/add")
    public ResponseEntity<Media> addItem(@PathVariable("type") MediaType type, @RequestBody Media item) {


        return status(HttpStatus.OK).body(mediaService.insertOrUpdateItem(type, item));
    }

    // Update exiting item via ID
    @PutMapping(path = "/{type}/update/id/{id}")
    public ResponseEntity updateItem(@PathVariable("type") MediaType type, @PathVariable("id") String id, @Valid @RequestBody Media itemToUpdate) {

        Optional<? extends Media> searchedItem = mediaService.getItemById(type,id);

        if (searchedItem.isPresent()) {

            return status(HttpStatus.OK).body(searchedItem.get().getTitle() + " has been updated: " + mediaService.insertOrUpdateItem(type,itemToUpdate));
        } else  {
            return status(HttpStatus.NOT_FOUND).body("Item could not be found to update. New item added" + mediaService.insertOrUpdateItem(type,itemToUpdate));
        }
    }

// Show all items of one type
    @GetMapping(path = "/{type}")
    public ResponseEntity getAll(@PathVariable("type") MediaType type) {
        List<? extends Media> items = mediaService.getItems(type);

        return status(HttpStatus.OK).body(items);

    }

    // show an item via ID search
    @GetMapping(path = "/{type}/id/{id}")
    public ResponseEntity getById(@PathVariable("type") MediaType type, @PathVariable("id") String id) {

        Optional<? extends Media> searchedItem = mediaService.getItemById(type,id);

        if (searchedItem.isPresent()) {
            return status(HttpStatus.OK).body(mediaService.getItemById(type, id));
        } else {
            return status(HttpStatus.NOT_FOUND).body("ID not found");
        }
    }

// Delete an item via ID search
    @DeleteMapping(path = "/{type}/delete/id/{id}")
    public ResponseEntity deleteItemById(@PathVariable("type") MediaType type, @PathVariable("id") String id) {


        if (mediaService.getItemById(type, id).isPresent()) {
            mediaService.deleteById(type, id);
            return status(HttpStatus.OK).body("id " + id + " has been deleted");
        } else {
            return status(HttpStatus.NOT_FOUND).body("id " + id + "  not found");
        }
    }


}
