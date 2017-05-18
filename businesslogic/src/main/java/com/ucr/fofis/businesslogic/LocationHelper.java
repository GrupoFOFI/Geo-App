package com.ucr.fofis.businesslogic;

import android.location.Location;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.ucr.fofis.dataaccess.entity.Punto;

/**
 * <h1> Location Helper </h1>
 * <p>
 * Class used to handle the location methods
 * </p>
 *
 * @author Fofis
 * @version 1.0
 * @since 1.0
 */
public class LocationHelper {

    public static final int TARGET_AMMOUNT = 3;
    public int topidx[];
    public double mindist[];
    /**
     *
     */
    private static LatLng mLastLocation;
    /**
     * Represents a geographical location.
     */
    protected Location latestLocation;


    /**
     * Method that updates the last location
     *
     * @param location
     */
    public static void updateLastLocation(LatLng location) {
        mLastLocation = location;
    }

    /**
     * Getter for the last location
     *
     * @return
     */
    public static LatLng getLastLocation() {
        return mLastLocation;
    }


    public Location getLatestLocation() {
        return latestLocation;
    }

    public void setLatestLocation(Location latestLocation) {
        this.latestLocation = latestLocation;
    }

    /**
     * Method that retrieves the distance
     *
     * @param lat1
     * @param lat2
     * @param lon1
     * @param lon2
     * @param el1
     * @param el2
     * @return
     */
    public static double distance(double lat1, double lat2, double lon1, double lon2, double el1, double el2) {
        final int R = 6371; // Radius of the earth

        Double latDistance = Math.toRadians(lat2 - lat1);
        Double lonDistance = Math.toRadians(lon2 - lon1);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

    /**
     * Method that retrieves the point that the camera is currently pointing to
     *
     * @param xCam
     * @param yCam
     * @param loc
     * @return
     *
    public Punto pointingCamera(double xCam, double yCam, LatLng loc) {
        //calculate distance
        float errorAngle = 0;
        double v1 = 0;
        double v2 = 0; // first vector
        double productoPunto = 0;
        double mod1 = 0;
        double mod2 = 0;
        double angulo = 0;
        TargetObject c[] = getClosestBuildings(latestLocation,3);
        for (int i = 0; i < 3; i++) {
            errorAngle = getErrorAngle(loc, c[i].getLatitude(), c[i].getLongitude());
            if (errorAngle != -1) {
                v1 = loc.latitude - c[i].getLatitude();
                v2 = loc.longitude - c[i].getLongitude();
                productoPunto = v1 * xCam + v2 * yCam;
                mod1 = Math.sqrt(Math.pow(v1, 2) + Math.pow(v2, 2));
                mod2 = Math.sqrt(Math.pow(xCam, 2) + Math.pow(yCam, 2));
                angulo = productoPunto / (mod1 * mod2);
                angulo = angulo * 180 / Math.PI;// a grados
                if (angulo < errorAngle) {
                    return c[i];
                }
            }
        }
        return null;
    }*/


    /**
     * Method that retrieves the error angle
     *
     * @param loc
     * @param xBuild
     * @param yBuild
     * @return
     */
    public float getErrorAngle(LatLng loc, double xBuild, double yBuild) {
        Location newLocation = new Location("newlocation");
        newLocation.setLatitude(xBuild);
        newLocation.setLongitude(yBuild);
        Location d = new Location("");
        d.setLatitude(loc.latitude);
        d.setLongitude(loc.longitude);
        float dist = d.distanceTo(newLocation);
        float a;
        if (dist < 20) {
            a = 80 - dist * 5;
        } else {
            a = -1;
        }
        return a;
    }

}

