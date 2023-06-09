package com.example.SharedSpaces.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;

//@Data
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public ArrayList<String> getFacilities() {
        return facilities;
    }

    public void setFacilities(ArrayList<String> facilities) {
        this.facilities = facilities;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
