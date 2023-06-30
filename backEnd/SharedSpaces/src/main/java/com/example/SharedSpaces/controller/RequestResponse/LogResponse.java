package com.example.SharedSpaces.controller.RequestResponse;

import java.util.Objects;

public class LogResponse {

    // Indicates whether the user is authenticated or not
    private Boolean valid;

    // The refresh token used for authentication
    private String refreshToken;

    public LogResponse() {

    }

    public LogResponse(boolean valid, String refreshToken) {
        this.valid = valid;
        this.refreshToken = refreshToken;
    }

    public LogResponse(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    // Getter and setter methods for valid
    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    // Getter and setter methods for refreshToken
    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    // Override the toString() method to return a string representation of the object
    @Override
    public String toString() {
        return "LogResponse{" +
                "valid=" + valid +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }

    // Override the equals() method to compare objects based on their fields
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LogResponse response = (LogResponse) o;
        return Objects.equals(valid, response.valid) && Objects.equals(refreshToken, response.refreshToken);
    }

    // Override the hashCode() method to generate a hash code based on the object's fields
    @Override
    public int hashCode() {
        return Objects.hash(valid, refreshToken);
    }
}