package com.example.rapusweatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.rapusweatherapp.DTOs.Daily;
import com.example.rapusweatherapp.DTOs.Data;

public class DailyDataActivity extends AppCompatActivity implements View.OnClickListener {

    private Daily daily;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_data);

        ((Button)findViewById(R.id.btnBack)).setOnClickListener(this);

        if (getIntent() != null && getIntent().getSerializableExtra(Daily.class.getName()) != null){
            this.daily = (Daily) getIntent().getSerializableExtra(Daily.class.getName());
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
