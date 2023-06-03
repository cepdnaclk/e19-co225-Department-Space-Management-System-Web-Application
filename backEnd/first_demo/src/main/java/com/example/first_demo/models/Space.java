package com.example.first_demo.models;

import java.util.ArrayList;

public class Space {

    private String name;
    private String discription;
    private int capacitor;
    private ArrayList<String> facilities =  new ArrayList<String>();
    private String image;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public int getCapacitor() {
        return capacitor;
    }

    public void setCapacitor(int capacitor) {
        this.capacitor = capacitor;
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
                "name=" + name +
                ", discription='" + discription + '\'' +
                ", capacitor='" + capacitor + '\'' +
                ", facilities='" + facilities.toString() + '\'' +
                ", image=" + image +
                '}';
    }
}
