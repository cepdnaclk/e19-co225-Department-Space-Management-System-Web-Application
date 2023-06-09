package com.example.SharedSpaces.controller.RequestResponse;

import java.util.Objects;

public class LogRequest {

    private String clientId;
    private String credential;
    private String select_by;

    public LogRequest(){

    }

    @Override
    public String toString() {
        return "LogRequest{" +
                "clientId='" + clientId + '\'' +
                ", credential='" + credential + '\'' +
                ", select_by='" + select_by + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogRequest that = (LogRequest) o;
        return Objects.equals(clientId, that.clientId) && Objects.equals(credential, that.credential) && Objects.equals(select_by, that.select_by);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, credential, select_by);
    }

    public LogRequest(String clientId, String credential, String select_by){
        this.clientId = clientId;
        this.credential = credential;
        this.select_by = select_by;
    }


    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public String getSelect_by() {
        return select_by;
    }

    public void setSelect_by(String select_by) {
        this.select_by = select_by;
    }
}
