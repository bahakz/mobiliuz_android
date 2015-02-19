package com.mobiliuz.demo.mobiliuzapp;


import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.support.v4.app.Fragment;


public class MainActivity extends FragmentActivity {

    View statisticsLayout;
    View notificationsLayout;
    View locationLayout;

    View statisticsFragment;
    View notificationsFragment;
    View locationFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statisticsLayout = findViewById(R.id.statisticsLayout);
        notificationsLayout = findViewById(R.id.notificationsLayout);
        locationLayout = findViewById(R.id.locationLayout);


        statisticsFragment = findViewById(R.id.statisticsFragment);
        notificationsFragment = findViewById(R.id.notificationsFragment);
        locationFragment = findViewById(R.id.map);

        statisticsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (statisticsFragment.getVisibility() == View.VISIBLE) {
                    statisticsFragment.setVisibility(View.GONE);
                } else {
                    statisticsFragment.setVisibility(View.VISIBLE);
                }
                notificationsFragment.setVisibility(View.GONE);
//                locationLayout.setVisibility(View.VISIBLE);
//                locationLayout.setBackgroundColor(Color.parseColor("#80000000"));
            }
        });

        notificationsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statisticsFragment.setVisibility(View.GONE);
//                locationLayout.setVisibility(View.VISIBLE);
//                locationLayout.setBackgroundColor(Color.parseColor("#80000000"));

                if (notificationsFragment.getVisibility() == View.VISIBLE) {
                    notificationsFragment.setVisibility(View.GONE);
                } else {
                    notificationsFragment.setVisibility(View.VISIBLE);
                }
            }
        });

        /*locationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationFragment.setVisibility(View.VISIBLE);
                notificationsFragment.setVisibility(View.GONE);
                statisticsFragment.setVisibility(View.GONE);
                locationLayout.setVisibility(View.INVISIBLE);
            }
        });
        */

        setupDefault(); 
    }

    private void setupDefault() {
        locationLayout.setVisibility(View.INVISIBLE);
        statisticsFragment.setVisibility(View.GONE);
        notificationsFragment.setVisibility(View.GONE);
    }

}
