package com.mobiliuz.demo.mobiliuzapp;

/**
 * Created by darhan on 2/5/15.
 */
public class Car {

    private int id;
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
