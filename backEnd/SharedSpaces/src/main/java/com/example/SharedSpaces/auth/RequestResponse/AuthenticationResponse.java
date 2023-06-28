package com.example.SharedSpaces.auth.RequestResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class AuthenticationResponse {

    // Fields for storing the access token and refresh token
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;

    // Constructor for creating a new AuthenticationResponse object with the given access token and refresh token
    public AuthenticationResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    // Getter method for the access token
    public String getAccessToken() {
        return accessToken;
    }

    // Setter method for the access token
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    // Getter method for the refresh token
    public String getRefreshToken() {
        return refreshToken;
    }

    // Setter method for the refresh token
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    // Override the toString() method to provide a string representation of this object
    @Override
    public String toString() {
        return "AuthenticationResponse{" +
                "accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }

    // Override the hashCode() method to ensure that objects with the same field values have the same hash code
    @Override
    public int hashCode() {
        return Objects.hash(accessToken, refreshToken);
    }

    // Override the equals() method to ensure that objects with the same field values are considered equal
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AuthenticationResponse that = (AuthenticationResponse) o;
        return Objects.equals(accessToken, that.accessToken) && Objects.equals(refreshToken, that.refreshToken);
    }
}