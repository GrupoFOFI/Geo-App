package com.ucr.fofis.dataaccess.entity;

import org.osmdroid.util.GeoPoint;

/**
 * Created by rapuc on 4/19/17.
 *  Clase que representa cada uno de los puntos geográficos, incluyendo información extra para mostrar en el app.
 */
public class Punto {


    private int idntfcdr;
    private String nombre;
    private String descripcion;
    private GeoPoint geoPoint;
    private Double geofenceRadio;
    private int[] imagenes;
    private Integer videoId;
    private Integer audioId;
    private Integer animationId;

    public Punto() {
    }

    public Punto(int idntfcdr, String nombre, String descripcion, GeoPoint geoPoint, Double geofenceRadio, int[] imagenes, Integer videoId, Integer audioId, Integer animationId) {
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

    public Double getGeofenceRadio() {
        return geofenceRadio;
    }

    public void setGeofenceRadio(Double geofenceRadio) {
        this.geofenceRadio = geofenceRadio;
    }

    public int[] getImagenes() {
        return imagenes;
    }

    public void setImagenes(int[] imagenes) {
        this.imagenes = imagenes;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Integer getAudioId() {
        return audioId;
    }

    public void setAudioId(Integer audioId) {
        this.audioId = audioId;
    }

    public Integer getAnimationId() {
        return animationId;
    }

    public void setAnimationId(Integer animationId) {
        this.animationId = animationId;
    }
}