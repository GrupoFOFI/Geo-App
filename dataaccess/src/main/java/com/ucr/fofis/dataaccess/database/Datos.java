package com.ucr.fofis.dataaccess.database;

import com.ucr.fofis.dataaccess.R;
import com.ucr.fofis.dataaccess.entity.Punto;
import com.ucr.fofis.dataaccess.entity.Recomendacion;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;

/**
 * Created by rapuc on 4/19/17.
 *  Esta clase guarda todos los datos estáticos que poblen las diferentes clases del app.
 *  Por ahora, las únicas utilizadas son los puntos de recomendación, las cuales separan el texto y la imagen que lleva el diálogo.
 */
public class Datos {

    public final static ArrayList<Punto> PUNTOS = new ArrayList<Punto>() {
        {

            add(new Punto(2, "Bajo Rojo", null, new GeoPoint(-85.738716666666647, 11.038133333333329), 100.0, null, 0, 0, 0));
        }
    };

    public final static ArrayList<Recomendacion> RECOMENDACIONES = new ArrayList<Recomendacion>() {
        {
            add(new Recomendacion("En TODO momento del viaje se debe de utilizar el chaleco salvavidas sin excepciones.", R.drawable.salvavidas));
            add(new Recomendacion("Siempre acatar las instrucciones del capitán.", R.drawable.captain));
            add(new Recomendacion("Mantenerse del mismo lado en donde se les fue ubicado.", R.drawable.boat));
        }
    };
}
