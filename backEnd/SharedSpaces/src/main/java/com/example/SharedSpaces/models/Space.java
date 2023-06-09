package com.example.SharedSpaces.models;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Space {

    // variable declaration
    private int id;
    private String name;
    private String description;
    private int capacity;
    private ArrayList<String> facilities;
    private String image;

    public Space(int id,String name, String description, int capacity, ArrayList<String> facilities, String image){
        this.id = id;
        this.name = name;
        this.description = description;
        this.capacity = capacity;
        this.facilities = facilities;
        this.image = image;
    }

}
