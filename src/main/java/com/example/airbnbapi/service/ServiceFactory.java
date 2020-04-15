package com.example.airbnbapi.service;
//

import com.example.airbnbapi.model.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ServiceFactory {

    Map<Integer, ServiceInterface<? extends Media>> map;

    @Autowired
    public ServiceFactory(List<ServiceInterface<? extends Media>> serviceInterfaces){
        this.map = new HashMap<>();

        serviceInterfaces.stream().forEach(serviceInterface -> map.put(serviceInterface.getAction(),serviceInterface));

    }

    public ServiceInterface<? extends Media> getService(int num) {

        return map.get(num);
    }
}
