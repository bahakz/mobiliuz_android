package com.mobiliuz.demo.mobiliuzapp;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by darhan on 2/11/15.
 */
public class DataHolder {

    private static ArrayList<Car> cars;
    private static DataHolder dataHolder;

    ArrayList<CarChangedListener> carChangedListeners;

    public static DataHolder getDataHolder(){
        if(dataHolder == null){
            dataHolder = new DataHolder();
        }

        if(cars == null){
            cars = new ArrayList<Car>();
        }


        return dataHolder;
    }

    public void addCar(Car car){
        cars.add(car);
        notifyCarAdded();
    }

    public void addCarListener(CarChangedListener listener) {
        if (carChangedListeners == null) {
            carChangedListeners = new ArrayList<CarChangedListener>();
        }
        carChangedListeners.add(listener);
    }

    public void removeCarListener(CarChangedListener listener) {
        if (carChangedListeners == null) {
            carChangedListeners = new ArrayList<CarChangedListener>();
        }
        carChangedListeners.remove(listener);
    }


    private void notifyCarAdded() {
        if (carChangedListeners == null) {
            carChangedListeners = new ArrayList<CarChangedListener>();
        }

        for (CarChangedListener listener: carChangedListeners) {
            listener.onCarDownloaded();
        }

    }

    public ArrayList<Car>getCars(){
        return cars;
    }
}
