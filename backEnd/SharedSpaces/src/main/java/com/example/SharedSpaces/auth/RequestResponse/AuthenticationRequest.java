package com.example.SharedSpaces.auth.RequestResponse;

import java.util.Objects;

public class AuthenticationRequest {

    // Fields for storing the user's email, first name, and last name
    private String email;
    private String firstName;
    private String lastName;

    // Default constructor
    public AuthenticationRequest() {
    }

    // Getter method for the user's email
    public String getEmail() {
        return email;
    }

    // Setter method for the user's email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter method for the user's first name
    public String getFirstName() {
        return firstName;
    }

    // Setter method for the user's first name
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Getter method for the user's last name
    public String getLastName() {
        return lastName;
    }

    // Setter method for the user's last name
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Override the toString() method to provide a string representation of this object
    @Override
    public String toString() {
        return "AuthenticationRequest{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    // Override the hashCode() method to ensure that objects with the same field values have the same hash code
    @Override
    public int hashCode() {
        return Objects.hash(email, firstName, lastName);
    }

    // Override the equals() method to ensure that objects with the same field values are considered equal
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AuthenticationRequest that = (AuthenticationRequest) o;
        return Objects.equals(email, that.email) && Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName);
    }
}