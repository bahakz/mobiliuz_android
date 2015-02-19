package com.mobiliuz.demo.mobiliuzapp;

/**
 * Created by darhan on 2/5/15.
 */
public class Car {

    private int id;
    private String model;
    private String fuelConsumption;
    private double powerVoltage;
    private double latitude;
    private double longitude;


    public Car(){


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(String fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public double getPowerVoltage() {
        return powerVoltage;
    }

    public void setPowerVoltage(double powerVoltage) {
        this.powerVoltage = powerVoltage;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
