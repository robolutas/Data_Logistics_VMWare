package com.example.datalogisticsvmware;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class organizationListAdapter extends ArrayAdapter<Organization> {

    private Context mContext;
    int mResource;

    public organizationListAdapter(Context context, int resource, ArrayList<Organization> objects) {
        super(context, resource, objects);
        this.mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = "VDC Name : " + getItem(position).getName();
        String id = getItem(position).getId();
        String vm = "Running Vm's : " +getItem(position).getVm();

        Organization org = new Organization(name, id, vm);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvName = (TextView) convertView.findViewById(R.id.orgName);
        TextView tvVm = (TextView) convertView.findViewById(R.id.vmAmount);
        TextView tvID = (TextView) convertView.findViewById(R.id.vdcID);

        tvName.setText(name);
        tvVm.setText(vm);
        tvID.setText(id);

        return convertView;


    }
}
