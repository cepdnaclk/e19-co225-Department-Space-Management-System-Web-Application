package com.example.SharedSpaces.models;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin")
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends User{

    public Admin(String firstName, String lastName, String email){
        super(firstName, lastName, email);
    }

    public Admin() {
    }
}
