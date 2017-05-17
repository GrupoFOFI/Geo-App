package com.ucr.fofis.dataaccess.database;

import com.ucr.fofis.dataaccess.R;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rapuc on 4/19/17.
 * Clase para guardar información estática sobre la Ruta.
 */
public class Ruta {

    //Título de la ruta
    public static final String TITULO = "Recorrido Isla Bolaños";

    //Descripción o texto introductorio a la ruta
    public static final String DESCRIPCION = "La isla Bolaños y los lugares cercanos permitirán una apreciación de rocas, depositadas en los taludes continentales, emergidos y visibles en superficie por diversos procesos tectónicos y de erosión. En esta ruta se identificarán rocas de hasta 40 millones de años, a través de 24 sitios de interés patrimonial.";

    //Lista de ids de las imágenes a poner en el carrusel.
    public static final int[] IMAGENES_CARRUSEL = new int[]{1,2,3,4,5,6};

    //Id del recurso del audio de bienvenida
    public static Integer AUDIO_INTRO = R.raw.intro;

    //Link a la página web de la ruta
    public static final String WEB_PAGE_URL = "http://cicg.ucr.ac.cr/";

    public static List<GeoPoint> points = new ArrayList<GeoPoint>() {
        {
            add(new GeoPoint(-85.7151,10.95148333));
            add(new GeoPoint(-85.73363333,10.95708333));
            add(new GeoPoint(-85.72713896,10.96987988));
            add(new GeoPoint(-85.71780711,10.9777046));
            add(new GeoPoint(-85.71779651,10.98176183));
            add(new GeoPoint(-85.71907908,10.98217438));
            add(new GeoPoint(-85.7189,10.9839));
            add(new GeoPoint(-85.748105,11.00168333));
            add(new GeoPoint(-85.74858333,11.03613333));
            add(new GeoPoint(-85.73871667,11.03813333));
            add(new GeoPoint(-85.7417,11.04481667));
            add(new GeoPoint(-85.73806667,11.04623333));
            add(new GeoPoint(-85.72666667,11.04883333));
            add(new GeoPoint(-85.7219,11.04576667));
            add(new GeoPoint(-85.70835,11.04653333));
            add(new GeoPoint(-85.70758198,11.04667491));
            add(new GeoPoint(-85.70738301,11.04695583));
            add(new GeoPoint(-85.7072228,11.04721267));
            add(new GeoPoint(-85.70713333,11.0489));
            add(new GeoPoint(-85.70726667,11.04898333));
            add(new GeoPoint(-85.70728333,11.04903333));
            add(new GeoPoint(-85.70746667,11.04913333));
            add(new GeoPoint(-85.70773333,11.04918333));
            add(new GeoPoint(-85.7082,11.04926667));
            add(new GeoPoint(-85.70865,11.04933333));
        }
    };
}
