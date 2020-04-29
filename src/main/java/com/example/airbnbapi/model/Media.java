package com.example.airbnbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
public abstract class Media {


    @Id
   @JsonProperty("id")
    private String id;
    @JsonProperty("title")
    private  String title;
    @JsonProperty("year")
    private  long year;
    @JsonProperty("creator")
    private  String creator;


    public String getTitle() {
        return title;
    }


    public long getYear() {
        return year;
    }


    public String getCreator() {
        return creator;
    }


    @Override
    public String toString() {
        return
                "id='" +'\'' +
                        ", title='" + title + '\'' +
                        ", year=" + year +
                        ", authors=" + creator +
                        '}';
    }
}
