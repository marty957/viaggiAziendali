package com.example.viaggiAziendali.exception;

public class NotFoundExcep extends RuntimeException {
    public NotFoundExcep(String message) {
        super(message);
    }

    public NotFoundExcep(long id) {
        super(id + " non trovato!");
    }
}
