package com.example.myweatherbase.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweatherbase.API.Connector;
import com.example.myweatherbase.R;
import com.example.myweatherbase.activities.model.Root;
import com.example.myweatherbase.base.BaseActivity;
import com.example.myweatherbase.base.CallInterface;
import com.example.myweatherbase.base.ImageDownloader;
import com.example.myweatherbase.base.Parameters;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CityActivity extends BaseActivity implements CallInterface {
    private TextView textViewCity;
    private Root root;
    private double lat;
    private double lon;
    AdaptadorRecycleView adaptador;
    private RecyclerView recycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        textViewCity = findViewById(R.id.textViewCity);
        recycler = findViewById(R.id.recyclerView);

        Bundle extras = getIntent().getExtras();
        lat = extras.getDouble("city_lat");
        lon = extras.getDouble("city_lon");

        // Mostramos la barra de progreso y ejecutamos la llamada a la API
        showProgress();
        executeCall(this);
    }

    // Realizamos la llamada y recogemos los datos en un objeto Root
    @Override
    public void doInBackground() {
        root = Connector.getConector().get(Root.class,"&lat="+lat+"&lon="+lon);
    }

    // Una vez ya se ha realizado la llamada, ocultamos la barra de progreso y presentamos los datos
    @Override
    public void doInUI() {
        hideProgress();

        textViewCity.setText(root.getCity().getName());

        adaptador = new AdaptadorRecycleView(this);
        recycler.setAdapter(adaptador);

        recycler.setLayoutManager(new LinearLayoutManager(this));
    }
}