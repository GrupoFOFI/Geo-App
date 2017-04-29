package com.ucr.fofis.businesslogic.Preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by enrico on 10/21/16.
 */
public abstract class Preferences {
    Context context;
    SharedPreferences prefs = null;

    public Preferences(Context context) {
        this.context = context;
    }

    protected Context getContext() {
        return context;
    }

    protected SharedPreferences getSharedPreferences() {
        if (prefs == null)
            prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        return prefs;
    }
}
