package com.example.proyectou2.excepciones;

public class InvalidApartamentoException extends RuntimeException {
    public InvalidApartamentoException(String message) {
        super(message);
    }
}