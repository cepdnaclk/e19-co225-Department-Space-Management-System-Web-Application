package com.example.SharedSpaces.service;

import com.example.SharedSpaces.db.ResponsiblePersonDB;
import com.example.SharedSpaces.models.ResponsiblePerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponsiblePersonService {

    private final ResponsiblePersonDB responsiblePersonDB;

    @Autowired
    public ResponsiblePersonService( ResponsiblePersonDB responsiblePersonDB){
        this.responsiblePersonDB = responsiblePersonDB;
    }

    public List<ResponsiblePerson> getAllResponsiblePersons(){
        return responsiblePersonDB.getAllResponsiblePersons();
    }

}
