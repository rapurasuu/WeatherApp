package com.example.rapusweatherapp.DTOs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;

public class Daily implements Serializable {
    // Time of the forecasted data, Unix, UTC
    private long dt;
    private boolean hasDt;

    // Sunrise time, Unix, UTC
    private long sunrise;
    private boolean hasSunrise;

    // Sunset time, Unix, UTC
    private long sunset;
    private boolean hasSunset;

    // Combined temperature data (Unit depends on API call)
    private Temperature temp;
    private boolean hasTemp;

    // Combined temperature data. This accounts for the human perception of weather. (Unit depends on API call)
    private Temperature feels_like;
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

    // Wind speed. (Unit depends on API call)
    private double wind_speed;
    private boolean hasWind_speed;

    // Wind direction, degrees (meteorological)
    private int wind_deg;
    private boolean hasWind_deg;

    // Weather info
    private Weather[] weather;
    private boolean hasWeather;

    // Cloudiness, %
    private double clouds;
    private boolean hasClouds;

    // Probability of precipitation
    private double pop;
    private boolean hasPop;

    // Precipitation volume, mm
    private double rain;
    private boolean hasRain;

    // Midday UV index
    private double uvi;
    private boolean hasUvi;

    public Daily (JSONObject jsonObject) throws JSONException {
        if (jsonObject.has("dt")) {
            this.dt = jsonObject.getLong("dt");
            this.hasDt = true;
        }
        if (jsonObject.has("sunrise")){
            this.sunrise = jsonObject.getLong("sunrise");
            this.hasSunrise = true;
        }
        if (jsonObject.has("sunset")){
            this.sunset = jsonObject.getLong("sunset");
            this.hasSunset = true;
        }
        if (jsonObject.has("temp")){
            this.temp = new Temperature(jsonObject.getJSONObject("temp"));
            this.hasTemp = true;
        }
        if (jsonObject.has("feels_like")){
            this.feels_like = new Temperature(jsonObject.getJSONObject("feels_like"));
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
        if (jsonObject.has("wind_speed")){
            this.wind_speed = jsonObject.getDouble("wind_speed");
            this.hasWind_speed = true;
        }
        if (jsonObject.has("wind_deg")){
            this.wind_deg = jsonObject.getInt("wind_deg");
            this.hasWind_deg = true;
        }
        if (jsonObject.has("weather")){
            this.weather = GetWeatherArray(jsonObject.getJSONArray("weather"));
            this.hasWeather = true;
        }
        if (jsonObject.has("clouds")){
            this.clouds = jsonObject.getDouble("clouds");
            this.hasClouds = true;
        }
        if (jsonObject.has("pop")){
            this.pop = jsonObject.getDouble("pop");
            this.hasPop = true;
        }
        if (jsonObject.has("rain")){
            this.rain = jsonObject.getDouble("rain");
            this.hasRain = true;
        }
        if (jsonObject.has("uvi")){
            this.uvi = jsonObject.getDouble("uvi");
            this.hasUvi = true;
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

    public Date getSunrise() {
        return new Date(sunrise);
    }

    public boolean hasSunrise() {
        return hasSunrise;
    }

    public Date getSunset() {
        return new Date(sunset);
    }

    public boolean hasSunset() {
        return hasSunset;
    }

    public Temperature getTemp() {
        return temp;
    }

    public boolean hasTemp() {
        return hasTemp;
    }

    public Temperature getFeels_like() {
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

    public double getClouds() {
        return clouds;
    }

    public boolean hasClouds() {
        return hasClouds;
    }

    public double getPop() {
        return pop;
    }

    public boolean hasPop() {
        return hasPop;
    }

    public double getRain() {
        return rain;
    }

    public boolean hasRain() {
        return hasRain;
    }

    public double getUvi() {
        return uvi;
    }

    public boolean hasUvi() {
        return hasUvi;
    }
}
