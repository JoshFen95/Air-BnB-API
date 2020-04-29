package com.example.airbnbapi.controller;

import com.example.airbnbapi.model.Media;
import com.example.airbnbapi.model.MediaType;
import com.example.airbnbapi.service.ServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.status;


import java.util.List;
import java.util.Optional;


@RequestMapping("api/v1/media")
@RestController
public class Controller {

    @Autowired
    private  ServiceFactory serviceFactory;


    // NEED TO ADD A PUT METHOD


    @GetMapping(path = "/{type}")
    public ResponseEntity getAll(@PathVariable("type") MediaType type) {
        List<? extends Media> items = serviceFactory.getServiceByType(type).getItems();

        return status(HttpStatus.OK).body(items);

    }

    @GetMapping(path = "/{type}/id/{id}")
    public ResponseEntity getById(@PathVariable("type") MediaType type, @PathVariable("id") String id) {

        Optional<? extends Media> searchedItem = serviceFactory.getServiceByType(type).getItemById(id);

        if (searchedItem.isPresent()) {
            return status(HttpStatus.OK).body(serviceFactory.getServiceByType(type).getItemById(id));
        } else {
            return status(HttpStatus.NOT_FOUND).body("ID not found");
        }
    }


    @DeleteMapping(path = "/delete/{type}/id/{id}")
    public ResponseEntity deleteItemById(@PathVariable("type") MediaType type, @PathVariable("id") String id) {


        if (serviceFactory.getServiceByType(type).getItemById(id).isPresent()) {
            serviceFactory.getServiceByType(type).deleteById(id);
            return status(HttpStatus.OK).body("id " + id + " has been deleted");
        } else {
            return status(HttpStatus.NOT_FOUND).body("id " + id + "  not found");
        }
    }


}
