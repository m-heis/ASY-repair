package com.example.asy;

public class CustomerOrders {
    public String city, section, car, description;

    public CustomerOrders(){

    }

    public CustomerOrders(String city, String section, String car, String description) {
        this.city = city;
        this.section = section;
        this.car = car;
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
