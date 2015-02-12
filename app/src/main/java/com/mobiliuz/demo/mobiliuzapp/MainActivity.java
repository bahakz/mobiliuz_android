package com.mobiliuz.demo.mobiliuzapp;


import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.support.v4.app.Fragment;


public class MainActivity extends FragmentActivity {

    View statisticsLayout;
    View notificationsLayout;

    View statisticsFragment;
    View notificationsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statisticsLayout = findViewById(R.id.statisticsLayout);
        notificationsLayout = findViewById(R.id.notificationsLayout);

        statisticsFragment = findViewById(R.id.statisticsFragment);
        notificationsFragment = findViewById(R.id.notificationsFragment);


        statisticsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (statisticsFragment.getVisibility() == View.VISIBLE) {
                    statisticsFragment.setVisibility(View.GONE);
                } else {
                    statisticsFragment.setVisibility(View.VISIBLE);
                }
                notificationsFragment.setVisibility(View.GONE);
            }
        });

        notificationsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statisticsFragment.setVisibility(View.GONE);
                if (notificationsFragment.getVisibility() == View.VISIBLE) {
                    notificationsFragment.setVisibility(View.GONE);
                } else {
                    notificationsFragment.setVisibility(View.VISIBLE);
                }
            }
        });


        setupDefault(); 
    }

    private void setupDefault() {
        statisticsFragment.setVisibility(View.GONE);
        notificationsFragment.setVisibility(View.GONE);
    }

}
