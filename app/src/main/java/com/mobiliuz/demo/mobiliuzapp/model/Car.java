package com.mobiliuz.demo.mobiliuzapp.model;


public class Car {

    private int backendID;
    private String make;
    private String model;
    private CarStatus lastStatus;

    public Car(){


    }

    public int getBackendID() {
        return backendID;
    }

    public void setBackendID(int backendID) {
        this.backendID = backendID;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public CarStatus getLastStatus() {
        return lastStatus;
    }

    public void setLastStatus(CarStatus lastStatus) {
        this.lastStatus = lastStatus;
    }

}
