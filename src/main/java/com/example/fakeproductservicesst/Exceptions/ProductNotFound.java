package com.example.fakeproductservicesst.Exceptions;

import lombok.Getter;

@Getter
public class ProductNotFound extends RuntimeException {
    private Long id;
    public ProductNotFound(String message, Long id) {
        super(message);
        this.id = id;
    }
}
