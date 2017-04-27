package com.ucr.fofis.geoapp.Application;

import android.app.Application;

import com.ucr.fofis.businesslogic.ResourceManager;

/**
 * Created by enrico on 4/27/17.
 */

public class GeoApp extends Application {
    ResourceManager resourceManager;
    @Override
    public void onCreate() {
        super.onCreate();
        try {
            resourceManager = new ResourceManager(getApplicationContext());
        } catch (Exception e) {

        }
    }
}
