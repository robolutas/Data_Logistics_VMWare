package com.example.datalogisticsvmware;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class VolleyRequests {

    private static VolleyRequests instance = null;

    //for Volley API
    public RequestQueue requestQueue;
    public String stringResponse;

    //Accept Headers
    // application/*+json;version=36.0 -
    // application/json;version=36.0 - Get/PostJson
    // application/*+json;multisite=;version=36.0 - Get/PostMulti

    private VolleyRequests(Context context)
    {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        //other stuff if you need
    }

    public static synchronized VolleyRequests getInstance(Context context)
    {
        if (null == instance)
            instance = new VolleyRequests(context);
        return instance;
    }

    //this is so you don't need to pass context each time
    public static synchronized VolleyRequests getInstance()
    {
        if (null == instance)
        {
            throw new IllegalStateException(VolleyRequests.class.getSimpleName() +
                    " is not initialized, call getInstance(...) first");
        }
        return instance;
    }

    public void httpPostRequest(String url) {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("HTTP - org ", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("HTTP - org ", String.valueOf(error));
            }
        }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                String jwt = SharedData.get().getJwt();
                //Bearer jwt token and accept specific version
                String bearer = "Bearer " + jwt;
                params.put("Accept", "application/*+json;version=36.0");
                params.put("Authorization", bearer);
                return params;
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                Log.e("response@@", response.headers.toString());
                return super.parseNetworkResponse(response);
            }
        };
        requestQueue.add(stringRequest);
    }

    public void httpPostJsonRequest(String url) {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("HTTP - org ", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("HTTP - org ", String.valueOf(error));
            }
        }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                String jwt = SharedData.get().getJwt();
                //Bearer jwt token and accept specific version
                String bearer = "Bearer " + jwt;
                params.put("Accept", "application/json;version=36.0");
                params.put("Authorization", bearer);
                return params;
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                Log.e("response@@", response.headers.toString());
                return super.parseNetworkResponse(response);
            }
        };
        requestQueue.add(stringRequest);
    }

    public void httpPostMultiRequest(String url) {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("HTTP - org ", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("HTTP - org ", String.valueOf(error));
            }
        }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                String jwt = SharedData.get().getJwt();
                //Bearer jwt token and accept specific version
                String bearer = "Bearer " + jwt;
                params.put("Accept", "application/*+json;multisite=;version=36.0");
                params.put("Authorization", bearer);
                return params;
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                Log.e("response@@", response.headers.toString());
                return super.parseNetworkResponse(response);
            }
        };
        requestQueue.add(stringRequest);
    }

    public void httpGetRequest(String url) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.d("HTTP - org ", response);
                        stringResponse = response;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("HTTP - org ", String.valueOf(error));
            }
        }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                String jwt = SharedData.get().getJwt();
                //Bearer jwt token and accept specific version
                String bearer = "Bearer " + jwt;
                //params.put("Accept", "application/*+json;version=36.0");
                params.put("Accept", "application/*+json;version=36.0");
                params.put("Authorization", bearer);
                return params;
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                Log.e("response@@", response.headers.toString());
                return super.parseNetworkResponse(response);
            }
        };
        requestQueue.add(stringRequest);
    }

    public void httpGetJsonRequest(String url) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.d("HTTP - org ", response);
                        stringResponse = response;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("HTTP - org ", String.valueOf(error));
            }
        }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                String jwt = SharedData.get().getJwt();
                //Bearer jwt token and accept specific version
                String bearer = "Bearer " + jwt;
                //params.put("Accept", "application/*+json;version=36.0");
                params.put("Accept", "application/json;version=36.0");
                params.put("Authorization", bearer);
                return params;
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                Log.e("response@@", response.headers.toString());
                return super.parseNetworkResponse(response);
            }
        };
        requestQueue.add(stringRequest);
    }

    public void httpGetMultiRequest(String url) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.d("HTTP - org ", response);
                        stringResponse = response;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("HTTP - org ", String.valueOf(error));
            }
        }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                String jwt = SharedData.get().getJwt();
                //Bearer jwt token and accept specific version
                String bearer = "Bearer " + jwt;
                //params.put("Accept", "application/*+json;version=36.0");
                params.put("Accept", "application/*+json;multisite=;version=36.0");
                params.put("Authorization", bearer);
                return params;
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                Log.e("response@@", response.headers.toString());
                return super.parseNetworkResponse(response);
            }
        };
        requestQueue.add(stringRequest);
    }

    public void httpPutRequest(String url) {


        StringRequest stringRequest = new StringRequest(Request.Method.PUT, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("HTTP - org ", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("HTTP - org ", String.valueOf(error));
            }
        }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                String jwt = SharedData.get().getJwt();
                //Bearer jwt token and accept specific version
                String bearer = "Bearer " + jwt;
                params.put("Accept", "application/*+json;version=36.0");
                params.put("Authorization", bearer);
                return params;
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                Log.e("response@@", response.headers.toString());
                return super.parseNetworkResponse(response);
            }
        };
        requestQueue.add(stringRequest);
    }
}
