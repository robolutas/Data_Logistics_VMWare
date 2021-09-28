package com.example.datalogisticsvmware;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
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

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String TAG = "MyActivity";
        Button loginButton = findViewById(R.id.loginButton);
        EditText userText = findViewById(R.id.email);
        EditText passwordText = findViewById(R.id.password);
        EditText orgText = findViewById(R.id.organization);
        String url = "https://vcloud.datalogistics.lt/cloudapi/1.0.0/sessions";

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String user = userText.getText().toString();
                String password = passwordText.getText().toString();
                String org = orgText.getText().toString();
                String auth = user + "@" + org + ":" + password;
                byte[] data = auth.getBytes(StandardCharsets.UTF_8);
                String basicAuth = Base64.encodeToString(data, Base64.DEFAULT);
                Log.d(TAG, "auth = " + basicAuth);
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