package com.example.SharedSpaces.models;


import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "responsible_person")
@PrimaryKeyJoinColumn(name = "id")
public class ResponsiblePerson extends User{

    private String type;

    @Override
    public String toString() {
        return "ResponsiblePerson{" +
                "type='" + type + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ResponsiblePerson(){

    }

    public ResponsiblePerson(String firstName, String lastName, String email){
        super(firstName, lastName, email);
    }

    public ResponsiblePerson(String firstName, String lastName, String email, String type){
        super(firstName, lastName, email);
        this.type = type;
    }

    public String fullName(){
        return this.type + " " + getFirstName() + " " + getLastName();
    }



}
