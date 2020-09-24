package com.example.rapusweatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.rapusweatherapp.DTOs.Data;
import com.example.rapusweatherapp.DTOs.Hourly;

public class HourlyDataActivity extends AppCompatActivity implements View.OnClickListener {

    private Hourly hourly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_data);

        ((Button)findViewById(R.id.btnBack)).setOnClickListener(this);

        if (getIntent() != null && getIntent().getSerializableExtra(Hourly.class.getName()) != null){
            this.hourly = (Hourly) getIntent().getSerializableExtra(Hourly.class.getName());
            this.refreshUi();
        }
    }

    private void refreshUi(){

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnBack:{
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        }
    }
}
