package com.example.datalogisticsvmware;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class networkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        TextView textView = findViewById(R.id.networkView);

        String edgeGatewayUrl = "https://vcloud.datalogistics.lt/cloudapi/1.0.0/edgeGateways";

        VolleyRequests.getInstance(this);
        VolleyRequests.getInstance().httpGetJsonRequest(edgeGatewayUrl);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                try {
                    JSONObject reader = new JSONObject(VolleyRequests.getInstance().stringResponse);
                    JSONArray main = reader.getJSONArray("values");
                    for (int i = 0; i < main.length(); i++) {
                        JSONObject object = main.getJSONObject(i);
                        final String id = object.getString("id");
                        String name = object.getString("name");
                        String desc = object.getString("description");
                        textView.append("Name - " + name + "\n");
                        textView.append("Description - " + desc + "\n");
                        JSONArray uplinks = object.getJSONArray("edgeGatewayUplinks");
                        for (int j = 0; j < uplinks.length(); j++) {
                            JSONObject uplink = uplinks.getJSONObject(j);
                            JSONObject subnets = uplink.getJSONObject("subnets");
                            JSONArray values = subnets.getJSONArray("values");
                            for (int k = 0; k < values.length(); k++) {
                                JSONObject value = values.getJSONObject(k);
                                textView.append("Gateway - " + value.getString("gateway") + "\n");
                                JSONObject ipRanges = value.getJSONObject("ipRanges");
                                JSONArray ipValues = ipRanges.getJSONArray("values");
                                for (int l = 0; l < ipValues.length(); l++) {
                                    JSONObject ipValue = ipValues.getJSONObject(l);
                                    textView.append("IP Range : " + ipValue.getString("startAddress") + " - " + ipValue.getString("endAddress") + "\n");

                                }
                                //textView.append("Total Ips used - " + value.getString("totalIpCount" + "\n"));
                                //textView.append("Used Ips used - " + value.getString("usedIpCount"));

                            }
                        }
                        textView.append("Licenses" + "\n");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            }, 1000
        );
    }
}