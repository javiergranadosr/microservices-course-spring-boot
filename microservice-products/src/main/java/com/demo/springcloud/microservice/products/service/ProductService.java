package com.demo.springcloud.microservice.products.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.springcloud.microservice.products.entity.Product;
import com.demo.springcloud.microservice.products.exception.ErrorNotFoundException;
import com.demo.springcloud.microservice.products.record.ProductRequest;
import com.demo.springcloud.microservice.products.record.ProductResponse;
import com.demo.springcloud.microservice.products.repository.ProductRepository;

@Service
public class ProductService implements IProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return (List<Product>) this.repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Product findById(Long id) {
        Optional<Product> product = this.repository.findById(id);
        if (!product.isPresent()) {
            throw new ErrorNotFoundException("Product not found try again");
        }
        return product.get();
    }

    @Override
    @Transactional
    public ProductResponse create(ProductRequest data) {
        Product product = new Product();
        product.setName(data.name());
        product.setPrice(data.price());
        Product tempProduct = this.repository.save(product);
        ProductResponse response = new ProductResponse(
                tempProduct.getId(),
                tempProduct.getName(),
                tempProduct.getPrice(),
                tempProduct.getCreatedAt(),
                tempProduct.getUpdatedAt());

        return response;
    }

    @Override
    @Transactional
    public ProductResponse update(Long id, ProductRequest data) {
        Optional<Product> product = this.repository.findById(id);
        if (!product.isPresent()) {
            throw new ErrorNotFoundException("Product not found try again");
        }

        product.get().setName(data.name());
        product.get().setPrice(data.price());

        Product tempProduct = this.repository.save(product.get());
        ProductResponse response = new ProductResponse(
                tempProduct.getId(),
                tempProduct.getName(),
                tempProduct.getPrice(),
                tempProduct.getCreatedAt(),
                tempProduct.getUpdatedAt());

        return response;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional<Product> product = this.repository.findById(id);
        if (!product.isPresent()) {
            throw new ErrorNotFoundException("Product not found try again");
        }
        this.repository.delete(product.get());
    }

}
