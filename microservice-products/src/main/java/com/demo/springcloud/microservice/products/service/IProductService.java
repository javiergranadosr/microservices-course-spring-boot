package com.demo.springcloud.microservice.products.service;

import java.util.List;

import com.demo.springcloud.microservice.products.record.ProductRequest;
import com.demo.springcloud.microservice.products.record.ProductResponse;

public interface IProductService {

    List<ProductResponse> findAll();

    ProductResponse findById(Long id);

    ProductResponse create(ProductRequest data);

    ProductResponse update(Long id, ProductRequest data);

    void delete(Long id);
}
