package com.demo.springcloud.microservice.products.exception;

public class ErrorNotFoundException extends RuntimeException {

  public ErrorNotFoundException(String message) {
    super(message);
  }
}
