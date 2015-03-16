package com.mobiliuz.demo.mobiliuzapp.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobiliuz.demo.mobiliuzapp.activities.MainActivity;
import com.mobiliuz.demo.mobiliuzapp.R;

public class StatisticsFragment extends Fragment {

    private static final String TAG = MainActivity.class.getSimpleName();

    public StatisticsFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_statistics, container, false);

        return v;
    }


}
