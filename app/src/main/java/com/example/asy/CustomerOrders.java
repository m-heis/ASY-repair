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
}
