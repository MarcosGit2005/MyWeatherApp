package com.example.myweatherbase.activities.model;

import com.example.myweatherbase.activities.model.prevision.Clouds;
import com.example.myweatherbase.activities.model.prevision.Main;
import com.example.myweatherbase.activities.model.prevision.Rain;
import com.example.myweatherbase.activities.model.prevision.Sys;
import com.example.myweatherbase.activities.model.prevision.Weather;
import com.example.myweatherbase.activities.model.prevision.Wind;

import java.io.Serializable;
import java.util.ArrayList;

public class List implements Serializable {
    public int dt;
    public Main main;
    public ArrayList<Weather> weather;
    public Clouds clouds;
    public Wind wind;
    public int visibility;
    public double pop;
    public Sys sys;
    public String dt_txt;
    public Rain rain;
}
