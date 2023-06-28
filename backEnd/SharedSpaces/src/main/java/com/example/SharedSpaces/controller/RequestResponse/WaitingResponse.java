package com.example.SharedSpaces.controller.RequestResponse;

public class WaitingResponse {

    // The name of the waiting request
    private String name;

    // The name of the person responsible for the waiting request
    private String responsiblePerson;

    public WaitingResponse() {

    }

    // Constructor for creating a new waiting response object
    public WaitingResponse(String name, String responsiblePerson) {
        this.name = name;
        this.responsiblePerson = responsiblePerson;
    }

    // Getter and setter methods for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and setter methods for responsiblePerson
    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    // Override the toString() method to return a string representation of the object
    @Override
    public String toString() {
        return "WaitingResponse{" +
                "name='" + name + '\'' +
                ", responsiblePerson='" + responsiblePerson + '\'' +
                '}';
    }
}

