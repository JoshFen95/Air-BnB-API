package com.example.airbnbapi.api;

import com.example.airbnbapi.model.Media;
import com.example.airbnbapi.model.MediaType;
import com.example.airbnbapi.service.ServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.status;


import java.util.List;
import java.util.Optional;


@RequestMapping("api/v1/media")
@RestController
public class Controller {

    @Autowired
    private  ServiceFactory serviceFactory;


    // NEED TO ADD A PUT METHOD


    @RequestMapping(value = "/{type}", method = RequestMethod.GET)
    public ResponseEntity getAll(@PathVariable("type") MediaType type) {
        List<? extends Media> items = serviceFactory.getServiceByType(type).getItems();

        return status(HttpStatus.OK).body(items);

    }

    @RequestMapping(value = "/{type}/id/{id}", method = RequestMethod.GET)
    public ResponseEntity getById(@PathVariable("type") MediaType type, @PathVariable("id") String id) {

        Optional<? extends Media> searchedItem = serviceFactory.getServiceByType(type).getItemById(id);

        if (searchedItem.isPresent()) {
            return status(HttpStatus.OK).body(serviceFactory.getServiceByType(type).getItemById(id));
        } else {
            return status(HttpStatus.NOT_FOUND).body("ID not found");
        }
    }


    @RequestMapping(value = "/delete/{type}/id/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteItemById(@PathVariable("type") MediaType type, @PathVariable("id") String id) {


        if (serviceFactory.getServiceByType(type).getItemById(id).isPresent()) {
            serviceFactory.getServiceByType(type).deleteById(id);
            return status(HttpStatus.OK).body("id " + id + " has been deleted");
        } else {
            return status(HttpStatus.NOT_FOUND).body("id " + id + "  not found");
        }
    }


}
