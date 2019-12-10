package com.app.dao;
import com.app.entities.*;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.RequestBody;

public interface ProductRepository extends MongoRepository<Product, String> {


List<Product>findBydesignation(String Des);
List<Product>findBydesignationContaining(String designation);
List<Product>findBypriceBetween(float a, float b);
}
