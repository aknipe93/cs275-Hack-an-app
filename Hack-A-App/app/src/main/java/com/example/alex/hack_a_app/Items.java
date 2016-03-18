package com.example.alex.hack_a_app;

/**
 * Created by alex on 3/17/2016.
 */
public class Items {
    public String name;
    private String date;
    private String urlHack;
    private String location;


    public Items(String name, String date, String urlHack, String location) {
        super();
        this.name = name;
        this.date = date;
        this.urlHack = urlHack;
        this.location = location;

    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getUrlHack() {
        return urlHack;
    }
    public String getLocation() {
        return location;
    }
    // getters and setters...
}
