package com.example.airbnbapi.model;

import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class MediaTypeConverter extends PropertyEditorSupport {

    private static final Map<String, MediaType> MEDIA_TYPE_MAP = new HashMap<>();

    public MediaTypeConverter() {

        Arrays.stream(MediaType.values()).forEach( mediaType -> MEDIA_TYPE_MAP.put(mediaType.getType(), mediaType));
    }

    @Override
    public void setAsText(final String type) {

        MediaType mediaType = MEDIA_TYPE_MAP.get(type);

        if(mediaType == null) {

            throw new RuntimeException("No Account Type found for: " + type);
        }

        setValue(mediaType);
    }
}
