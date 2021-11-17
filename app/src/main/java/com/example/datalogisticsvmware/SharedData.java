package com.example.datalogisticsvmware;

public class SharedData {
    private static SharedData instance = new SharedData();

    private String jwt;

    private SharedData() {

    }

    private static SharedData mSharedData;
    public static SharedData get(){
        if(mSharedData == null){
            mSharedData = new SharedData();
        }
        return mSharedData;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String value2) {
        mSharedData.jwt = value2;
    }
}
