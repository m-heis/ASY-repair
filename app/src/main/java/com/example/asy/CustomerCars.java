package com.example.asy;

public class CustomerCars {
    public String brand, model, year, engineCapacity, vinCode, mileage;

    public CustomerCars(){

    }

    public CustomerCars(String brand, String model, String year, String engineCapacity, String vinCode, String mileage) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.engineCapacity = engineCapacity;
        this.vinCode = vinCode;
        this.mileage = mileage;
    }
}
