package com.example.SharedSpaces.controller.RequestResponse;

import java.util.Objects;

public class LogResponse {

    private Boolean valid;
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

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public String toString() {
        return "LogResponse{" +
                "valid=" + valid +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LogResponse response = (LogResponse) o;
        return Objects.equals(valid, response.valid) && Objects.equals(refreshToken, response.refreshToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valid, refreshToken);
    }
}
