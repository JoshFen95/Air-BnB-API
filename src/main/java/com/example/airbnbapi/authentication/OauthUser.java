package com.example.airbnbapi.authentication;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "oauthUsers")
public class OauthUser {

    private UUID oauthCode;
    private String emailAddress;
    private String password;

    public UUID getOauthCode() {
        return oauthCode;
    }

    public void setOauthCode(UUID oauthCode) {
        this.oauthCode = oauthCode;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
