package com.ucr.fofis.dataaccess.entity;

/**
 * Created by rapuc on 4/19/17.
 */

public class Recomendacion {

    private String texto;
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
}
