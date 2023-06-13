package com.example.SharedSpaces.service;

import com.example.SharedSpaces.db.ResponsiblePersonDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponsiblePersonService {

    private final ResponsiblePersonDB responsiblePersonDB;

    @Autowired
    public ResponsiblePersonService( ResponsiblePersonDB responsiblePersonDB){
        this.responsiblePersonDB = responsiblePersonDB;
    }



}
