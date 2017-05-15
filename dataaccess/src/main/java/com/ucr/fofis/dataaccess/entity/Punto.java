package com.ucr.fofis.dataaccess.entity;

/**
 * Created by rapuc on 4/19/17.
 *  Clase que representa cada uno de los puntos geográficos, incluyendo información extra para mostrar en el app.
 */
public class Punto {


    private int idntfcdr;
    private String nombre;
    private String descripcion;
    private Double latitud;
    private Double longitud;
    private Double geofenceRadio;
    private int[] imagenes ;
    private Integer videoId;
    private Integer audioId;
    private Integer animationId;


    /* Constructores */

    public Punto(int idntfcdr, String nombre, String descripcion, Double latitud, Double longitud, Double geofenceRadio, int[] imagenes, Integer videoId, Integer audioId, Integer animationId) {
        this.idntfcdr = idntfcdr;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.latitud = latitud;
        this.longitud = longitud;
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

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
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

    public void setImagenes( int[] imagenes) {
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