package com.demo.springcloud.microservice.products.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.demo.springcloud.microservice.products.entity.Product;
import com.demo.springcloud.microservice.products.record.ProductRequest;
import com.demo.springcloud.microservice.products.record.ProductResponse;
import com.demo.springcloud.microservice.products.service.IProductService;

//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

//@Tag(name = "ProductController", description = "API Productos")
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    // @Operation(summary = "Obtener listado de productos", description = "Obtiene
    // listado de productos", method = "GET")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> findAll() {
        return new ResponseEntity<>(this.productService.findAll(), HttpStatus.OK);
    }

    // @Operation(summary = "Obtener producto por id", description = "Obtiene
    // producto por id", method = "GET")
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        return new ResponseEntity<>(this.productService.findById(id), HttpStatus.OK);
    }

    // @Operation(summary = "Crear producto", description = "Crea un producto",
    // method = "POST")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest data) {
        return new ResponseEntity<>(this.productService.create(data), HttpStatus.CREATED);
    }

    // @Operation(summary = "Actualiza producto", description = "Actualiza un
    // producto", method = "PUT")
    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponse> update(@PathVariable Long id, @RequestBody ProductRequest data) {
        return new ResponseEntity<>(this.productService.update(id, data), HttpStatus.OK);
    }

    // @Operation(summary = "Elimina producto", description = "Elimina un producto",
    // method = "DELETE")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
