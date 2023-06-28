package com.example.SharedSpaces.models;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin")
@PrimaryKeyJoinColumn(name = "id")
// This class represents an administrator user in the system.
public class Admin extends User {

    // Constructor that takes the administrator's first name, last name, and email address.
    public Admin(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }

    public Admin() {
    }
}