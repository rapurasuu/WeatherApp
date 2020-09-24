package com.example.rapusweatherapp.DTOs;

import android.content.Context;
import com.example.rapusweatherapp.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Data implements Serializable {
    // Geographical coordinates of the location (latitude)
    private double lat;
    private boolean hasLat;

    // Geographical coordinates of the location (longitude)
    private double lon;
    private boolean hasLon;

    // Timezone name for the requested location
    private String timezone;
    private boolean hasTimezone;

    // Shift in seconds from UTC
    private long timezone_offset;
    private boolean hasTimezone_offset;

    // The current weather data
    private Current current;
    private boolean hasCurrent;

    // The minuetly weather data (Contains weather data for the next 61 minutes)
    private Minutely[] minutely;
    private boolean hasMinutely;

    // The hourly weather data (Contains weather data from the current hour up to 48h in the future)
    private Hourly[] hourly;
    private boolean hasHourly;

    // The daily weather data (Contains weather data from the current day up to 7 days in the future)
    private Daily[] daily;
    private boolean hasDaily;

    public Data(JSONObject jsonObject) throws JSONException {
        if (jsonObject.has("lat")) {
            this.lat = jsonObject.getDouble("lat");
            this.hasLat = true;
        }
        if (jsonObject.has("lon")) {
            this.lon = jsonObject.getDouble("lon");
            this.hasLon = true;
        }
        if (jsonObject.has("timezone")) {
            this.timezone = jsonObject.getString("timezone");
            this.hasTimezone = true;
        }
        if (jsonObject.has("timezone_offset")) {
            this.timezone_offset = jsonObject.getLong("timezone_offset");
            this.hasTimezone_offset = true;
        }
        if (jsonObject.has("current")) {
            this.current = new Current(jsonObject.getJSONObject("current"));
            this.hasCurrent = true;
        }
        if (jsonObject.has("minutely")) {
            this.minutely = this.GetMinutelyArray(jsonObject.getJSONArray("minutely"));
            this.hasMinutely = true;
        }
        if (jsonObject.has("hourly")) {
            this.hourly = this.GetHourlyArray(jsonObject.getJSONArray("hourly"));
            this.hasHourly = true;
        }
        if (jsonObject.has("daily")) {
            this.daily = this.GetDailyArray(jsonObject.getJSONArray("daily"));
            this.hasDaily = true;
        }
    }

    private Minutely[] GetMinutelyArray(JSONArray jsonArray) throws JSONException {
        Minutely[] minutelies = new Minutely[jsonArray.length()];

        for (int idx = 0; idx < minutelies.length; idx++){
            minutelies[idx] = new Minutely(jsonArray.getJSONObject(idx));
        }

        return minutelies;
    }

    private Hourly[] GetHourlyArray(JSONArray jsonArray) throws JSONException {
        Hourly[] hourlies = new Hourly[jsonArray.length()];

        for (int idx = 0; idx < hourlies.length; idx++){
            hourlies[idx] = new Hourly(jsonArray.getJSONObject(idx));
        }

        return hourlies;
    }

    private Daily[] GetDailyArray(JSONArray jsonArray) throws JSONException {
        Daily[] dailies = new Daily[jsonArray.length()];

        for (int idx = 0; idx < dailies.length; idx++){
            dailies[idx] = new Daily(jsonArray.getJSONObject(idx));
        }

        return dailies;
    }

    public double getLat() {
        return lat;
    }

    public boolean hasLat() {
        return hasLat;
    }

    public double getLon() {
        return lon;
    }

    public boolean hasLon() {
        return hasLon;
    }

    public String getTimezone() {
        return timezone;
    }

    public boolean hasTimezone() {
        return hasTimezone;
    }

    public long getTimezone_offset() {
        return timezone_offset;
    }

    public boolean hasTimezone_offset() {
        return hasTimezone_offset;
    }

    public Current getCurrent() {
        return current;
    }

    public boolean hasCurrent() {
        return hasCurrent;
    }

    public Minutely[] getMinutely() {
        return minutely;
    }

    public boolean hasMinutely() {
        return hasMinutely;
    }

    public Hourly[] getHourly() {
        return hourly;
    }

    public boolean hasHourly() {
        return hasHourly;
    }

    public Daily[] getDaily() {
        return daily;
    }

    public boolean hasDaily() {
        return hasDaily;
    }
}
