package com.ucr.fofis.geoapp.Application;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDexApplication;
import android.support.v4.app.NotificationCompat;

import com.google.android.gms.location.Geofence;
import com.ucr.fofis.businesslogic.GeofenceManager;
import com.ucr.fofis.businesslogic.Geofences.Service.GeofenceService;
import com.ucr.fofis.businesslogic.ResourceManager;
import com.ucr.fofis.businesslogic.TourManager;
import com.ucr.fofis.dataaccess.entity.Punto;
import com.ucr.fofis.geoapp.CameraActivity;
import com.ucr.fofis.geoapp.R;

/**
 * Created by enrico on 4/27/17.
 * Clase que inicializa el manejador de recursos y controlador de geofences
 * habilita la recomendación visual y el audio introductorio
 */

public class GeoApp extends MultiDexApplication {
    ResourceManager resourceManager;
    GeofenceManager geofenceManager;
    public static boolean recommendationPlay;
    public static boolean audioPlay;

    private GeofenceNotificationReceiver geofenceNotificationReceiver = new GeofenceNotificationReceiver();

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            resourceManager = new ResourceManager(getApplicationContext());
            geofenceManager = new GeofenceManager(getApplicationContext());
        } catch (Exception e) {

        }
        //registerReceiver(geofenceNotificationReceiver, new IntentFilter(GeofenceService.GEOFENCE_NOTIFICATION_FILTER));
        recommendationPlay = true;
        audioPlay = true;
    }

    public class GeofenceNotificationReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int id = intent.getIntExtra(GeofenceService.GEOFENCE_ID, -1); // point id
            int trigger = intent.getIntExtra(GeofenceService.GEOFENCE_TRIGGER, 0);
            if (trigger == Geofence.GEOFENCE_TRANSITION_ENTER) { // entered region
                showNotification("Atención","Se esta acercando al punto" + TourManager.getPoints().get(id).getNombre(), TourManager.getPoints().get(id));
            } else if (trigger == Geofence.GEOFENCE_TRANSITION_EXIT) { // left region

            }
        }

        /**
         * Sends a notification.
         *
         * @param title the notification's title.
         * @param description the notification's description.
         */
        private void showNotification(String title, String description, Punto point) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
            builder.setContentTitle(title).setContentText(description);
            Intent resultIntent = new Intent(getApplicationContext(), CameraActivity.class);
            resultIntent.putExtra(CameraActivity.POINT_TAG, point);
            PendingIntent resultPendingIntent =
                    PendingIntent.getActivity(
                            getApplicationContext(),
                            0,
                            resultIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT
                    );
            builder.setSmallIcon(R.drawable.ic_launcher);
            builder.setContentIntent(resultPendingIntent);
            builder.setAutoCancel(true);
            builder.setColor(getResources().getColor(R.color.colorPrimaryDark));
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.notify(5, builder.build());
        }
    }
}
