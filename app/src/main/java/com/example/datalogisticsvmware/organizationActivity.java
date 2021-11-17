package com.example.datalogisticsvmware;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class organizationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization);

        TextView parseText = findViewById(R.id.parseText);

        String url2 = "https://vcloud.datalogistics.lt/api/query?type=orgVdc";

        VolleyRequests.getInstance(this);

        VolleyRequests.getInstance().httpGetRequest(url2);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                try {
                    //String output;
                    //Json parse
                    JSONObject reader = new JSONObject(VolleyRequests.getInstance().stringResponse);
                    JSONArray main = reader.getJSONArray("record");
                    JSONObject record = main.getJSONObject(0);
                    String name = record.getString("name");
                    String vm = record.getString("numberOfVMs");
                    String runningVm = record.getString("numberOfRunningVMs");
                    String vms = runningVm + "/" + vm;
                    String info = "Name : " + name + "\n" + "Number of running vm's : " + vms;
                    parseText.setText(info);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 1000);
    }
}