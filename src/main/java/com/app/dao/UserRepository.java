package com.app.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.app.entities.Product;
import com.app.entities.User;

public interface UserRepository extends MongoRepository<User, String>{

}
