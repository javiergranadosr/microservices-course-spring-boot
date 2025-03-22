package com.demo.springcloud.microservice.products.record;

import java.time.LocalDate;

public record ProductResponse(
        Long id,
        String name,
        double price,
        LocalDate createdAt,
        LocalDate updatedAt) {
}
