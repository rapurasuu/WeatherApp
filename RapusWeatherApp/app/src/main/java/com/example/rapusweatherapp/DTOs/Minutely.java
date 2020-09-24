package com.example.rapusweatherapp.DTOs;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;

public class Minutely implements Serializable {
    // Time of the forecasted data, unix, UTC
    private long dt;
    private boolean hasDt;

    // Precipitation volume, mm
    private int precipitation;
    private boolean hasPrecipitation;

    public Minutely (JSONObject jsonObject) throws JSONException {
        if (jsonObject.has("dt")) {
            this.dt = jsonObject.getLong("dt");
            this.hasDt = true;
        }
        if (jsonObject.has("precipitation")) {
            this.precipitation = jsonObject.getInt("precipitation");
            this.hasPrecipitation = true;
        }
    }

    public Date getDate() {
        return new Date(dt);
    }

    public boolean hasDt() {
        return hasDt;
    }

    public int getPrecipitation() {
        return precipitation;
    }

    public boolean hasPrecipitation() {
        return hasPrecipitation;
    }
}
