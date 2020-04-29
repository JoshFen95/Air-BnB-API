package com.example.airbnbapi.service;

import com.example.airbnbapi.model.Media;
import com.example.airbnbapi.model.MediaType;

import java.util.List;
import java.util.Optional;


public interface ServiceInterface<T extends Media> {

List<T> getItems();

Optional<T> getItemById(String id);

MediaType getType();

void deleteById(String id);
}
