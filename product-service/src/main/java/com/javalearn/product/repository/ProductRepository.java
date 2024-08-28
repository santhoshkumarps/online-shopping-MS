package com.javalearn.product.repository;

import com.javalearn.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,String> {

    Boolean existsByproductSkuCode(String productSkuCode);

}



