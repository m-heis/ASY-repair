package com.example.asy;

public class ExecServices {
    public String service, price, description;

    public ExecServices() {
    }

    public ExecServices(String service, String price, String description) {
        this.service = service;
        this.price = price;
        this.description = description;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
