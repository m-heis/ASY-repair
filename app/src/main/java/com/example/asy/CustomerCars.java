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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getVinCode() {
        return vinCode;
    }

    public void setVinCode(String vinCode) {
        this.vinCode = vinCode;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }
}
