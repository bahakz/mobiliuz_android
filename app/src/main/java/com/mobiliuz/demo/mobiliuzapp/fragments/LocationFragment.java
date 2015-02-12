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
import com.mobiliuz.demo.mobiliuzapp.CarChangedListener;
import com.mobiliuz.demo.mobiliuzapp.DataHolder;
import com.mobiliuz.demo.mobiliuzapp.MainActivity;
import com.mobiliuz.demo.mobiliuzapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocationFragment extends Fragment implements CarChangedListener {

    private static View view;
    private static GoogleMap map;
    private SupportMapFragment fragment;
    SharedPreferences settings;
    public static final String PREFS_NAME = "MyPrefsFile";
    MarkerOptions marker;

    private static final String TAG = MainActivity.class.getSimpleName();


    public LocationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DataHolder.getDataHolder().addCarListener(this);

        settings = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

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

            LatLng latLng = new LatLng(43.236365, 76.910514);
            marker = new MarkerOptions().position(latLng);

            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.car_icon));
            map.addMarker(marker);

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(latLng).zoom(13).build();
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

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

        Log.d(TAG, "i am in onCarDownloaded");

    }
}
