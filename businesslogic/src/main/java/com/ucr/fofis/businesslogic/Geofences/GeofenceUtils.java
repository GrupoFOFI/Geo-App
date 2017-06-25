package com.ucr.fofis.businesslogic.Geofences;

import android.app.PendingIntent;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.ucr.fofis.businesslogic.GeofenceManager;
import com.ucr.fofis.dataaccess.entity.Punto;

import java.util.ArrayList;
import java.util.List;

/**
 * Comunicación directa con el Android Geofence API.
 *
 * Created by enrico on 3/31/17.
 */
public class GeofenceUtils {
    private static List<com.google.android.gms.location.Geofence> mGeofenceList;


    /**
     * Inicializa la lista de Geofences.
     *
     * @param geofences Los geofences almacenados.
     */
    public static void init(List<Punto> geofences) {
        createGeofenceList(geofences);
    }

    private static void createGeofenceList(List<Punto> geofences) {
        mGeofenceList = new ArrayList<>();
        int i = 0;
        for (Punto geofence : geofences) {
            com.google.android.gms.location.Geofence.Builder builder = new com.google.android.gms.location.Geofence.Builder();
            float radius = geofence.getGeofenceRadio().floatValue();
            builder.setRequestId("" + i)
                    .setCircularRegion(geofence.getGeoPoint().getLatitude(), geofence.getGeoPoint().getLongitude(), radius)
                    .setExpirationDuration(com.google.android.gms.location.Geofence.NEVER_EXPIRE)
                    .setTransitionTypes(com.google.android.gms.location.Geofence.GEOFENCE_TRANSITION_ENTER | com.google.android.gms.location.Geofence.GEOFENCE_TRANSITION_EXIT)
                    .setNotificationResponsiveness(0);
            mGeofenceList.add(builder.build());
            i++;
        }
    }

    /**
     * Comienza a monitorear los Geofences específicos.
     * El límite son 100 Geofences por dispositivo.
     *
     * @param geofences los Geofences a monitorear.
     * @param pendingIntent el intent pendiente que espera el evento que se activa desde el Geofence API.
     * @param googleApiClient El cliente del Google API.
     * @return verdadero si el monitoreo se comienza exitosamente, de otra manera falso.
     */
    public static boolean startGeofences(List<Punto> geofences, PendingIntent pendingIntent, GoogleApiClient googleApiClient) {
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
     * Detiene el monitoreo de Geofences.
     *
     * @param pendingIntent el intent pendiente que espera el evento que se activa desde el Geofence API..
     * @param googleApiClient El cliente del Google API.
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
