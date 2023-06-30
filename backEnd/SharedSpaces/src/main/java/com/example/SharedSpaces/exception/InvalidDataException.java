package com.example.SharedSpaces.exception;

// This class represents a custom exception that is thrown when data provided to a method is invalid or incomplete.
public class InvalidDataException extends Exception {

    // Constructor that takes an error message as a parameter and passes it to the parent Exception class
    public InvalidDataException(String errorMessage) {
        super(errorMessage);
    }
}