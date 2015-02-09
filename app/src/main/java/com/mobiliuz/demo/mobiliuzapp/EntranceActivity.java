package com.mobiliuz.demo.mobiliuzapp;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;


public class EntranceActivity extends ActionBarActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String PREFS_NAME = "MyPrefsFile";

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    String api_token;
    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);

        sharedPref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        editor = sharedPref.edit();

        if (sharedPref.contains("username")) {
            Intent intent = new Intent(EntranceActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        loginButton = (Button) findViewById(R.id.loginButton);

        //emailEditText.setText("demo@mobiliuz.com");
        //passwordEditText.setText("demo");

        emailEditText.setText(sharedPref.getString("username", " "));

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginClick(); 
            }
        });

    }

    private class loginTask extends AsyncTask<String, Void, Void> {

        protected Void doInBackground(String... strings) {

            //RestClient client = new RestClient("http://private-anon-d69e0f196-mobiliuz.apiary-mock.com");  //Write your url here

            RestClient client = new RestClient("http://demo.mobiliuz.com/api/v1/auth/login");  //Write your url here

            client.addParam("username", strings[0]); //Here I am adding key-value parameters
            client.addParam("password", strings[1]);

            client.addHeader("content-type", "application/json"); // Here I am specifying that the key-value pairs are sent in the JSON format

            try {
                String response = client.executePost(); // In case your server sends any response back, it will be saved in this response string.
                Log.d(TAG, "first response " + response.toString());

                JSONObject userObject = new JSONObject(response);

                api_token = userObject.getString("api_token");
                Log.d(TAG, "api token " + api_token);

                editor.putString("api_token", api_token);

                if (userObject.getString("success").equals("true")) {
                    Intent intent = new Intent(EntranceActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
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

            email = emailEditText.getText().toString();
            password = passwordEditText.getText().toString();

            editor.putString("username", email);
            editor.apply();
            new loginTask().execute(email, password);

    }


}
