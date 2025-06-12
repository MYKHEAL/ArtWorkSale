package org.example.exceptions;

public class invalidPriceException extends RuntimeException {
    public invalidPriceException(String message) {
        super(message);
    }
}
