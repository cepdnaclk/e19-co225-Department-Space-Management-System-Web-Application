package com.example.SharedSpaces.models;


import java.util.Date;

public class ResponsiblePerson extends User{

    private String type;
    private Date dateOfCreate;

    public ResponsiblePerson(String firstName, String lastName, String email){
        super(firstName, lastName, email);
    }

    public ResponsiblePerson(String firstName, String lastName, String email, String type, Date dateOfCreate){
        super(firstName, lastName, email);
        this.type = type;
        this.dateOfCreate = dateOfCreate;
    }

}
