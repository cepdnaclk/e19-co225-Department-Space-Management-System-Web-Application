package com.example.SharedSpaces.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;

@Data
@Entity
@Table(name = "spaces")
public class Space {

    // variable declaration
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int capacity;
    private ArrayList<String> facilities;
    private String image;

    public Space(Long id,String name, String description, int capacity, ArrayList<String> facilities, String image){
        this.id = id;
        this.name = name;
        this.description = description;
        this.capacity = capacity;
        this.facilities = facilities;
        this.image = image;
    }

    public Space() {

    }
}
