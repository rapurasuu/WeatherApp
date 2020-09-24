package com.example.rapusweatherapp.DTOs;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Weather implements Serializable {
    // Weather condition id
    private int id;
    private boolean hasId;

    // Group of weather parameters (Rain, Snow, Extreme etc.)
    private String main;
    private boolean hasMain;

    // Weather condition within the group
    private String description;
    private boolean hasDescription;

    // Weather icon id.
    private String icon;
    private boolean hasIcon;

    public Weather(JSONObject jsonObject) throws JSONException {
        if (jsonObject.has("id")){
            this.id = jsonObject.getInt("id");
            this.hasId = true;
        }
        if (jsonObject.has("main")){
            this.main = jsonObject.getString("main");
            this.hasMain = true;
        }
        if (jsonObject.has("description")){
            this.description = jsonObject.getString("description");
            this.hasDescription = true;
        }
        if (jsonObject.has("icon")){
            this.icon = jsonObject.getString("icon");
            this.hasIcon = true;
        }
    }

    public int getId() {
        return id;
    }

    public boolean hasId() {
        return hasId;
    }

    public String getMain() {
        return main;
    }

    public boolean hasMain() {
        return hasMain;
    }

    public String getDescription() {
        return description;
    }

    public boolean hasDescription() {
        return hasDescription;
    }

    public String getIcon() {
        return icon;
    }

    public boolean hasIcon() {
        return hasIcon;
    }
}
