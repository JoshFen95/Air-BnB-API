package com.example.airbnbapi.service;


import com.example.airbnbapi.model.Media;
import com.example.airbnbapi.model.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ServiceFactory {

    Map<MediaType, ServiceInterface<? extends Media>> map;

    @Autowired
    public ServiceFactory(List<ServiceInterface<? extends Media>> serviceInterfaces){
        this.map = new HashMap<>();

        serviceInterfaces.stream().forEach(serviceInterface -> map.put(serviceInterface.getType(),serviceInterface));

    }


    public ServiceInterface<? extends Media> getServiceByType(MediaType type) {

     return map.get(type);
    }
}


