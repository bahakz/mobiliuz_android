package com.mobiliuz.demo.mobiliuzapp.data;

import android.content.Context;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.util.Log;

import com.mobiliuz.demo.mobiliuzapp.helpers.PrefsHelper;
import com.mobiliuz.demo.mobiliuzapp.helpers.RestClient;
import com.mobiliuz.demo.mobiliuzapp.listeners.CarChangedListener;
import com.mobiliuz.demo.mobiliuzapp.model.Car;
import com.mobiliuz.demo.mobiliuzapp.model.CarStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataHolder {

    private static final String TAG = "DataHolder";
    private static ArrayList<Car> cars;
    private static DataHolder dataHolder;
    ArrayList<CarChangedListener> carChangedListeners;

    private static Context ctx;

    public static DataHolder getDataHolder(Context context){
        if(dataHolder == null){
            dataHolder = new DataHolder();
        }

        if(cars == null){
            cars = new ArrayList<Car>();
        }

        ctx = context;

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

    public String getUsername()
    {
        PrefsHelper helper = new PrefsHelper(ctx);
        return helper.getPref(PrefsHelper.PREF_EMAIL);
    }

    public String getApiToken()
    {
        PrefsHelper helper = new PrefsHelper(ctx);
        return helper.getPref(PrefsHelper.PREF_API_TOKEN);
    }


    public void downloadCars()
    {
        Log.d(TAG, "started downloading cars");
        new downloadListOfCarsTask().execute();
    }



    private class downloadListOfCarsTask extends AsyncTask<String, Void, Void> {

        protected Void doInBackground(String... strings) {

            String url = "http://demo.mobiliuz.com/api/v1/car" + "?username=" + getUsername() + "&api_token=" + getApiToken();
            RestClient client = new RestClient(url);
            client.addHeader("content-type", "application/json");
            try {
                String response = client.executeGet();
                Log.d(TAG, "response on getting list of cars" + response);
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jarray = jsonObject.getJSONArray("objects");

                for(int i = 0; i < jarray.length(); i++){

                    JSONObject object = jarray.getJSONObject(i);

                    Car singleCar = new Car();

                    singleCar.setBackendID(object.getInt("id"));
                    singleCar.setMake(object.getString("make"));
                    singleCar.setModel(object.getString("model"));

                    downloadCarStatus(singleCar);

//                    singleCar.setPowerVoltage(object.getJSONObject("last_online_info").getDouble("power_voltage"));
//                    double latitude = (double) object.getJSONObject("last_online_info").getJSONArray("where").get(0);
//                    double longitude = (double) object.getJSONObject("last_online_info").getJSONArray("where").get(1);
//                    singleCar.setLatitude(latitude);
//                    singleCar.setLongitude(longitude);

//                    DataHolder.getDataHolder().addCar(singleCar);

                }

//                Log.d(TAG, "cars size " + DataHolder.getDataHolder().getCars().size());
                Log.d(TAG, "json array length " + jarray.length());
                Log.d(TAG, "json array " + jarray.toString());

            } catch (Exception e) {

                e.printStackTrace();
            }

            return null;
        }
    }

    private void downloadCarStatus(Car singleCar) {

        String url = "http://demo.mobiliuz.com/api/v1/vehicle_status/" + "?vehicle_pk=" + singleCar.getBackendID() + "&username=" + getUsername() + "&api_token=" + getApiToken();
//        String url = "http://demo.mobiliuz.com/api/v1/vehicle_status/" + "?username=" + getUsername() + "&api_token=" + getApiToken();

        Log.d(TAG, "url for car status is " + url);

        RestClient client = new RestClient(url);
        client.addHeader("content-type", "application/json");

        String response = client.executeGet();
        Log.d(TAG, "response for car status with id " + singleCar.getBackendID() + " is " + response);

        try {
            JSONObject jsonObject = new JSONObject(response);
            //{"meta": {"limit": 20, "next": null, "offset": 0, "previous": null, "total_count": 11}, "objects": [{"dt": "2015-02-12T08:41:55", "id": 727, "imei": "356308044445101", "is_moving": true, "is_online": false, "last_online_info": {"power_voltage": 0.0, "speed": 0, "ts": 1423730168, "where"
            JSONArray jarray = jsonObject.getJSONArray("objects");

            if (jarray.length() == 0) return;

            JSONObject object = jarray.getJSONObject(0);

            CarStatus carStatus = new CarStatus();

            carStatus.setPowerVoltage(object.getJSONObject("last_online_info").getDouble("power_voltage"));
            carStatus.setMoving(object.getBoolean("is_moving"));
            carStatus.setOnline(object.getBoolean("is_online"));
            double latitude = (double) object.getJSONObject("last_online_info").getJSONArray("where").get(0);
            double longitude = (double) object.getJSONObject("last_online_info").getJSONArray("where").get(1);
            carStatus.setLatitude(latitude);
            carStatus.setLongtitude(longitude);
            singleCar.setLastStatus(carStatus);
            addCar(singleCar);
            Log.d(TAG, "car longtitude is " + singleCar.getLastStatus().getLongtitude());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Car>getCars(){
        return cars;
    }



}
