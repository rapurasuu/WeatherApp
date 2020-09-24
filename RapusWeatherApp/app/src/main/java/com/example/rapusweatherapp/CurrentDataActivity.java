package com.example.rapusweatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.rapusweatherapp.DTOs.Current;
import com.example.rapusweatherapp.DTOs.Data;

import java.text.SimpleDateFormat;

public class CurrentDataActivity extends AppCompatActivity implements View.OnClickListener {

    private Current current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_data);

        ((Button)findViewById(R.id.btnBack)).setOnClickListener(this);

        // Prüfen ob Daten mit übergeben wurden und wenn ja in field speichern
        if (getIntent() != null && getIntent().getSerializableExtra(Current.class.getName()) != null){
            this.current = (Current) getIntent().getSerializableExtra(Current.class.getName());
            this.refreshUi();
        }
    }

    // Setzen der Werte für die UI Elemente
    private void refreshUi(){
        SimpleDateFormat dateFormat = new SimpleDateFormat(getResources().getString(R.string.date_format));

        if (current.hasDate()){
            ((TextView)findViewById(R.id.txtCurrentDate)).setText(getResources().getString(R.string.date)
                                                                    + " "
                                                                    + dateFormat.format(current.getDate()));
        }
        else {
            ((TextView)findViewById(R.id.txtCurrentDate)).setVisibility(View.GONE);
        }

        if (current.hasSunrise()){
            ((TextView)findViewById(R.id.txtCurrentSunrise)).setText(getResources().getString(R.string.sunrise)
                                                                        + " "
                                                                        + dateFormat.format(current.getSunrise()));
        }
        else {
            ((TextView)findViewById(R.id.txtCurrentSunrise)).setVisibility(View.GONE);
        }

        if (current.hasSunset()){
            ((TextView)findViewById(R.id.txtCurrentSunset)).setText(getResources().getString(R.string.sunset)
                                                                    + " "
                                                                    + dateFormat.format(current.getSunset()));
        }
        else {
            ((TextView)findViewById(R.id.txtCurrentSunset)).setVisibility(View.GONE);
        }

        if (current.hasTemp()){
            ((TextView)findViewById(R.id.txtCurrentTemp)).setText(getResources().getText(R.string.temp)
                                                                    + " "
                                                                    + current.getTemp()
                                                                    + "°C");
        }
        else {
            ((TextView)findViewById(R.id.txtCurrentTemp)).setVisibility(View.GONE);
        }

        if (current.hasFeels_like()){
            ((TextView)findViewById(R.id.txtCurrentFeelsLike)).setText(getResources().getText(R.string.feels_like)
                    + " "
                    + current.getFeels_like()
                    + "°C");
        }
        else {
            ((TextView)findViewById(R.id.txtCurrentFeelsLike)).setVisibility(View.GONE);
        }

        if (current.hasPressure()){
            ((TextView)findViewById(R.id.txtCurrentPressure)).setText(getResources().getText(R.string.atmospheric_pressure)
                    + " "
                    + current.getPressure()
                    + "hPa");
        }
        else {
            ((TextView)findViewById(R.id.txtCurrentPressure)).setVisibility(View.GONE);
        }

        if (current.hasHumidity()){
            ((TextView)findViewById(R.id.txtCurrentHumidity)).setText(getResources().getText(R.string.humidity)
                    + " "
                    + current.getHumidity()
                    + "%");
        }
        else {
            ((TextView)findViewById(R.id.txtCurrentHumidity)).setVisibility(View.GONE);
        }

        if (current.hasDew_point()){
            ((TextView)findViewById(R.id.txtCurrentDewPoint)).setText(getResources().getText(R.string.dew_point)
                    + " "
                    + current.getDew_point()
                    + "°C");
        }
        else {
            ((TextView)findViewById(R.id.txtCurrentDewPoint)).setVisibility(View.GONE);
        }

        if (current.hasUvi()){
            ((TextView)findViewById(R.id.txtCurrentUVI)).setText(getResources().getText(R.string.uvi)
                    + " "
                    + current.getUvi());
        }
        else {
            ((TextView)findViewById(R.id.txtCurrentUVI)).setVisibility(View.GONE);
        }

        if (current.hasClouds()){
            ((TextView)findViewById(R.id.txtCurrentClouds)).setText(getResources().getText(R.string.clouds)
                    + " "
                    + current.getClouds()
                    + "%");
        }
        else {
            ((TextView)findViewById(R.id.txtCurrentClouds)).setVisibility(View.GONE);
        }

        if (current.hasVisibility()){
            ((TextView)findViewById(R.id.txtCurrentVisibility)).setText(getResources().getText(R.string.visibility)
                    + " "
                    + current.getVisibility()
                    + "m");
        }
        else {
            ((TextView)findViewById(R.id.txtCurrentVisibility)).setVisibility(View.GONE);
        }

        if (current.hasWind_speed()){
            ((TextView)findViewById(R.id.txtCurrentWindSpeed)).setText(getResources().getText(R.string.wind_speed)
                    + " "
                    + current.getWind_speed()
                    + "m/s");
        }
        else {
            ((TextView)findViewById(R.id.txtCurrentWindSpeed)).setVisibility(View.GONE);
        }

        if (current.hasWind_deg()){
            ((TextView)findViewById(R.id.txtCurrentWindDeg)).setText(getResources().getText(R.string.wind_deg)
                    + " "
                    + current.getWind_deg()
                    + "°");
        }
        else {
            ((TextView)findViewById(R.id.txtCurrentWindDeg)).setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnBack:{
                // Neue Activity Starten und öffnen
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        }
    }
}
