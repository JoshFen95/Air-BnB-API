package com.example.airbnbapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import org.springframework.data.annotation.Id;



@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type", visible = false)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Game.class, name = "Game"),

        @JsonSubTypes.Type(value = Film.class, name = "Film") }
)


public class Media {


    @Id
   @JsonProperty("id")
    private String id;
    @JsonProperty("title")
    private  String title;
    @JsonProperty("year")
    private long year;
    @JsonProperty("creator")
    private String creator;
    @JsonProperty("url")
    private String url;
    @JsonProperty("videoUrl")
    private String videoUrl;



    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }


    public long getYear() {
        return year;
    }


    public String getCreator() {
        return creator;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(long year) {
        this.year = year;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "ID: (" + id +
                        "), Title: " + title +
                        ", Year: " + year +
                        ", Creator: " + creator;
    }
}
