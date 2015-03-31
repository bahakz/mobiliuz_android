package com.mobiliuz.demo.mobiliuzapp.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.mobiliuz.demo.mobiliuzapp.R;
import com.mobiliuz.demo.mobiliuzapp.data.DataHolder;
import com.mobiliuz.demo.mobiliuzapp.fragments.LocationFragment;
import com.mobiliuz.demo.mobiliuzapp.helpers.PrefsHelper;

public class MainActivity extends ActionBarActivity{

    private static final String TAG = MainActivity.class.getSimpleName();

    private PrefsHelper prefsHelper;

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

        prefsHelper = new PrefsHelper(this);

        setupActionBar(); 

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

        setupDefaultLayout();

        DataHolder.getDataHolder(this).downloadCars();

        Log.d(TAG, "i am in onCreate()");
    }

    private void setupActionBar() {
        getSupportActionBar().setCustomView(R.layout.actionbar_custom_view);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0A92E3")));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_logout:
                logOutClick();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logOutClick() {
        Toast.makeText(getApplicationContext(), "log out clicked", Toast.LENGTH_SHORT).show();

        prefsHelper.delete(PrefsHelper.PREF_EMAIL);
        prefsHelper.delete(PrefsHelper.PREF_PASSWORD);
        prefsHelper.delete(PrefsHelper.PREF_API_TOKEN);

        DataHolder.getDataHolder(this).removeAllCars();

        Intent intent = new Intent(this, EntranceActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        finish();
    }

    private void setupDefaultLayout() {
        locationLayout.setVisibility(View.INVISIBLE);
        statisticsFragment.setVisibility(View.GONE);
        notificationsFragment.setVisibility(View.GONE);
    }
}
