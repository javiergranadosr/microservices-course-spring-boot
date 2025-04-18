package com.demo.springcloud.microservice.products.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.springcloud.microservice.products.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
