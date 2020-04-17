package com.example.airbnbapi.service;

import com.example.airbnbapi.model.Media;
import com.example.airbnbapi.model.MediaType;


public interface ServiceInterface<T extends Media> {

T[] getItems();

int getAction();

T getMediaById(int id);

MediaType getType();
}