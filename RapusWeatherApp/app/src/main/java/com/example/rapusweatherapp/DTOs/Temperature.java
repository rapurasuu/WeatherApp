package com.example.rapusweatherapp.DTOs;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Temperature implements Serializable {
    // Min daily temperature.
    private double min;
    private boolean hasMin;

    // Max daily temperature.
    private double max;
    private boolean hasMax;

    // Day temperature.
    private double day;
    private boolean hasDay;

    // Night temperature.
    private double night;
    private boolean hasNight;

    // Evening temperature.
    private double eve;
    private boolean hasEve;

    // Morning temperature.
    private double morn;
    private boolean hasMorn;

    public Temperature(JSONObject jsonObject) throws JSONException {
        if (jsonObject.has("min")) {
            this.min = jsonObject.getDouble("min");
            this.hasMin =true;
        }
        if (jsonObject.has("max")) {
            this.max = jsonObject.getDouble("max");
            this.hasMax =true;
        }
        if (jsonObject.has("day")) {
            this.day = jsonObject.getDouble("day");
            this.hasDay =true;
        }
        if (jsonObject.has("night")) {
            this.night = jsonObject.getDouble("night");
            this.hasNight =true;
        }
        if (jsonObject.has("eve")) {
            this.eve = jsonObject.getDouble("eve");
            this.hasEve =true;
        }
        if (jsonObject.has("morn")) {
            this.morn = jsonObject.getDouble("morn");
            this.hasMorn =true;
        }
    }

    public double getMin() {
        return min;
    }

    public boolean hasMin() {
        return hasMin;
    }

    public double getMax() {
        return max;
    }

    public boolean hasMax() {
        return hasMax;
    }

    public double getDay() {
        return day;
    }

    public boolean hasDay() {
        return hasDay;
    }

    public double getNight() {
        return night;
    }

    public boolean hasNight() {
        return hasNight;
    }

    public double getEve() {
        return eve;
    }

    public boolean hasEve() {
        return hasEve;
    }

    public double getMorn() {
        return morn;
    }

    public boolean hasMorn() {
        return hasMorn;
    }
}
