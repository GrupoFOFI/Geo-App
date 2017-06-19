package com.ucr.fofis.businesslogic;

import com.ucr.fofis.dataaccess.database.Datos;
import com.ucr.fofis.dataaccess.entity.Punto;

import java.util.List;

/**
 * Created by tete on 4/29/17.
 */
public final class TourManager {
    /**
     * Retorna los putnos del tour
     * @return
     */
    public static List<Punto> getPoints() {
        return Datos.PUNTOS;
    }
}
