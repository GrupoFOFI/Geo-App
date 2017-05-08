package com.ucr.fofis.dataaccess.database;

import com.ucr.fofis.dataaccess.R;

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

}
