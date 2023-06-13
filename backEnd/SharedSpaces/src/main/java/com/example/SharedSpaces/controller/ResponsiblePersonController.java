package com.example.SharedSpaces.controller;

import com.example.SharedSpaces.service.ResponsiblePersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("responsible")
public class ResponsiblePersonController {

    private final ResponsiblePersonService responsiblePersonService;

    @Autowired
    public ResponsiblePersonController(ResponsiblePersonService responsiblePersonService) {
        this.responsiblePersonService = responsiblePersonService;
    }

    @GetMapping
    public String getAllReservations(){
        return null;
    }

    @PostMapping
    public void addResevation(){

    }

    @PutMapping()
    public void updateResevation(){

    }

    @DeleteMapping()
    public  void deleteResevation(){

    }
}
