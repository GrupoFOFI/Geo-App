package com.ucr.fofis.dataaccess.database;

import com.ucr.fofis.dataaccess.entity.Punto;

import java.util.ArrayList;

/**
 * Created by rapuc on 4/19/17.
 */

public class Puntos {

    public final static ArrayList<Punto> puntos = new ArrayList<Punto>() {
        {

            add(new Punto(2, "Bajo Rojo", null, -85.738716666666647, 11.038133333333329, 100.0, null, null, null, null));
        }
    };
}
