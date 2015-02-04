package com.mobiliuz.demo.mobiliuzapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class EntranceActivity extends ActionBarActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);

        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginClick(); 
            }
        });

    }

    private class loginTask extends AsyncTask<String, Void, Void> {

        protected Void doInBackground(String... strings) {

//            RestClient client = new RestClient("http://private-anon-d69e0f196-mobiliuz.apiary-mock.com");  //Write your url here
            RestClient client = new RestClient("http://demo.mobiliuz.com/api/v1/auth/login");  //Write your url here

            client.addParam("username", "vasya.pupkin@yandex.ru"); //Here I am adding key-value parameters
            client.addParam("password", "1234567");

            client.addHeader("content-type", "application/json"); // Here I am specifying that the key-value pairs are sent in the JSON format

            try {
                String response = client.executePost(); // In case your server sends any response back, it will be saved in this response string.
                Log.d(TAG, "responsde " + response.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    private void onLoginClick() {

            new loginTask().execute();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
    }


}
