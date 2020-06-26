package com.example.airbnbapi.controller;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.example.airbnbapi.model.Media;
import com.example.airbnbapi.model.MediaType;
import com.example.airbnbapi.model.MediaTypeConverter;
import com.example.airbnbapi.service.MediaService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
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

    @Autowired
    private MediaTypeConverter mediaTypeConverter;

    @InitBinder
    protected void initBinder(final WebDataBinder webDataBinder) {

        webDataBinder.registerCustomEditor(MediaType.class, mediaTypeConverter);

    }


    private static final Logger logger = LoggerFactory.getLogger(Controller.class);


    // Show all items of one type
    @GetMapping(path = "/log/{level}")
    public ResponseEntity changeLevel(@PathVariable("level") String level) {

        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        context.getLogger(Controller.class).setLevel(Level.valueOf(level));


            return status(HttpStatus.OK).body(level);


    }

    // Add new item
    @PostMapping(path = "/{type}/add")
    public ResponseEntity addItem(@PathVariable("type") MediaType type, @RequestBody Media item) {

        logger.trace("User Entered: Type= " + type + " Media= " + item);
        return status(HttpStatus.OK).body(mediaService.insertOrUpdateItem(type, item));


    }


    // Update exiting item via ID
    @PutMapping(path = "/{type}/{id}")
    public ResponseEntity updateItem(@PathVariable("type") MediaType type, @PathVariable("id") String id, @Valid @RequestBody Media itemToUpdate) {

        logger.trace("User Entered: Type= " + type + " Id= "+ id + " Media= " + itemToUpdate);

        Optional<? extends Media> searchedItem = mediaService.getItemById(type, id);

        if (searchedItem.isPresent()) {

            return status(HttpStatus.OK).body(searchedItem.get().getTitle() + " has been updated: " + mediaService.insertOrUpdateItem(type, itemToUpdate));
        } else {
            return status(HttpStatus.NOT_FOUND).body("Item could not be found to update. New item added" + mediaService.insertOrUpdateItem(type, itemToUpdate));
        }
    }

    // Show all items of one type
    @GetMapping(path = "/{type}")
    public ResponseEntity getAll(@PathVariable("type") MediaType type) {

        List<? extends Media> items = mediaService.getItems(type);
        logger.trace("User Entered: Type= " + type + " DATA RETURNED by CALL: " + items);

        if (items != null) {
            return status(HttpStatus.OK).body(items);
        } else {
            return status(HttpStatus.NOT_FOUND).body("ITEMS NOT FOUND");
        }

    }

    // show an item via ID search
    @GetMapping(path = "/{type}/{id}")
    public ResponseEntity getById(@PathVariable("type") MediaType type, @PathVariable("id") String id) {

        Optional<? extends Media> searchedItem = mediaService.getItemById(type, id);
        logger.trace("User Entered: Type= " + type + " Id= " + id);

        if (searchedItem.isPresent()) {
            return status(HttpStatus.OK).body(mediaService.getItemById(type, id));
        } else {
            return status(HttpStatus.NOT_FOUND).body("ID NOT FOUND");
        }
    }

    // Delete an item via ID search
    @DeleteMapping(path = "/{type}/{id}")
    public ResponseEntity deleteItemById(@PathVariable("type") MediaType type, @PathVariable("id") String id) {
        logger.trace("User Entered: Type= " + type + " Id= " + id);

        if (mediaService.getItemById(type, id).isPresent()) {
            mediaService.deleteById(type, id);
            return status(HttpStatus.OK).body("Id " + id + " has been deleted");
        } else {
            return status(HttpStatus.NOT_FOUND).body("ID NOT FOUND. Could not delete item");
        }
    }


}
