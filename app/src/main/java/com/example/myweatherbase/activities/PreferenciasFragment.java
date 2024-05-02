package com.example.myweatherbase.activities;


import android.os.Bundle;

import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.myweatherbase.R;

import java.util.Arrays;
import java.util.List;

public class PreferenciasFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        setPreferencesFromResource(R.xml.preferencias, rootKey);

        // Modificacion de la vista de preferencias por codigo
        // ListPreference unidades
        final ListPreference unidades = findPreference("unidades");
        final List<String> unidades_entries = Arrays.asList(getResources().getStringArray(R.array.unidades_entries));
        final List<String> unidades_values = Arrays.asList(getResources().getStringArray(R.array.unidades_values));

        int posUni  = unidades_values.indexOf(GestionPreferencias.getInstance().getUnidades(getContext()));

        unidades.setSummary("Unidades en " + unidades_entries.get(posUni));
        unidades.setOnPreferenceChangeListener((preference, newValue) -> {

            int pos = unidades_values.indexOf(newValue);
            unidades.setSummary("Unidades en " + unidades_entries.get(pos));

            return true;
        });

        // ListPreference Idioma
        final ListPreference idiomas = findPreference("idioma");
        final List<String> idiomas_entries = Arrays.asList(getResources().getStringArray(R.array.language_entries));
        final List<String> idiomas_values = Arrays.asList(getResources().getStringArray(R.array.language_values));

        int posLan  = idiomas_values.indexOf(GestionPreferencias.getInstance().getLanguage(getContext()));

        idiomas.setSummary(""+idiomas_entries.get(posLan));
        idiomas.setOnPreferenceChangeListener((preference, newValue) -> {

            int pos = idiomas_values.indexOf(newValue);
            idiomas.setSummary(""+idiomas_entries.get(pos));

            return true;
        });

        // API
        final EditTextPreference editTextPreference = findPreference("editTextPreferenceAPI");
        editTextPreference.setSummary(GestionPreferencias.getInstance().getAPI(getContext()));
        editTextPreference.setOnPreferenceChangeListener((preference, newValue) -> {
            editTextPreference.setSummary("Actualmente: " + newValue);
            return true;
        });

        // Theme preferences with ListPreference
        ListPreference themePreference = getPreferenceManager().findPreference(getString(R.string.settings_theme_key));
        if (themePreference.getValue() == null) {
            themePreference.setValue(ThemeSetup.Mode.DEFAULT.name());
        }
        themePreference.setOnPreferenceChangeListener((preference, newValue) -> {
            ThemeSetup.applyTheme(ThemeSetup.Mode.valueOf((String) newValue));
            return true;
        });
    }
}
