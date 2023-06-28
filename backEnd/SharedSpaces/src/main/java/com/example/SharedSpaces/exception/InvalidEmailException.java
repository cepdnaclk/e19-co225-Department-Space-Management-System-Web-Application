package com.example.SharedSpaces.exception;

// This class represents a custom exception that is thrown when an email address is invalid or malformed.
public class InvalidEmailException extends Exception {

    // Constructor that takes an error message as a parameter and passes it to the parent Exception class
    public InvalidEmailException(String errorMessage) {
        super(errorMessage);
    }
}