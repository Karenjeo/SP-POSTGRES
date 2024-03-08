package com.example.proyectou2.excepciones;

public class InvalidPisosException extends RuntimeException {
    public InvalidPisosException(String message) {
        super(message);
    }
}