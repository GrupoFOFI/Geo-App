package com.ucr.fofis.dataaccess.entity;

import org.osmdroid.util.GeoPoint;

import java.io.Serializable;

/**
 * Created by rapuc on 4/19/17.
 *  Clase que representa cada uno de los puntos geográficos, incluyendo información extra para mostrar en el app.
 */
public class Punto implements Serializable {


    private int idntfcdr;
    private String nombre;
    private String descripcion;
    private GeoPoint geoPoint;
    private double geofenceRadio;
    private Resource[] imagenes;
    private Resource video;
    private Resource[] audios;
    private Resource animation;

    public Punto() {
    }

    public Punto(int idntfcdr, String nombre, String descripcion, GeoPoint geoPoint, double geofenceRadio, Resource[] imagenes, Resource video, Resource[] audios, Resource animation) {
        this.idntfcdr = idntfcdr;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.geoPoint = geoPoint;
        this.geofenceRadio = geofenceRadio;
        this.imagenes = imagenes;
        this.video = video;
        this.audios = audios;
        this.animation = animation;
    }

    public int getIdntfcdr() {
        return idntfcdr;
    }

    public void setIdntfcdr(int idntfcdr) {
        this.idntfcdr = idntfcdr;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public GeoPoint getGeoPoint() {
        return geoPoint;
    }

    public void setGeoPoint(GeoPoint geoPoint) {
        this.geoPoint = geoPoint;
    }

    public double getGeofenceRadio() {
        return geofenceRadio;
    }

    public void setGeofenceRadio(double geofenceRadio) {
        this.geofenceRadio = geofenceRadio;
    }

    public Resource[] getImagenes() {
        return imagenes;
    }

    public void setImagenes(Resource[] imagenes) {
        this.imagenes = imagenes;
    }

    public Resource getVideo() {
        return video;
    }

    public void setVideo(Resource video) {
        this.video = video;
    }

    public Resource[] getAudios() {
        return audios;
    }

    public void setAudios(Resource[] audios) {
        this.audios = audios;
    }

    public Resource getAnimation() {
        return animation;
    }

    public void setAnimation(Resource animation) {
        this.animation = animation;
    }
}