package com.ucr.fofis.dataaccess.database;

import com.ucr.fofis.dataaccess.entity.Recomendacion;

import java.util.ArrayList;

/**
 * Created by rapuc on 4/19/17.
 */

public class Recomendaciones {

    public final static ArrayList<Recomendacion> puntos = new ArrayList<Recomendacion>() {
        {
            add(new Recomendacion("Manitos Adentro",3));
        }
    };
}
