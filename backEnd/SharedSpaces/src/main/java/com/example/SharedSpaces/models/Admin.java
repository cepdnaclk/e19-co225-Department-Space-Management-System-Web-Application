package com.example.SharedSpaces.models;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import java.util.Date;


@Entity
@Table(name = "admin")
@PrimaryKeyJoinColumn(name = "id")

public class Admin extends User{

    private Date dateOfCreate;

    public Admin(String firstName, String lastName, String email, Date dateOfCreate){
        super(firstName, lastName, email);
        this.dateOfCreate = dateOfCreate;
    }

    public Admin(String firstName, String lastName, String email){
        super(firstName, lastName, email);
    }

    public Admin() {

    }
}
