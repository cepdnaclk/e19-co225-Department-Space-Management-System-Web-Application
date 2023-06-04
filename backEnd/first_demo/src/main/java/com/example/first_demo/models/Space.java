package com.example.first_demo.models;

import java.util.ArrayList;
import java.util.Objects;

// space class for a space
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscription() {
        return description;
    }

    public void setDiscription(String discription) {
        this.description = discription;
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

    @Override
    public String toString(){
        return "Space{" +
                "id=" + id +
                ", name=" + name + '\'' +
                ", description='" + description + '\'' +
                ", capacity='" + capacity + '\'' +
                ", facilities='" + facilities.toString() + '\'' +
                ", image=" + image +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, capacity, facilities, image);
    }

}
