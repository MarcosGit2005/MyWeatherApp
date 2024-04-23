package com.example.myweatherbase.activities;

public enum Ciudades {
    LAPOBLA(39.5862518,-0.5411163);
    private double lat;
    private double lon;
    Ciudades(double lat, double lon){
        this.lat = lat;
        this.lon = lon;
    }
    public double getLat(){return lat;}
    public double getLon(){return lon;}

    @Override
    public String toString(){
        return this.name();
    }
}
