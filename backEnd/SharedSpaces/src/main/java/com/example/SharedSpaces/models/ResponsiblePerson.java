package com.example.SharedSpaces.models;


import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "responsible_person")
@PrimaryKeyJoinColumn(name = "id")
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

    public ResponsiblePerson() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(Date dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }
}
