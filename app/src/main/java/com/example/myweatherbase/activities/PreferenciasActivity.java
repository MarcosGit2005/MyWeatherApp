package com.example.myweatherbase.activities;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myweatherbase.base.Parameters;

public class PreferenciasActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new PreferenciasFragment())
                .commit();
    }

    @Override
    public void finish() {
        Parameters.API = GestionPreferencias.getInstance().getAPI(getApplicationContext());
        Parameters.UNITS = GestionPreferencias.getInstance().getUnidades(getApplicationContext());
        Parameters.LANG = GestionPreferencias.getInstance().getLanguage(getApplicationContext());
        super.finish();
    }
}