package com.example.first_demo.models;

import jakarta.persistence.Entity;

@Entity
public class ResponsiblePerson extends  Person{

    public ResponsiblePerson(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }

    public ResponsiblePerson() {
        super();
    }
}
