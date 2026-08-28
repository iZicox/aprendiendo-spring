package com.example.practica3.exception;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(String mensaje) {
        super(mensaje);
    }
}
