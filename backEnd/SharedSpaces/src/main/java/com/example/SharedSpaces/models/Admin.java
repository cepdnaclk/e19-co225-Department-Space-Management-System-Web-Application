package com.example.SharedSpaces.models;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "admin")
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends User{

    private Date dateOfCreate;

    public Admin(String firstName, String lastName, String email, Date dateOfCreate){
        super(firstName, lastName, email);
        this.dateOfCreate = dateOfCreate;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "dateOfCreate=" + dateOfCreate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(dateOfCreate, admin.dateOfCreate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateOfCreate);
    }

    public Date getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(Date dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    public Admin(String firstName, String lastName, String email){
        super(firstName, lastName, email);
    }

    public Admin() {
    }
}
