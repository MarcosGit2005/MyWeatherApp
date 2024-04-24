package com.example.myweatherbase.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myweatherbase.R;
import com.example.myweatherbase.activities.model.List;
import com.example.myweatherbase.base.ImageDownloader;
import com.example.myweatherbase.base.Parameters;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ListActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView diaSemana, fecha, hora, desc, temp, tempMax, tempMin;
    private Button buttonExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        imageView = findViewById(R.id.imageView);
        desc = findViewById(R.id.textViewDesc);
        diaSemana = findViewById(R.id.textViewDayOfWeek);
        fecha = findViewById(R.id.textViewDate);
        hora = findViewById(R.id.textViewHour);
        temp = findViewById(R.id.textViewTemperature);
        tempMax = findViewById(R.id.textViewMaxTemperature);
        tempMin = findViewById(R.id.textViewMinTemperature);

        buttonExit = findViewById(R.id.buttonExit);

        Bundle extras = getIntent().getExtras();
        List list = (List)extras.getSerializable("list");

        ImageDownloader.downloadImage(Parameters.ICON_URL_PRE + list.weather.get(0).icon + Parameters.ICON_URL_POST, imageView);
        desc.setText(list.weather.get(0).description);

        Date date = new Date((long) list.dt*1000);
        SimpleDateFormat dateDayOfWeek = new SimpleDateFormat("EEE");
        SimpleDateFormat dateDay = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat hour = new SimpleDateFormat("HH:mm z");
        diaSemana.setText(dateDayOfWeek.format(date));
        fecha.setText(dateDay.format(date));
        hora.setText(hour.format(date));

        temp.setText("Temp "+list.main.temp+"º");
        tempMax.setText("Max "+list.main.temp_max+"º");
        tempMin.setText("Min "+list.main.temp_min+"º");

        buttonExit.setOnClickListener(view -> finish());
    }
}
