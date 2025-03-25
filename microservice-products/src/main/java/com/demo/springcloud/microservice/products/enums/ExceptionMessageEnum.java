package com.demo.springcloud.microservice.products.enums;

public enum ExceptionMessageEnum {
    ERROR_NOT_FOUND(404, "Product not found try again");

    private int code;
    private String message;

    private ExceptionMessageEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
