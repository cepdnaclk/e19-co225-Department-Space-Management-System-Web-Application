package com.example.SharedSpaces.exception;

// This class represents a custom exception that is thrown when an email is invalid or cannot be processed.
public class EmailException extends Exception {

    // Constructor that takes an error message as a parameter and passes it to the parent Exception class
    public EmailException(String errorMessage) {
        super(errorMessage);
    }
}