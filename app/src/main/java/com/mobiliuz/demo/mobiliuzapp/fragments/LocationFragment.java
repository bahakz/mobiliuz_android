package com.mobiliuz.demo.mobiliuzapp.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mobiliuz.demo.mobiliuzapp.Car;
import com.mobiliuz.demo.mobiliuzapp.CarChangedListener;
import com.mobiliuz.demo.mobiliuzapp.DataHolder;
import com.mobiliuz.demo.mobiliuzapp.MainActivity;
import com.mobiliuz.demo.mobiliuzapp.R;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocationFragment extends Fragment implements CarChangedListener {

    private static View view;
    private static GoogleMap map;
    private SupportMapFragment fragment;
    MarkerOptions marker;

    private static final String TAG = MainActivity.class.getSimpleName();


    public LocationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DataHolder.getDataHolder().addCarListener(this);

        // Inflate the layout for this fragment
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = inflater.inflate(R.layout.fragment_location, container, false);
        } catch (InflateException e) {
            /* map is already there, just return view as it is */
        }

        FragmentManager fm = getChildFragmentManager();
        fragment = (SupportMapFragment) fm.findFragmentById(R.id.googleMap);
        if (fragment == null) {
            fragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.googleMap, fragment).commit();
        }

        if (map == null) {
            map = fragment.getMap();
            for(int i = 0; i < DataHolder.getDataHolder().getCars().size(); i++) {
                double latitude = DataHolder.getDataHolder().getCars().get(i).getLatitude();
                double longitude = DataHolder.getDataHolder().getCars().get(i).getLongitude();
                LatLng latLng = new LatLng(latitude, longitude);
                marker = new MarkerOptions().position(latLng);

                marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)).title("Id: " + DataHolder.getDataHolder().getCars().get(i).getId()).snippet("Power voltage: " + DataHolder.getDataHolder());
                map.addMarker(marker);

                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(latLng).zoom(13).build();
                map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            }


//          map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//          map.animateCamera(CameraUpdateFactory.zoomTo(15));
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        DataHolder.getDataHolder().removeCarListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        FragmentManager fm = getChildFragmentManager();
//        fragment = (SupportMapFragment) fm.findFragmentById(R.id.googleMap);
//        if (fragment == null) {
//            fragment = SupportMapFragment.newInstance();
//            fm.beginTransaction().replace(R.id.googleMap, fragment).commit();
//        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        if (map == null) {
//            map = fragment.getMap();
//            LatLng latLng = new LatLng(43.236365, 76.910514);
//            map.addMarker(new MarkerOptions().position(latLng));
//            map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//            map.animateCamera(CameraUpdateFactory.zoomTo(15));
//        }
    }

    @Override
    public void onCarDownloaded() {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < DataHolder.getDataHolder().getCars().size(); i++) {

                    double latitude = DataHolder.getDataHolder().getCars().get(i).getLatitude();
                    double longitude = DataHolder.getDataHolder().getCars().get(i).getLongitude();
                    LatLng latLng = new LatLng(latitude, longitude);
                    marker = new MarkerOptions().position(latLng);

                    marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)).title("Id: " + DataHolder.getDataHolder().getCars().get(i).getId()).snippet("Power voltage: " + DataHolder.getDataHolder().getCars().get(i).getPowerVoltage());
                    map.addMarker(marker);

                    CameraPosition cameraPosition = new CameraPosition.Builder()
                            .target(latLng).zoom(13).build();
                    map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


                }
            }
        });


    }
}
