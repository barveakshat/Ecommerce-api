package com.task.ecommerce.exceptions;

public class itemNotFoundException extends RuntimeException{
    public itemNotFoundException(Long id) {
        super("Product not found with id: " + id);
    }

    public itemNotFoundException(String message) {
        super(message);
    }
}
