package com.demo.springcloud.microservice.products.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.springcloud.microservice.products.entity.Product;
import com.demo.springcloud.microservice.products.enums.ExceptionMessageEnum;
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
    public List<ProductResponse> findAll() {
        List<Product> products = (List<Product>) this.repository.findAll();
        return products.stream()
                .map(p -> new ProductResponse(
                        p.getId(),
                        p.getName(),
                        p.getPrice(),
                        p.getCreatedAt(),
                        p.getUpdatedAt()))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse findById(Long id) {
        Optional<Product> product = this.repository.findById(id);
        if (!product.isPresent()) {
            throw new ErrorNotFoundException(ExceptionMessageEnum.ERROR_NOT_FOUND.getMessage());
        }
        Product tempProduct = product.get();
        return new ProductResponse(
                tempProduct.getId(),
                tempProduct.getName(),
                tempProduct.getPrice(),
                tempProduct.getCreatedAt(),
                tempProduct.getUpdatedAt());
    }

    @Override
    @Transactional
    public ProductResponse create(ProductRequest data) {
        Product product = new Product();
        product.setName(data.name());
        product.setPrice(data.price());
        Product tempProduct = this.repository.save(product);
        return new ProductResponse(
                tempProduct.getId(),
                tempProduct.getName(),
                tempProduct.getPrice(),
                tempProduct.getCreatedAt(),
                tempProduct.getUpdatedAt());
    }

    @Override
    @Transactional
    public ProductResponse update(Long id, ProductRequest data) {
        Optional<Product> product = this.repository.findById(id);
        if (!product.isPresent()) {
            throw new ErrorNotFoundException(ExceptionMessageEnum.ERROR_NOT_FOUND.getMessage());
        }

        product.get().setName(data.name());
        product.get().setPrice(data.price());

        Product tempProduct = this.repository.save(product.get());
        return new ProductResponse(
                tempProduct.getId(),
                tempProduct.getName(),
                tempProduct.getPrice(),
                tempProduct.getCreatedAt(),
                tempProduct.getUpdatedAt());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional<Product> product = this.repository.findById(id);
        if (!product.isPresent()) {
            throw new ErrorNotFoundException(ExceptionMessageEnum.ERROR_NOT_FOUND.getMessage());
        }
        this.repository.delete(product.get());
    }
}
