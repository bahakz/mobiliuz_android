package com.mobiliuz.demo.mobiliuzapp.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobiliuz.demo.mobiliuzapp.activities.MainActivity;
import com.mobiliuz.demo.mobiliuzapp.R;
import com.mobiliuz.demo.mobiliuzapp.data.DataHolder;
import com.mobiliuz.demo.mobiliuzapp.listeners.CarChangedListener;

public class StatisticsFragment extends Fragment implements CarChangedListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView engineHealthTextView;
    private TextView batteryLevelTextView;
    private DataHolder dataHolder;

    public StatisticsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_statistics, container, false);

        engineHealthTextView = (TextView) v.findViewById(R.id.engineHealthStatusTextView);
        batteryLevelTextView = (TextView) v.findViewById(R.id.batteryLevelTextView);

        dataHolder = DataHolder.getDataHolder(getActivity());
        dataHolder.addCarListener(this);

        setupViews();

        return v;
    }

    @Override
    public void onDestroyView() {
        dataHolder.removeCarListener(this);
        super.onDestroyView();
    }

    private void setupViews() {
        Log.d(TAG, "I am in setup cars");
        for (int i = 0; i < DataHolder.getDataHolder(getActivity()).getCars().size(); i++) {
            double powerVoltage = dataHolder.getCars().get(i).getLastStatus().getPowerVoltage();
            if (powerVoltage > 12.00) {
                engineHealthTextView.setText("ON");
            } else {
                engineHealthTextView.setText("OFF");
            }
        }


    }

    @Override
    public void onCarDownloaded() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setupViews();
            }
        });
    }

}
