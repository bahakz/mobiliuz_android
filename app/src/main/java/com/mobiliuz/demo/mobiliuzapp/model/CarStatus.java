package com.mobiliuz.demo.mobiliuzapp.model;

/**
 * Created by darhan on 3/5/15.
 */
public class CarStatus {


    //{"dt": "2015-03-05T11:27:23", "id": 7246, "imei": "356308044918966", "is_moving": false, "is_online": true, "last_online_info": {"power_voltage": 12.0, "speed": 0, "ts": 1425554833, "where": [43.201208, 76.97554099999999]},

    private double longtitude;
    private double latitude;
    private boolean isMoving;
    private boolean isOnline;
    private double powerVoltage;
    private double speed;

    public CarStatus() {
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getPowerVoltage() {
        return powerVoltage;
    }

    public void setPowerVoltage(double powerVoltage) {
        this.powerVoltage = powerVoltage;
    }
}
