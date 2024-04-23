package com.example.myweatherbase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myweatherbase.R;

public class ActivityMain extends AppCompatActivity {
    private Button buttonPrevision;
    private Spinner spinnerCities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonPrevision = findViewById(R.id.buttonPrevision);
        spinnerCities = findViewById(R.id.spinnerCities);

        buttonPrevision.setOnClickListener(view -> {
            Intent intent = new Intent(this, CityActivity.class);
            Ciudades ciudad = (Ciudades)spinnerCities.getSelectedItem();
            intent.putExtra("city_lat",ciudad.getLat());
            intent.putExtra("city_lon",ciudad.getLon());
            startActivity(intent);
        });

        ArrayAdapter<Ciudades> myAdapter = new ArrayAdapter<>(
                this,android.R.layout.simple_spinner_item,Ciudades.values()
        );
        spinnerCities.setAdapter(myAdapter);
    }
}
