package com.mobiliuz.demo.mobiliuzapp.activities;


import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.mobiliuz.demo.mobiliuzapp.R;
import com.mobiliuz.demo.mobiliuzapp.helpers.PrefsHelper;
import com.mobiliuz.demo.mobiliuzapp.helpers.RestClient;

import org.json.JSONObject;


public class EntranceActivity extends ActionBarActivity{

    private static final String TAG = MainActivity.class.getSimpleName();

    private EditText emailEditText;
    private EditText passwordEditText;
    private ImageButton loginButton;
    private PrefsHelper prefsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);
        getSupportActionBar().hide();

        prefsHelper = new PrefsHelper(this);

        if (prefsHelper.isPrefExists(PrefsHelper.PREF_EMAIL)) {
            enterApp();
        }

        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        loginButton = (ImageButton) findViewById(R.id.loginButton);


        emailEditText.setText("yernar@v3na.kz");
        passwordEditText.setText("123");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginClick();
            }
        });

    }

    private void enterApp() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private class loginTask extends AsyncTask<String, Void, Void> {

        protected Void doInBackground(String... strings) {

            String email = strings[0];
            String password = strings[1];

            RestClient client = new RestClient("http://demo.mobiliuz.com/api/v1/auth/login");
            client.addParam("username", email);
            client.addParam("password", password);
            client.addHeader("content-type", "application/json");

            try {
                String response = client.executePost();
                Log.d(TAG, "response on entrance " + response.toString());

                JSONObject userObject = new JSONObject(response);
                if (userObject.getString("success").equals("true")) {

                    String api_token = userObject.getString("api_token");
                    Log.d(TAG, "api token on entrance " + api_token);

                    prefsHelper.savePref(PrefsHelper.PREF_EMAIL, email);
                    prefsHelper.savePref(PrefsHelper.PREF_PASSWORD, password);
                    prefsHelper.savePref(PrefsHelper.PREF_API_TOKEN, api_token);

                    enterApp();
                } else{
                    EntranceActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(EntranceActivity.this, "Incorrect login or password", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    private void onLoginClick() {

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        email = email.trim();

        new loginTask().execute(email, password);

    }
}
