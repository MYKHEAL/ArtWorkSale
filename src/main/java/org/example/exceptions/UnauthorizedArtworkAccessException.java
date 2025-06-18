package org.example.exceptions;

public class UnauthorizedArtworkAccessException extends RuntimeException {
    public UnauthorizedArtworkAccessException(String message) {
        super(message);
    }
}
