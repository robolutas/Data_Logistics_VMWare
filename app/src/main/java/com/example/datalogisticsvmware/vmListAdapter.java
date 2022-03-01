package com.example.datalogisticsvmware;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class vmListAdapter extends ArrayAdapter<VM> {

    private Context mContext;
    int mResource;

    public vmListAdapter(Context context, int resource, ArrayList<VM> objects) {
        super(context, resource, objects);
        this.mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = "Vm Name : " + getItem(position).getName();
        String status = "Status : " +getItem(position).getStatus();

        //VM vmObj = new VM(name, status);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvName = (TextView) convertView.findViewById(R.id.vmName);
        TextView tvStatus = (TextView) convertView.findViewById(R.id.vmStatus);

        tvName.setText(name);
        tvStatus.setText(status);

        return convertView;


    }
}
