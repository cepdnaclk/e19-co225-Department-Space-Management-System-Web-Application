package com.example.first_demo.models;

import jakarta.persistence.Entity;

@Entity

public class User extends Person {

    public User(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }


    public User() {

    }
}

