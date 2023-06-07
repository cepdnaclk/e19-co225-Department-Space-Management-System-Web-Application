package com.example.SharedSpaces.models;


import java.util.Date;

public class ResponsiblePerson extends User{

    private String type;
    private Date dateOfCreate;

    public ResponsiblePerson(String name, String email){
        super(name, email);
    }

    public ResponsiblePerson(String name, String email, String type, Date dateOfCreate){
        super(name, email);
        this.type = type;
        this.dateOfCreate = dateOfCreate;
    }

}
