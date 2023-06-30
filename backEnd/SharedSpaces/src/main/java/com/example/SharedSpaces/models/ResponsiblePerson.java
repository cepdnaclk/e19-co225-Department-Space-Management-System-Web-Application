package com.example.SharedSpaces.models;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "responsible_person")
@PrimaryKeyJoinColumn(name = "id")
// This class represents a responsible person who is assigned to manage a shared space.
public class ResponsiblePerson extends User {

    private String type;

    public ResponsiblePerson() {

    }

    // Constructor that takes the responsible person's first name, last name, and email address.
    public ResponsiblePerson(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }

    // Constructor that takes the responsible person's first name, last name, email address, and type.
    public ResponsiblePerson(String firstName, String lastName, String email, String type) {
        super(firstName, lastName, email);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Returns the full name of the responsible person, including their type (e.g. "Manager John Doe").
    public String fullName() {
        return this.type + " " + getFirstName() + " " + getLastName();
    }

    @Override
    public String toString() {
        return "ResponsiblePerson{" +
                "type='" + type + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }

}