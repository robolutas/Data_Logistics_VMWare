package com.example.datalogisticsvmware;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class menuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button orgButton = findViewById(R.id.vmButton);
        Button networkButton = findViewById(R.id.networkButton);
        Intent orgIntent = new Intent(menuActivity.this, organizationActivity.class);
        Intent networkIntent = new Intent(menuActivity.this, networkActivity.class);

        orgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(orgIntent);
            }
        });
        networkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(networkIntent);
            }
        });
    }
}