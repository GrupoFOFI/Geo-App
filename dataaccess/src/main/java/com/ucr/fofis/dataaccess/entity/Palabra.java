package com.ucr.fofis.dataaccess.entity;

/**
 * Created by rapuc on 6/22/17.
 * Representa las palabras del glosario con su significado y audio respectivo.
 */

public class Palabra {

    private int audioId;
    private String palabra;
    private String significado;

    public Palabra(int audioId, String palabra, String significado) {
        this.audioId = audioId;
        this.palabra = palabra;
        this.significado = significado;
    }

    public Palabra() {
    }

    public int getAudioId() {
        return audioId;
    }

    public void setAudioId(int audioId) {
        this.audioId = audioId;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public String getSignificado() {
        return significado;
    }

    public void setSignificado(String significado) {
        this.significado = significado;
    }
}
