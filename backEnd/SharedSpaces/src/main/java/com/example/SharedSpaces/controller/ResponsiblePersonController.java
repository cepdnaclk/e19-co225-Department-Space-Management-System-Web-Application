package com.example.SharedSpaces.controller;

import com.example.SharedSpaces.service.ResponsiblePersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("responsible")
public class ResponsiblePersonController {

    private final ResponsiblePersonService responsiblePersonService;

    @Autowired
    public ResponsiblePersonController(ResponsiblePersonService responsiblePersonService) {
        this.responsiblePersonService = responsiblePersonService;
    }

//    @GetMapping
//    public String getAllResponsiblePerson(){
//        return null;
//    }
//
//    @PostMapping
//    public void addResponsiblePerson(){
//
//    }
//
//    @PutMapping()
//    public void updateResponsiblePerson(){
//
//    }
//
//    @DeleteMapping()
//    public  void deleteResponsiblePersonn(){
//
//    }
}
