package com.example.datalogisticsvmware;

public class Organization {
    String name ;
    String id ;
    String vm ;

    public Organization(String name, String id, String vm) {
        this.name = name;
        this.id = id;
        this.vm = vm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVm() {
        return vm;
    }

    public void setVm(String vm) {
        this.vm = vm;
    }
}
