package com.ucr.fofis.dataaccess.entity;

/**
 * Created by rapuc on 4/19/17.
 * Información contenida en una carta de recomendación
 */

public class Recomendacion {

    //Texto inlcuiddo en la recomendacion
    private String texto;

    //Imagen mostrada en la recomendacion
    private int imagen;

    public Recomendacion(String texto, int imagen) {
        this.texto = texto;
        this.imagen = imagen;
    }

    public Recomendacion() {
    }


    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
