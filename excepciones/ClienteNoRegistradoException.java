package com.example.excepciones;

//excepcion personalizada de un cliente no registrado
public class ClienteNoRegistradoException extends Exception {

    //constructor que envia el mensaje del error
    public ClienteNoRegistradoException(String mensaje) {
        super(mensaje);
    }
}
