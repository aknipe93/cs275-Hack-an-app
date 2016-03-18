package com.example.alex.hack_a_app;

/**
 * Created by alex on 3/17/2016.
 */
public class favDB {
    private int id;
    public String name;
    public String urlHack;
    public String location;
    public String date;

    public favDB()
    {
    }
    public favDB(String name, String date, String urlHack, String location) {
        this.id=id;
        this.name = name;
        this.date = date;
        this.urlHack = urlHack;
        this.location = location;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDate(String date) { this.date = date; }
    public void setUrlHack(String urlHack) {
        this.urlHack = urlHack;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public int getId() {
        return id;
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

}
