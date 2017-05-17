package com.ucr.fofis.dataaccess.entity;

/**
 * Created by tete on 4/29/17.
 */

public class Geofence {
    int pointId;
    float radius;
    float latitude, longitude;

    public Geofence(int pointId) {
        this.pointId = pointId;
    }

    public int getPointId() {
        return pointId;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
