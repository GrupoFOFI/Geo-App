package com.ucr.fofis.dataaccess.entity;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rapuc on 4/19/17.
 *  Clase que representa cada uno de los puntos geográficos, incluyendo información extra para mostrar en el app.
 */
public class Punto {


    private int idntfcdr;
    private String nombre;
    private String descripcion;
    private GeoPoint geoPoint;
    private double geofenceRadio;
    private List<Integer> imagenes = new ArrayList<Integer>(0); //
    private int videoId;
    private int audioId;
    private int animationId;


    /* Constructores */

    public Punto(int idntfcdr, String nombre, String descripcion, GeoPoint geoPoint, double geofenceRadio, List<Integer> imagenes, int videoId, int audioId, int animationId) {
        this.idntfcdr = idntfcdr;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.geoPoint = geoPoint;
        this.geofenceRadio = geofenceRadio;
        this.imagenes = imagenes;
        this.videoId = videoId;
        this.audioId = audioId;
        this.animationId = animationId;
    }

    public Punto() {
    }

    /* Getters & setters */

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

    public List<Integer> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<Integer> imagenes) {
        this.imagenes = imagenes;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public int getAudioId() {
        return audioId;
    }

    public void setAudioId(int audioId) {
        this.audioId = audioId;
    }

    public int getAnimationId() {
        return animationId;
    }

    public void setAnimationId(int animationId) {
        this.animationId = animationId;
    }
}