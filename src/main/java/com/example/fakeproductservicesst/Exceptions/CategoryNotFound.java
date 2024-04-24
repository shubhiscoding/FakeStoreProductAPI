package com.example.fakeproductservicesst.Exceptions;

import lombok.Getter;

@Getter
public class CategoryNotFound extends RuntimeException{
    private String category;
    public CategoryNotFound(String message, String category){
        super(message);
        this.category = category;
    }
}
