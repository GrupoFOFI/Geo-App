package com.ucr.fofis.businesslogic;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.ucr.fofis.businesslogic.Geofences.GeofenceUtils;
import com.ucr.fofis.businesslogic.Geofences.Preferences.GeofencePreferences;
import com.ucr.fofis.businesslogic.Geofences.Service.GeofenceService;
import com.ucr.fofis.dataaccess.entity.Punto;

import java.util.List;

/**
 *  Clase para el manejo general de los Geofecnes
 *
 * */
public class GeofenceManager implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        LocationListener {
    private GeofenceService geofenceService;
    private GeofencePreferences geofencePreferences;
    private static GeofenceManager geofenceManagerInstance;
    private PendingIntent mGeofencePendingIntent;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest locationRequest;
    private Context mContext;

    private static final long POLLING_FREQ = 1000 * 60 * 3;
    private static final long FASTEST_UPDATE_FREQ = 1000 * 30;

    private List<Punto> mGeofences;

    public GeofenceManager(Context context) throws Exception {
        mContext = context;
        mGeofences = TourManager.getPoints();
        geofencePreferences = new GeofencePreferences(context);
        if (geofenceManagerInstance == null) geofenceManagerInstance = this;
        else throw new Exception("Can't instantiate more than one GeofenceManager, use GeofenceManager.getInstance() instead");
    }

    /**
     * Inicializa el GeofenceManager.
     * Requiere una actividad.
     *
     * @param activity requirida para el cliente de Goggle API.
     */
    public void init(Activity activity) {
        buildGoogleApiClient(activity);
        GeofenceUtils.init(mGeofences);
        mContext = activity;
    }

    public void stop() {
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
    }

    private void buildGoogleApiClient(Activity activity) {
        mGoogleApiClient = new GoogleApiClient.Builder(activity)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        if (hasLocationPermission())
            mGoogleApiClient.connect();
    }

    private PendingIntent getGeofencePendingIntent() {
        // Reutiliza el PendingIntent si ya se tiene.
        if (mGeofencePendingIntent != null) {
            return mGeofencePendingIntent;
        }
        Intent intent = new Intent(mContext, GeofenceService.class);
        // Se utiliza FLAG_UPDATE_CURRENT para obtener el intent pendiente de vuelta cuando
        // se llama addGeofences() y removeGeofences().
        return PendingIntent.getService(mContext, 0, intent, PendingIntent.
                FLAG_UPDATE_CURRENT);
    }

    public boolean hasLocationPermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION);
        return (permissionCheck == PackageManager.PERMISSION_GRANTED);
    }

    public static GeofenceManager getInstance() {
        return geofenceManagerInstance;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (mGeofences != null && mGeofences.size() > 0) {
            GeofenceUtils.stopGeofences(getGeofencePendingIntent(), mGoogleApiClient);
            if (GeofenceUtils.startGeofences(mGeofences, getGeofencePendingIntent(), mGoogleApiClient)) {
                //geofencePreferences.setGeofenceReady(true);
            }
        }
        // comience las actualizason de ubicación
        locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(POLLING_FREQ)
                .setFastestInterval(FASTEST_UPDATE_FREQ);
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        System.out.println(location.toString());
    }
}
