package com.example.datalogisticsvmware;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class organizationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization);


        String url = "https://vcloud.datalogistics.lt/api/query?type=orgVdc";
        ListView listView = findViewById(R.id.listView);
        Intent intent = new Intent(organizationActivity.this, vmActivity.class);

        VolleyRequests.getInstance(this);

        VolleyRequests.getInstance().httpGetRequest(url);

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
                    String href = record.getString("href");
                    String[] splitter = href.split("/");
                    String id = splitter[5];
                    String runningVm = record.getString("numberOfRunningVMs");
                    String vms = runningVm + "/" + vm;
                    //Objects
                    Organization org = new Organization(name, id, vms);

                    ArrayList<Organization> orgList = new ArrayList<>();
                    orgList.add(org);

                    organizationListAdapter adapter = new organizationListAdapter(organizationActivity.this, R.layout.adapter_organization_view_layout, orgList);
                    listView.setAdapter(adapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                            Organization tempOrg = orgList.get(i);
                            String id = tempOrg.getId();
                            intent.putExtra("orgid", id);
                            startActivity(intent);
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 1000);
    }
}