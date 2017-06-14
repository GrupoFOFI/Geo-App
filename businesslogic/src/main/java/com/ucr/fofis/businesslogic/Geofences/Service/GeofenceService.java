package com.ucr.fofis.businesslogic.Geofences.Service;

import android.app.IntentService;
import android.content.Intent;
import android.location.Location;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;
import com.google.android.gms.maps.model.LatLng;
import com.ucr.fofis.businesslogic.LocationHelper;

import java.util.List;

/**
 * Created by enrico on 3/31/17.
 */

public class GeofenceService extends IntentService {
    public static final String GEOFENCE_ID = "GEOFENCE_ID";
    public static final String GEOFENCE_TRIGGER = "GEOFENCE_TRIGGER";
    public static String GEOFENCE_NOTIFICATION_FILTER = "com.ucr.fofis.GeofenceNotification";

    public GeofenceService() {
        super("com.ucr.fofis.GeofenceService");
    }

    protected void onHandleIntent(Intent intent) {
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if (geofencingEvent.hasError()) {
            return;
        }
        Location l = geofencingEvent.getTriggeringLocation();
        LocationHelper.updateLastLocation(new LatLng(l.getLatitude(), l.getLongitude()));

        // Get the transition type.
        int geofenceTransition = geofencingEvent.getGeofenceTransition();

        // Test that the reported transition was of interest.
        if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER) {

            // Get the geofences that were triggered. A single event can trigger
            // multiple geofences.
            List<Geofence> triggeringGeofences = geofencingEvent.getTriggeringGeofences();
            if (triggeringGeofences != null) {
                for (int i = 0; i < triggeringGeofences.size(); i++) {
                    Geofence geofence = triggeringGeofences.get(i);
                    String id = geofence.getRequestId();
                    Intent broadcastIntent = new Intent(GEOFENCE_NOTIFICATION_FILTER);
                    broadcastIntent.setAction(GEOFENCE_NOTIFICATION_FILTER);
                    broadcastIntent.putExtra(GEOFENCE_ID, Integer.parseInt(id)); // point id
                    broadcastIntent.putExtra(GEOFENCE_TRIGGER, Geofence.GEOFENCE_TRANSITION_ENTER);
                    sendBroadcast(broadcastIntent);
                }
            }
        } else if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT) {
            List<Geofence> triggeringGeofences = geofencingEvent.getTriggeringGeofences();
            if (triggeringGeofences != null) {
                for (int i = 0; i < triggeringGeofences.size(); i++) {
                    Geofence geofence = triggeringGeofences.get(i);
                    String id = geofence.getRequestId();
                    Intent broadcastIntent = new Intent(GEOFENCE_NOTIFICATION_FILTER);
                    broadcastIntent.setAction(GEOFENCE_NOTIFICATION_FILTER);
                    broadcastIntent.putExtra(GEOFENCE_ID, Integer.parseInt(id)); // point id
                    broadcastIntent.putExtra(GEOFENCE_TRIGGER, Geofence.GEOFENCE_TRANSITION_EXIT);
                    sendBroadcast(broadcastIntent);
                }
            }
        } else {
            // Log the error.
        }
    }

}
