package com.example.datalogisticsvmware;

public class VM {
    String name;
    String status;
    String link;

    public VM(String name, String status, String href) {
        this.name = name;
        this.status = status;
        this.link = href;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
