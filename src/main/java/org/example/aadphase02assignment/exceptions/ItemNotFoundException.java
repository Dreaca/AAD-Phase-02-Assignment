package org.example.aadphase02assignment.exceptions;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String message) {
        super(message);
    }
    public ItemNotFoundException(String message, Throwable cause) {}
    public ItemNotFoundException() {}
}
