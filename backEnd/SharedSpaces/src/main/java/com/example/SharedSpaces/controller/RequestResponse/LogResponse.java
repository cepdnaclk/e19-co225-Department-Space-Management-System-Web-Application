package com.example.SharedSpaces.controller.RequestResponse;

import com.example.SharedSpaces.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    public LogResponse(String refreshToken){
        this.refreshToken = refreshToken;
    }

}
