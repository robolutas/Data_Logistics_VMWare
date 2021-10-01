package com.example.datalogisticsvmware;

public class SharedData {
    private static SharedData instance = new SharedData();

    // Getter-Setters
    public static SharedData getInstance() {
        return instance;
    }

    public static void setInstance(SharedData instance) {
        SharedData.instance = instance;
    }

    private String id;
    private String jwt;


    private SharedData() {

    }


    public String getId() {
        return id;
    }

    public String getJwt() {
        return jwt;
    }

    public void setId(String value1) {
        this.id = value1;
    }

    public void setJwt(String value2) {
        this.jwt = value2;
    }
}
