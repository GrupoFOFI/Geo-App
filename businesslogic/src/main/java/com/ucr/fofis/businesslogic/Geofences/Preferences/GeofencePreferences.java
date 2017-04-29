package com.ucr.fofis.businesslogic.Geofences.Preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.ucr.fofis.businesslogic.Preferences.Preferences;


/**
 * Created by enrico on 3/31/17.
 */

public class GeofencePreferences extends Preferences {
    private static final String GEOFENCE_READY = "GEOFENCE_READY";

    public GeofencePreferences(Context context) {
        super(context);
    }

    public boolean isGeofenceReady() {
        return getSharedPreferences().getBoolean(GEOFENCE_READY, true);
    }

    public void setGeofenceReady(boolean ready) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putBoolean(GEOFENCE_READY, ready);
        editor.commit();
    }
}
