package com.example.SharedSpaces.exception;

// This class represents a custom exception that is thrown when a reservation cannot be made
// because the space is already reserved by someone else.
public class AllReadyReservedException extends Exception {

    // Constructor that takes an error message as a parameter and passes it to the parent Exception class
    public AllReadyReservedException(String errorMessage) {
        super(errorMessage);
    }
}