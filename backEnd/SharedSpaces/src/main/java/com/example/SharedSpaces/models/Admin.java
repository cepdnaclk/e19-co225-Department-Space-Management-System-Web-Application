package com.example.SharedSpaces.models;

import java.util.Date;

public class Admin extends User{

    private Date dateOfCreate;

    public Admin(String name, String email, Date dateOfCreate){
        super(name, email);
        this.dateOfCreate = dateOfCreate;
    }

    public Admin(String name, String email){
        super(name, email);
    }

}
