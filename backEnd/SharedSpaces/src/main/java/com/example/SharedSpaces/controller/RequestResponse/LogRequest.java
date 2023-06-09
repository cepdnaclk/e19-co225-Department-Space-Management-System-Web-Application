package com.example.SharedSpaces.controller.RequestResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@Builder
//@AllArgsConstructor
@NoArgsConstructor
public class LogRequest {

    private String clientId;
    private String credential;
    private String select_by;

    public LogRequest(){

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
