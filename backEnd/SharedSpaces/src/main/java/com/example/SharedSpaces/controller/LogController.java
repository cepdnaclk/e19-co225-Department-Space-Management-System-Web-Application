package com.example.SharedSpaces.controller;

import com.example.SharedSpaces.models.User;
import com.example.SharedSpaces.service.LogService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("log")
public class LogController {

    private final LogService log;

    public LogController(LogService log) {
        this.log = log;
    }

    @GetMapping
    public String getLogging() {
        return "log";
    }

    @PostMapping
    public User getLog(@RequestBody String data) {
        String token = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjYwODNkZDU5ODE2NzNmNjYxZmRlOWRhZTY0NmI2ZjAzODBhMDE0NWMiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJuYmYiOjE2ODU4ODk5ODMsImF1ZCI6IjQ2MTQxODU0MTA2Ni01YzlwMmNmMGQ2ZDhxdGhoZ2g3bjJ0anJkMjhwZjN0OS5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsInN1YiI6IjEwNjM2MjA3Mzk1Mzg1MzM1NDQ1MiIsImVtYWlsIjoia2FuaXNoa2FndW5hd2FydGhhbmFAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImF6cCI6IjQ2MTQxODU0MTA2Ni01YzlwMmNmMGQ2ZDhxdGhoZ2g3bjJ0anJkMjhwZjN0OS5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsIm5hbWUiOiJLYW5pc2hrYSBHdW5hd2FyZGFuYSIsInBpY3R1cmUiOiJodHRwczovL2xoMy5nb29nbGV1c2VyY29udGVudC5jb20vYS9BQWNIVHRjT0FSTnN4VmdzZDliUmQwdnluS1Z3RFdUR2VDLTU5N2pYRWRCTU1nPXM5Ni1jIiwiZ2l2ZW5fbmFtZSI6IkthbmlzaGthIiwiZmFtaWx5X25hbWUiOiJHdW5hd2FyZGFuYSIsImlhdCI6MTY4NTg5MDI4MywiZXhwIjoxNjg1ODkzODgzLCJqdGkiOiJjODllYWI5YzUzZGE2YjA2MDRmMzhmNDNjMmU4MmY4OGY3NTRlMTRiIn0.Lu_aw7EdrT2f3pOQW8zYLzY6GmgDHPKGCKyoMzND6gEjCdsLxQdugPc8A4JgJNfpUd_cKk3ZUcQiSVTni4uUOcm5aD7KU_Wuy1EOJNHwORUuWVwfGk-nSX4TMuXurtqb7w92E0mnHNg3VTk6WogmInuLrruiwFKUysQ5-6Hqcwbr4UM0yvmzoobfpHYEFw-RFcWvr4npdcGJqtX3UgaFKH3VQjzYpttlQfKCvnCgyb80oIy4xvLhuMD8TwVjEQGB69B5Y7iK_-CEhSJ9hb-XvGTmX-bHX6WGGXrSw700_6z_6B9gbBlgEcFnRRVkGFjNbGw9zhiNOpCbpRlO2v8q5Q";

//        return log.extractClaims(data.substring(7, data.length()));
        return log.extractClaims(token);
    }


    @PutMapping()
    public void updateLogging(){

    }

    @DeleteMapping()
    public  void deleteLogging(){

    }


}
