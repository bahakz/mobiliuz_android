package com.mobiliuz.demo.mobiliuzapp;

/**
 * Created by darhan on 2/5/15.
 */
public class Car {

    private String id;
    private String model;
    private String fuelConsumption;
    private boolean is_moving;
    private boolean is_online;
    private double power_voltage;
    private double latitude;
    private double longitude;


    public Car(){


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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


}
