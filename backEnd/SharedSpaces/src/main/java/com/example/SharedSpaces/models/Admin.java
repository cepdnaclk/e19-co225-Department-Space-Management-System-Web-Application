package com.example.SharedSpaces.models;

import java.util.Date;

public class Admin extends User{

    private Date dateOfCreate;

    public Admin(String firstName, String lastName, String email, Date dateOfCreate){
        super(firstName, lastName, email);
        this.dateOfCreate = dateOfCreate;
    }

    public Admin(String firstName, String lastName, String email){
        super(firstName, lastName, email);
    }

}
