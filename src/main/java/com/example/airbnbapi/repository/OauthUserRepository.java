package com.example.airbnbapi.repository;

import com.example.airbnbapi.authentication.OauthUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface OauthUserRepository extends MongoRepository<OauthUser, String> {
}
