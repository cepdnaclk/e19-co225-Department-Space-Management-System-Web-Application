package com.example.SharedSpaces.controller.RequestResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LogResponse {

//    private User user;
//    private String role;
//
    private Boolean valid;

    private String refreshToken;

//    public LogResponse(User user, String role){
//        this.user = user;
//        this.role = role;
//    }

    public LogResponse(){

    }

    public LogResponse(String refreshToken){
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
}
