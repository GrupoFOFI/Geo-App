package com.ucr.fofis.businesslogic;

import com.ucr.fofis.dataaccess.database.Ruta;

import org.osmdroid.util.GeoPoint;

import java.util.List;

/**
 * Created by tete on 4/29/17.
 */
public final class TourManager {
    /**
     * Returns the tour points
     * @return
     */
    public static List<GeoPoint> getPoints() {
        return Ruta.points;
    }
}
