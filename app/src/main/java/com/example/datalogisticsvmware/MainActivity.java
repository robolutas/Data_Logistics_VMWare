package com.example.datalogisticsvmware;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String TAG = "MyActivity";
        Button loginButton = findViewById(R.id.loginButton);
        EditText emailText = findViewById(R.id.email);
        EditText passwordText = findViewById(R.id.password);

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();
                Log.d(TAG, "email is = " + email);
                Log.d(TAG, "password is = " + password);
            }
        });
    }

    public void httpCall(String url, String email, String password) {

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // enjoy your response
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // enjoy your error status
            }
        });

        queue.add(stringRequest);
    }
}