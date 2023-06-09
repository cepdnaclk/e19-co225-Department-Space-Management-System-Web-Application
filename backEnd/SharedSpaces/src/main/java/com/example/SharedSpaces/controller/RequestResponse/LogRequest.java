package com.example.SharedSpaces.controller.RequestResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogRequest {

    private String clientId;
    private String credential;
    private String select_by;


}
