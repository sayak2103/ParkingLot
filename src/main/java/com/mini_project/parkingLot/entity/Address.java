package com.mini_project.parkingLot.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    private String firstLine;
    private String city;
    private String state;
    private String country;
    private String pincode;
    private String landmark;

    public Address(){

    }

    public Address(String firstLine, String city, String state, String country, String pincode, String landmark) {
        this.firstLine = firstLine;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
        this.landmark = landmark;
    }

    public String getFirstLine() {
        return firstLine;
    }

    public void setFirstLine(String firstLine) {
        this.firstLine = firstLine;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }
}
