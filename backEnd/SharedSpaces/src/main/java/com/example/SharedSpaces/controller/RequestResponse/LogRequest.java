package com.example.SharedSpaces.controller.RequestResponse;

import java.util.Objects;

public class LogRequest {

    // Unique identifier of the client making the request
    private String clientId;

    // User's credential (e.g., password or token) for authentication
    private String credential;

    // Type of identifier used to authenticate the user (e.g., email or username)
    private String select_by;

    public LogRequest() {

    }

    public LogRequest(String clientId, String credential, String select_by) {
        this.clientId = clientId;
        this.credential = credential;
        this.select_by = select_by;
    }

    // Getter and setter methods for clientId
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    // Getter and setter methods for credential
    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    // Getter and setter methods for select_by
    public String getSelect_by() {
        return select_by;
    }

    public void setSelect_by(String select_by) {
        this.select_by = select_by;
    }

    // Override the toString() method to return a string representation of the object
    @Override
    public String toString() {
        return "LogRequest{" +
                "clientId='" + clientId + '\'' +
                ", credential='" + credential + '\'' +
                ", select_by='" + select_by + '\'' +
                '}';
    }

    // Override the equals() method to compare objects based on their fields
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LogRequest that = (LogRequest) o;
        return Objects.equals(clientId, that.clientId) && Objects.equals(credential, that.credential)
                && Objects.equals(select_by, that.select_by);
    }

    // Override the hashCode() method to generate a hash code based on the object's fields
    @Override
    public int hashCode() {
        return Objects.hash(clientId, credential, select_by);
    }
}