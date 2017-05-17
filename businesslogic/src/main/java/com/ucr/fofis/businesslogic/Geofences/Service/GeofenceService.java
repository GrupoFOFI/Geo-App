package com.ucr.fofis.businesslogic.Geofences.Service;

import android.app.IntentService;
import android.content.Intent;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;

/**
 * Created by enrico on 3/31/17.
 */

public class GeofenceService extends IntentService {
    public static final String GEOFENCE_ID = "GEOFENCE_ID";
    public static String GEOFENCE_NOTIFICATION_FILTER = "net.symbiotic.GeofenceNotification";

    public GeofenceService() {
        super("net.symbiotic.GeofenceService");
    }

    protected void onHandleIntent(Intent intent) {
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if (geofencingEvent.hasError()) {
            return;
        }

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
                    broadcastIntent.putExtra(GEOFENCE_ID, id);
                    sendBroadcast(broadcastIntent);
                }
            }
            // TODO: send notification to user on geofence enter.

            // Get the transition details as a String.
            /*String geofenceTransitionDetails = getGeofenceTransitionDetails(
                    this,
                    geofenceTransition,
                    triggeringGeofences
            );*/

            // Send notification and log the transition details.
            //sendNotification(geofenceTransitionDetails);
        } else {
            // Log the error.
        }
    }

}
