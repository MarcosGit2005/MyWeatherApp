package com.example.myweatherbase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myweatherbase.R;
import com.example.myweatherbase.base.Parameters;
public class ActivityMain extends AppCompatActivity {
    private Button buttonPrevision;
    private Spinner spinnerCities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ThemeSetup.applyPreferenceTheme(getApplicationContext());

        buttonPrevision = findViewById(R.id.buttonPrevision);
        spinnerCities = findViewById(R.id.spinnerCities);

        Parameters.API = GestionPreferencias.getInstance().getAPI(getApplicationContext());
        Parameters.UNITS = GestionPreferencias.getInstance().getUnidades(getApplicationContext());
        Parameters.LANG = GestionPreferencias.getInstance().getLanguage(getApplicationContext());

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.configuracion):
                Intent intentPreferenciasActivity = new Intent(this, PreferenciasActivity.class);
                startActivity(intentPreferenciasActivity);
                return true;
            case (R.id.exit):
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
