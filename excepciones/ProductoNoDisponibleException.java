package com.example.excepciones;

//excepcion personalizada de un cliente no registrado
public class ProductoNoDisponibleException extends Exception {

    //constructor que envia el mensaje del error
    public ProductoNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}
