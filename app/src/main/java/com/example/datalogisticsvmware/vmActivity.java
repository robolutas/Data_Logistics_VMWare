package com.example.datalogisticsvmware;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class vmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vm);

        VolleyRequests.getInstance(this);

        Intent intent = getIntent();
        String id = intent.getStringExtra("orgid");
        ListView listView = findViewById(R.id.vmView);

        String url = "https://vcloud.datalogistics.lt/api/query?type=vm&filter=vdc==" + id;

        VolleyRequests.getInstance().httpGetRequest(url);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                try {
                    //String output;
                    //Json parse
                    ArrayList<VM> vmList = new ArrayList<>();
                    JSONObject reader = new JSONObject(VolleyRequests.getInstance().stringResponse);
                    JSONArray main = reader.getJSONArray("record");
                    for (int i = 0; i < main.length(); i++)
                    {
                        JSONObject record = main.getJSONObject(i);
                        String name = record.getString("name");
                        String status = record.getString("status");
                        String link = record.getString("href");
                        VM vm = new VM(name, status, link);
                        vmList.add(vm);
                    }
                    vmListAdapter adapter = new vmListAdapter(vmActivity.this, R.layout.adapter_vm_view_layout, vmList);
                    listView.setAdapter(adapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                            VM vm = vmList.get(i);
                            String status = vm.getStatus();
                            if (status.equals("POWERED_ON")) {
                                String url2 = vm.getLink() + "/power/action/powerOff";
                                VolleyRequests.getInstance().httpPostRequest(url2);
                            }
                            else {
                                String url2 = vm.getLink() + "/power/action/powerOn";
                                VolleyRequests.getInstance().httpPostRequest(url2);
                            }
                            adapter.notifyDataSetChanged();
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 1000);
    }
}