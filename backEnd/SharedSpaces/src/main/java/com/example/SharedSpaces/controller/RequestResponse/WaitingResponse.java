package com.example.SharedSpaces.controller.RequestResponse;

public class WaitingResponse {

    private String name;
    private String responsiblePerson;

    public WaitingResponse(){

    }

    public WaitingResponse(String name, String responsiblePerson) {
        this.name = name;
        this.responsiblePerson = responsiblePerson;
    }

    @Override
    public String toString() {
        return "WaitingResponse{" +
                "name='" + name + '\'' +
                ", responsiblePerson='" + responsiblePerson + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }
}
