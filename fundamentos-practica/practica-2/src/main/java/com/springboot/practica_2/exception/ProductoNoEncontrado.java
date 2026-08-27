package com.springboot.practica_2.exception;

public class ProductoNoEncontrado extends RuntimeException{
    public ProductoNoEncontrado(String mensaje){
        super(mensaje);
    }
}
