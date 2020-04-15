package com.example.android.quakereport;

public class entry_placeholder {
    private String index,place,date,web;
    entry_placeholder(String index,String place,String date,String web){
        this.place = place;
        this.date = date;
        this.index = index;
        this.web = web;
    }

    public String getWeb() {
        return web;
    }

    public String getDate() {
        return date;
    }

    public String getIndex() {
        return index;
    }

    public String getPlace() {
        return place;
    }
}
