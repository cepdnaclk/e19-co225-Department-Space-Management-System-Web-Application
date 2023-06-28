package com.example.SharedSpaces.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "spaces")
// This class represents a shared space that can be reserved by users.
public class Space {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int capacity;
    private String facilities;
    private String image;

    public Space() {

    }

    // Constructor that takes the space ID, name, description, capacity, facilities, and image.
    public Space(Long id, String name, String description, int capacity, String facilities, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.capacity = capacity;
        this.facilities = facilities;
        this.image = image;
    }

    // Returns a list of facilities available in this space.
    public List<String> getFacilitiesList() {
        List<String> list = new ArrayList<>();

        for (String facilities : this.getFacilities().split("\\,")) {
            list.add(facilities);
        }

        return list;
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

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Space{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", capacity=" + capacity +
                ", facilities=" + getFacilitiesList() +
                ", image='" + image + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Space space = (Space) o;
        return capacity == space.capacity && Objects.equals(id, space.id) && Objects.equals(name, space.name)
                && Objects.equals(description, space.description) && Objects.equals(facilities, space.facilities)
                && Objects.equals(image, space.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, capacity, facilities, image);
    }
}