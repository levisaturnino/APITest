package br.com.levi.apiteste.services.exceptions;

public class ObjectnotFoundException extends RuntimeException {

    public ObjectnotFoundException(String message) {
        super(message);
    }

    public ObjectnotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
