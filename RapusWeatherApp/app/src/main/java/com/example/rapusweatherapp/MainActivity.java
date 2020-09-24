package com.example.rapusweatherapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.*;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.rapusweatherapp.DTOs.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String[] INITIAL_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION};

    private final String URL = "https://api.openweathermap.org/data/2.5/onecall?lat={lat}&lon={lon}&units={units}&appid={appid}";

    private Date lastUpdateTime;

    private Location location;

    private Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Listener setzen
        ((Button)findViewById(R.id.btnMainUpdateWeatherData)).setOnClickListener(this);
        ((Button)findViewById(R.id.btnMainUpdateLocation)).setOnClickListener(this);
        ((Button)findViewById(R.id.btnShowCurrent)).setOnClickListener(this);
        ((Button)findViewById(R.id.btnShowDaily)).setOnClickListener(this);
        ((Button)findViewById(R.id.btnShowHourly)).setOnClickListener(this);
        ((Button)findViewById(R.id.btnShowMinutely)).setOnClickListener(this);

        // Abfrage nach Permissions
        if (!this.canAccessLocation()){
            requestPermissions(INITIAL_PERMISSIONS, 666);
        }

        // Wenn Ort Aktualisiert werden konnte die Wetterdaten für diesen Ort Abfragen
        if (this.updateLocation()) {
            this.updateWeatherData();
        }

        // Buttons Aktivieren/Deaktivieren
        this.enableWeatherButtons();
    }

    private void enableWeatherButtons(){
        boolean enabled = this.data != null;

        ((Button)findViewById(R.id.btnShowCurrent)).setEnabled(enabled);
        ((Button)findViewById(R.id.btnShowDaily)).setEnabled(enabled);
        ((Button)findViewById(R.id.btnShowHourly)).setEnabled(enabled);
        ((Button)findViewById(R.id.btnShowMinutely)).setEnabled(enabled);
    }

    private boolean updateWeatherData(){
        // Url erstellen
        String url = this.generateUrl();

        // Alte Daten verwerfen
        this.data = null;
        this.enableWeatherButtons();

        // HTTP Request an API
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        receiveResponse(response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });
        queue.add(jsonObjectRequest);
        return true;
    }

    private String generateUrl(){
        String url = this.URL;

        // Wenn ein Ort vorhanden die lon und lat Werte der URL austauschen
        if (this.location != null){
            url = url.replace("{lon}", String.valueOf(this.location.getLongitude()))
                    .replace("{lat}", String.valueOf(this.location.getLatitude()));
        }

        // App ID und Unit setzen
        url = url.replace("{appid}", "6e1fa6bbd9a5fb996730042aa3153268")
                .replace("{units}", "metric");

        return url;
    }

    // Verarbeiten des JSONObject das als Response vom HTTP Request empfangen wird
    private void receiveResponse(JSONObject jsonObject){
        try {
            this.data = new Data(jsonObject);

            this.updateLastUpdateTime();
            this.enableWeatherButtons();
        } catch (JSONException exception){
            exception.printStackTrace();
        }
    }

    // Label mit der "Zuletz Aktualisiert" Zeit Updaten
    private void updateLastUpdateTime(){
        this.lastUpdateTime = Calendar.getInstance().getTime();

        SimpleDateFormat dateFormat = new SimpleDateFormat(getResources().getString(R.string.date_format_main));
        ((TextView)findViewById(R.id.txtMainLastUpdated)).setText(dateFormat.format(this.lastUpdateTime));
    }

    // Mithilfe von GPS oder dem Textblock den Ort ermitteln und setzen
    private boolean updateLocation(){
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        String locationName = getResources().getString(R.string.unkown_location);

        try {
            try {
                this.location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                Geocoder gc = new Geocoder(this, Locale.getDefault());
                List<Address> addresses = null;

                if (!((EditText) findViewById(R.id.edittxtMainLocation)).getText().toString().equals("")) {
                    addresses = gc.getFromLocationName(((EditText) findViewById(R.id.edittxtMainLocation)).getText().toString(), 1);
                    ((EditText) findViewById(R.id.edittxtMainLocation)).setText("");
                } else if (this.location != null) {
                    addresses = gc.getFromLocation(this.location.getLatitude(), this.location.getLongitude(), 1);
                }

                if (addresses != null && addresses.size() > 0) {
                    Address address = addresses.get(0);
                    locationName = address.getLocality();
                    locationName += "\n" + address.getCountryName();
                    locationName += "\nlon: " + address.getLongitude();
                    locationName += "\nlat: " + address.getLatitude();

                    return true;
                }

                return false;
            } catch (SecurityException exception) {
                return false;
            } catch (IOException exception) {
                return false;
            }
        }
        finally {
            ((TextView)findViewById(R.id.txtMainLocation)).setText(locationName);
        }
    }

    // Prüft ob Rechte für GPS gegeben sind
    private boolean canAccessLocation(){
        return this.hasPermissions(Manifest.permission.ACCESS_FINE_LOCATION);
    }

    private boolean hasPermissions(String permission){
        return PackageManager.PERMISSION_GRANTED==checkSelfPermission(permission);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnMainUpdateWeatherData:{
                this.updateWeatherData();
                break;
            }
            case R.id.btnMainUpdateLocation:{
                if (this.updateLocation()){
                    this.updateWeatherData();
                }
                break;
            }
            case R.id.btnShowCurrent:{
                Intent intent = new Intent(this, CurrentDataActivity.class);
                intent.putExtra(Current.class.getName(), this.data.getCurrent());
                startActivity(intent);
                break;
            }
            case R.id.btnShowDaily:{
                Intent intent = new Intent(this, DailyDataActivity.class);
                intent.putExtra(Daily.class.getName(), this.data.getDaily());
                startActivity(intent);
                break;
            }
            case R.id.btnShowHourly:{
                Intent intent = new Intent(this, HourlyDataActivity.class);
                intent.putExtra(Hourly.class.getName(), this.data.hasHourly());
                startActivity(intent);
                break;
            }
            case R.id.btnShowMinutely:{
                Intent intent = new Intent(this, MinutelyDataActivity.class);
                intent.putExtra(Minutely.class.getName(), this.data.getMinutely());
                startActivity(intent);
                break;
            }
        }
    }
}
