package com.example.datalogisticsvmware;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VolleyRequests.getInstance(this);

        Button loginButton = findViewById(R.id.loginButton);
        EditText userText = findViewById(R.id.username);
        EditText passwordText = findViewById(R.id.password);
        EditText orgText = findViewById(R.id.organization);

        Intent intent = new Intent(this, menuActivity.class);

        String url = "https://vcloud.datalogistics.lt/cloudapi/1.0.0/sessions";

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Get data from text fields
                String user = userText.getText().toString();
                String password = passwordText.getText().toString();
                String org = orgText.getText().toString();
                //Turn data into base64
                String auth = user + "@" + org + ":" + password;
                byte[] data = auth.getBytes(StandardCharsets.UTF_8);
                String basicAuth = Base64.encodeToString(data, Base64.DEFAULT);
                //Get HTTP response
                loginCall(url, basicAuth);

                //Delayed run for singleton to acquire variable
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        try {
                            //VolleyRequests.getInstance().httpGetRequest(url2);
                            startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 1000);
            }
        });
    }

    public void loginCall(String url, String basicAuth) {

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.d("HTTP", response);
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

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                Map<String, String> responseHeaders = response.headers;
                //Parse jwt
                String jwt = responseHeaders.get("X-VMWARE-VCLOUD-ACCESS-TOKEN");
                SharedData.get().setJwt(jwt);

                return super.parseNetworkResponse(response);
            }
        };
        queue.add(stringRequest);
    }
}