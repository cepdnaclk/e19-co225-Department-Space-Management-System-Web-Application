package com.example.first_demo.models;

import jakarta.persistence.Entity;

@Entity

public class Admin extends Person {

    public Admin(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }

    public Admin() {

    }
}
