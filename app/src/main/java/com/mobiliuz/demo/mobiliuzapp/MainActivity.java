package com.mobiliuz.demo.mobiliuzapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.mobiliuz.demo.mobiliuzapp.adapters.ViewPagerAdapter;
import com.mobiliuz.demo.mobiliuzapp.fragments.ContactFragment;
import com.mobiliuz.demo.mobiliuzapp.fragments.LocationFragment;
import com.mobiliuz.demo.mobiliuzapp.fragments.SettingsFragment;
import com.mobiliuz.demo.mobiliuzapp.fragments.StatisticsFragment;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), getFragments());
        viewPager.setAdapter(viewPagerAdapter);

    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new LocationFragment());
        fragments.add(new StatisticsFragment());
        fragments.add(new ContactFragment());
        fragments.add(new SettingsFragment());
        return fragments;
    }

}
