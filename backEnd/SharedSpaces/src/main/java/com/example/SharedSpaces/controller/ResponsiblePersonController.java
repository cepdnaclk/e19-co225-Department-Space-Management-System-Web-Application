package com.example.SharedSpaces.controller;

import com.example.SharedSpaces.models.ResponsiblePerson;
import com.example.SharedSpaces.service.ResponsiblePersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("responsible")
public class ResponsiblePersonController {

    private final ResponsiblePersonService responsiblePersonService;

    @Autowired
    public ResponsiblePersonController(ResponsiblePersonService responsiblePersonService) {
        this.responsiblePersonService = responsiblePersonService;
    }

    @GetMapping
    public List<ResponsiblePerson> getAllResponsiblePerson(){
        return responsiblePersonService.getAllResponsiblePersons();
    }

}
