package com.example.datalogisticsvmware;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


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
                httpCall(url, basicAuth);
            }
        });
    }

    public void httpCall(String url, String basicAuth) {

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("HTTP", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("HTTP", "Error");
            }
        }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Accept", "application/json;version=36.0");
                params.put("Content-type", "application/json;version=36.0");
                params.put("Authorization", "Basic " + basicAuth);

                return params;
            }
        };

        queue.add(stringRequest);
    }
}