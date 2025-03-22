package com.demo.springcloud.microservice.products.service;

import java.util.List;

import com.demo.springcloud.microservice.products.entity.Product;
import com.demo.springcloud.microservice.products.record.ProductRequest;
import com.demo.springcloud.microservice.products.record.ProductResponse;

public interface IProductService {

    public List<Product> findAll();

    public Product findById(Long id);

    public ProductResponse create(ProductRequest data);

    public ProductResponse update(Long id, ProductRequest data);

    public void delete(Long id);

}
