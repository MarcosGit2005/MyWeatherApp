package com.example.myweatherbase.API;


import com.example.myweatherbase.base.Parameters;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;


public class Connector{

    private static Connector connector;
    private static Conversor conversor;
    private static CallMethods callMethodsObject;

    public static Connector getConector(){
        if(connector == null){
            connector = new Connector();
            conversor = Conversor.getConversor();
            callMethodsObject = CallMethods.getCallMethodsObject();
        }
        return connector;
    }

    public <T> List<T> getAsList(Class<T> clazz, String path){
        String url = Parameters.URL + Parameters.URL_OPTIONS + path;
        String jsonResponse = callMethodsObject.get(url);
        if(jsonResponse != null)
            return conversor.fromJsonList(jsonResponse, clazz);
        return null;
    }


    public <T> T get(Class<T> clazz, String path){
        String url = Parameters.URL + "forecast?appid=" + Parameters.API + "&lang=" + Parameters.LANG + "&units=" + Parameters.UNITS + path;
        String jsonResponse = callMethodsObject.get(url);
        if(jsonResponse != null)
            return conversor.fromJson(jsonResponse, clazz);
        return null;
    }

}

