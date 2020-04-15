package com.example.airbnbapi.service;

import com.example.airbnbapi.model.Media;

public interface ServiceInterface<T extends Media> {

T[] getItems();

int getAction();
}
