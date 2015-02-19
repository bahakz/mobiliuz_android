package com.mobiliuz.demo.mobiliuzapp.fragments;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mobiliuz.demo.mobiliuzapp.Car;
import com.mobiliuz.demo.mobiliuzapp.DataHolder;
import com.mobiliuz.demo.mobiliuzapp.MainActivity;
import com.mobiliuz.demo.mobiliuzapp.R;
import com.mobiliuz.demo.mobiliuzapp.RestClient;

import org.apache.http.HttpEntity;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticsFragment extends Fragment {

    private static final String TAG = MainActivity.class.getSimpleName();


    public StatisticsFragment() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        new userCarsCollection().execute();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statistics, container, false);
    }

    private class userCarsCollection extends AsyncTask<String, Void, Void> {

        protected Void doInBackground(String... strings) {

            // RestClient client = new RestClient("http://private-anon-d69e0f196-mobiliuz.apiary-mock.com");  //Write your url here
            RestClient client = new RestClient("http://demo.mobiliuz.com/api/v1/vehicle_status");

            client.addParam("username", "demo@mobiliuz.com"); // Here I am adding key-value parameters
            client.addParam("api_token", "f4b25cbe8debb1a8bf3ad57a793051de1ce4ff82");

            client.addHeader("content-type", "application/json"); // Here I am specifying that the key-value pairs are sent in the JSON format

            try {
                String response = client.executeGet(); // In case your server sends any response back, it will be saved in this response string.
                Log.d(TAG, "second response " + response.toString());

                JSONObject jsonObject = new JSONObject(response);

                JSONArray jarray = jsonObject.getJSONArray("objects");

                //"objects": [{"dt": "2015-02-18T09:34:24", "id": 2531, "imei": "864251020002568", "is_moving": true, "is_online": true, "last_online_info": {"power_voltage": 13002.0, "speed": 32, "ts": 1424252047, "where": [43.252976, 76.918615]}, "resource_uri": "/api/v1/vehicle_status/2531", "vehicle_pk": 37}

                for(int i = 0; i < jarray.length(); i++){

                    JSONObject object = jarray.getJSONObject(i);

                    Car singleCar = new Car();

                    singleCar.setId(object.getInt("id"));
                    singleCar.setPowerVoltage(object.getJSONObject("last_online_info").getDouble("power_voltage"));
                    double latitude = (double) object.getJSONObject("last_online_info").getJSONArray("where").get(0);
                    double longitude = (double) object.getJSONObject("last_online_info").getJSONArray("where").get(1);
                    singleCar.setLatitude(latitude);
                    singleCar.setLongitude(longitude);

                    DataHolder.getDataHolder().addCar(singleCar);

                }

                Log.d(TAG, "cars size " + DataHolder.getDataHolder().getCars().size());
                Log.d(TAG, "json array length " + jarray.length());
                Log.d(TAG, "json array " + jarray.toString());

            } catch (Exception e) {

                e.printStackTrace();
            }

            return null;
        }
    }

}
