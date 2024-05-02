package com.example.myweatherbase.activities;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.myweatherbase.R;


public class GestionPreferencias {

    private SharedPreferences pref;
    private static GestionPreferencias gestionPreferencias;

    private GestionPreferencias(){

    }

    public static GestionPreferencias getInstance(){
        if(gestionPreferencias==null)
            gestionPreferencias = new GestionPreferencias();
        return gestionPreferencias;
    }

    private void inicializa(Context context) {
        if (pref == null)
            pref = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public String getUnidades(Context context){
        inicializa(context);
        return pref.getString("unidades","standard");
    }
    public String getLanguage(Context context){
        inicializa(context);
        return pref.getString("idioma","es");
    }

    public String getAPI(Context context){
        inicializa(context);
        return pref.getString("editTextPreferenceAPI","4075320d62ffb8ea170b13cac84f86f5");
    }
    public String getTheme(Context context){
        inicializa(context);
        return pref.getString(context.getString(R.string.settings_theme_key),ThemeSetup.Mode.DEFAULT.name());
    }

}