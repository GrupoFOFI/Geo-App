package com.ucr.fofis.businesslogic.Geofences;

import android.app.PendingIntent;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.ucr.fofis.businesslogic.GeofenceManager;
import com.ucr.fofis.dataaccess.entity.Geofence;

import java.util.ArrayList;
import java.util.List;

/**
 * Direct communication with the Android Geofence API.
 *
 * Created by enrico on 3/31/17.
 */
public class GeofenceUtils {
    private static List<com.google.android.gms.location.Geofence> mGeofenceList;


    /**
     * Initializes the geofences list.
     *
     * @param geofences the stored geofences.
     */
    public static void init(List<Geofence> geofences) {
        createGeofenceList(geofences);
    }

    private static void createGeofenceList(List<Geofence> geofences) {
        mGeofenceList = new ArrayList<>();
        for (Geofence geofence : geofences) {
            com.google.android.gms.location.Geofence.Builder builder = new com.google.android.gms.location.Geofence.Builder();
            float radius = geofence.getRadius();
            builder.setRequestId("" + geofence.getPointId())
                    .setCircularRegion(geofence.getLatitude(), geofence.getLongitude(), radius)
                    .setExpirationDuration(com.google.android.gms.location.Geofence.NEVER_EXPIRE)
                    .setTransitionTypes(com.google.android.gms.location.Geofence.GEOFENCE_TRANSITION_ENTER | com.google.android.gms.location.Geofence.GEOFENCE_TRANSITION_EXIT)
                    .setNotificationResponsiveness(0);
            mGeofenceList.add(builder.build());
        }
    }

    /**
     * Starts monitoring for the specified geofences.
     * The limit is 100 geofences per device.
     *
     * @param geofences the geofences to monitor.
     * @param pendingIntent the pending intent that expects the event triggers from the geofence api.
     * @param googleApiClient the google api client.
     * @return true if geofence monitoring starts successfully, otherwise false.
     */
    public static boolean startGeofences(List<Geofence> geofences, PendingIntent pendingIntent, GoogleApiClient googleApiClient) {
        if (GeofenceManager.getInstance().hasLocationPermission()) {
            createGeofenceList(geofences);
            if (googleApiClient != null && googleApiClient.isConnected()) {
                LocationServices.GeofencingApi.addGeofences(googleApiClient, getGeofencingRequest(), pendingIntent);
            } else return false;
            return true;
        }
        return false;
    }

    /**
     * Stops monitoring for geofences.
     *
     * @param pendingIntent the pending intent that expects the event triggers from the geofence api.
     * @param googleApiClient the google api client.
     */
    public static void stopGeofences(PendingIntent pendingIntent, GoogleApiClient googleApiClient) {
        if (GeofenceManager.getInstance().hasLocationPermission()) {
            if (googleApiClient != null && googleApiClient.isConnected()) {
                LocationServices.GeofencingApi.removeGeofences(googleApiClient, pendingIntent);
            }
        }
    }

    private static GeofencingRequest getGeofencingRequest() {
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
        builder.addGeofences(mGeofenceList);
        return builder.build();
    }
}
