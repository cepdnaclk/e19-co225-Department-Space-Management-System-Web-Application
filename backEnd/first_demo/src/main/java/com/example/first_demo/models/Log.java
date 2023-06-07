package com.example.first_demo.models;

import jakarta.persistence.Entity;

import java.util.Objects;
@Entity

public class Log {

    private String userName;
    private String role;
    private String email;

    public Log(String userName, String role, String email){
        this.userName = userName;
        this.role = role;
        this.email = email;
    }

    public Log() {

    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Log{" +
                "userName='" + userName + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Log log = (Log) o;
        return Objects.equals(userName, log.userName) && Objects.equals(role, log.role) && Objects.equals(email, log.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, role, email);
    }
}
