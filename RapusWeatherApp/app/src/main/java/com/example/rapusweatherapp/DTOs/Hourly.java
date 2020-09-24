package com.example.rapusweatherapp.DTOs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;

public class Hourly implements Serializable {
    // Time of the forecasted data, Unix, UTC
    private long dt;
    private boolean hasDt;

    // Temperature. (Unit depends on API call)
    private double temp;
    private boolean hasTemp;

    // Temperature. This accounts for the human perception of weather. (Unit depends on API call)
    private double feels_like;
    private boolean hasFeels_like;

    // Atmospheric pressure on the sea level, hPa
    private int pressure;
    private boolean hasPressure;

    // Humidity, %
    private double humidity;
    private boolean hasHumidity;

    // Atmospheric temperature (varying according to pressure and humidity) below which water droplets begin to condense and dew can form. (Unit depends on API call)
    private double dew_point;
    private boolean hasDew_point;

    // Cloudiness, %
    private double clouds;
    private boolean hasClouds;

    // Average visibility, metres
    private int visibility;
    private boolean hasVisibility;

    // Wind speed. (Unit depends on API call)
    private double wind_speed;
    private boolean hasWind_speed;

    // Wind direction, degrees (meteorological)
    private int wind_deg;
    private boolean hasWind_deg;

    // Weather info
    private Weather[] weather;
    private boolean hasWeather;

    // Probability of precipitation
    private double pop;
    private boolean hasPop;

    public Hourly(JSONObject jsonObject) throws JSONException {
        if (jsonObject.has("dt")){
            this.dt = jsonObject.getLong("dt");
            this.hasDt = true;
        }
        if (jsonObject.has("temp")){
            this.temp = jsonObject.getDouble("temp");
            this.hasTemp = true;
        }
        if (jsonObject.has("feels_like")){
            this.feels_like = jsonObject.getDouble("feels_like");
            this.hasFeels_like = true;
        }
        if (jsonObject.has("pressure")){
            this.pressure = jsonObject.getInt("pressure");
            this.hasPressure = true;
        }
        if (jsonObject.has("humidity")){
            this.humidity = jsonObject.getDouble("humidity");
            this.hasHumidity = true;
        }
        if (jsonObject.has("dew_point")){
            this.dew_point = jsonObject.getDouble("dew_point");
            this.hasDew_point = true;
        }
        if (jsonObject.has("clouds")){
            this.clouds = jsonObject.getDouble("clouds");
            this.hasClouds = true;
        }
        if (jsonObject.has("visibility")){
            this.visibility = jsonObject.getInt("visibility");
            this.hasVisibility = true;
        }
        if (jsonObject.has("wind_speed")){
            this.wind_speed = jsonObject.getDouble("wind_speed");
            this.hasWind_speed = true;
        }
        if (jsonObject.has("wind_deg")){
            this.wind_deg = jsonObject.getInt("wind_deg");
            this.hasWind_deg = true;
        }
        if (jsonObject.has("weather")){
            this.weather = this.GetWeatherArray(jsonObject.getJSONArray("weather"));
            this.hasWeather = true;
        }
        if (jsonObject.has("pop")) {
            this.pop = jsonObject.getDouble("pop");
            this.hasPop = true;
        }
    }

    private Weather[] GetWeatherArray(JSONArray jsonArray) throws JSONException {
        Weather[] weathers = new Weather[jsonArray.length()];

        for (int idx = 0; idx < weathers.length; idx++){
            weathers[idx] = new Weather(jsonArray.getJSONObject(idx));
        }

        return weathers;
    }

    public Date getDate() {
        return new Date(dt);
    }

    public boolean hasDt() {
        return hasDt;
    }

    public double getTemp() {
        return temp;
    }

    public boolean hasTemp() {
        return hasTemp;
    }

    public double getFeels_like() {
        return feels_like;
    }

    public boolean hasFeels_like() {
        return hasFeels_like;
    }

    public int getPressure() {
        return pressure;
    }

    public boolean hasPressure() {
        return hasPressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public boolean hasHumidity() {
        return hasHumidity;
    }

    public double getDew_point() {
        return dew_point;
    }

    public boolean hasDew_point() {
        return hasDew_point;
    }

    public double getClouds() {
        return clouds;
    }

    public boolean hasClouds() {
        return hasClouds;
    }

    public int getVisibility() {
        return visibility;
    }

    public boolean hasVisibility() {
        return hasVisibility;
    }

    public double getWind_speed() {
        return wind_speed;
    }

    public boolean hasWind_speed() {
        return hasWind_speed;
    }

    public int getWind_deg() {
        return wind_deg;
    }

    public boolean hasWind_deg() {
        return hasWind_deg;
    }

    public Weather getWeather() {
        return weather[0];
    }

    public boolean hasWeather() {
        return hasWeather && weather.length > 0;
    }

    public double getPop() {
        return pop;
    }

    public boolean hasPop() {
        return hasPop;
    }
}
