package com.mobiliuz.demo.mobiliuzapp.fragments;


import android.os.Bundle;
import android.provider.ContactsContract;
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
import com.mobiliuz.demo.mobiliuzapp.listeners.CarChangedListener;
import com.mobiliuz.demo.mobiliuzapp.data.DataHolder;
import com.mobiliuz.demo.mobiliuzapp.activities.MainActivity;
import com.mobiliuz.demo.mobiliuzapp.R;
import com.mobiliuz.demo.mobiliuzapp.model.Car;


public class LocationFragment extends Fragment implements CarChangedListener {

    private View view;
    private GoogleMap map;
    private SupportMapFragment fragment;
    MarkerOptions marker;
    private DataHolder dataHolder;

    private static final String TAG = MainActivity.class.getSimpleName();


    public LocationFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        dataHolder = DataHolder.getDataHolder(getActivity());
        dataHolder.addCarListener(this);

        Log.d(TAG, "is it before or then");

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
            setupCars();
            Log.d(TAG, "I am in map");
        }

        return view;
    }

    private String statusHelper(boolean bool) {
        if (bool) return "YES";
        else return "NO";
    }

    private void setupCars() {
        Log.d(TAG, "I am in setup cars");
        for (int i = 0; i < dataHolder.getCars().size(); i++) {
            double latitude = dataHolder.getCars().get(i).getLastStatus().getLatitude();
            double longitude = dataHolder.getCars().get(i).getLastStatus().getLongtitude();
            LatLng latLng = new LatLng(latitude, longitude);
            marker = new MarkerOptions().position(latLng);

            Car car = dataHolder.getCars().get(i);

            String title = car.getMake() + " " + car.getModel();
            String snippet = "Power voltage: " + car.getLastStatus().getPowerVoltage() + "\n Moving: " + statusHelper(car.getLastStatus().isMoving()) + "\n Online:" + statusHelper(car.getLastStatus().isOnline());

            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)).title(title).snippet(snippet);
            map.addMarker(marker);

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(latLng).zoom(13).build();
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        dataHolder.removeCarListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onCarDownloaded() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setupCars();
            }
        });
    }
}
