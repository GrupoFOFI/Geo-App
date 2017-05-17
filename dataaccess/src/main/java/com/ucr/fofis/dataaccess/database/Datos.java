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
            add(new Punto(1,"Punto1",null,new GeoPoint(-85.7151,10.95148333),50.0, new int[]{R.drawable.punto_01_1, R.drawable.punto_01_2, R.drawable.punto_01_3},0,R.raw.audio_1bl,0));
            add(new Punto(2,"Bajo Rojo",null,new GeoPoint(-85.73363333,10.95708333),50.0, new int[]{R.drawable.punto_02_1},0,R.raw.audio_2bl,0));
            add(new Punto(3,"Bahia Junquillal",null,new GeoPoint(-85.72713896,10.96987988),50.0, new int[]{R.drawable.punto_03_1},0,R.raw.audio_3bl,0));
            add(new Punto(4,"Isla Muñecos",null,new GeoPoint(-85.71780711,10.9777046),50.0, new int[]{R.drawable.punto_04_1, R.drawable.punto_04_3},0,R.raw.audio_4bl,0));
            add(new Punto(5,"Punto5",null,new GeoPoint(-85.71779651,10.98176183),50.0, new int[]{R.drawable.punto_05_1, R.drawable.punto_05_2},0,R.raw.audio_5bl,0));
            add(new Punto(6,"Punto6",null,new GeoPoint(-85.71907908,10.98217438),50.0, new int[]{R.drawable.punto_06_1, R.drawable.punto_06_2},0,R.raw.audio_6bl,0));
            add(new Punto(7,"Punto7",null,new GeoPoint(-85.7189,10.9839),50.0, new int[]{R.drawable.punto_07_1},0,R.raw.audio_7bl,0));
            add(new Punto(8,"Punto8",null,new GeoPoint(-85.748105,11.00168333),50.0, new int[]{R.drawable.punto_08_1, R.drawable.punto_08_2, R.drawable.punto_08_2a},0,R.raw.audio_8bl,0));
            add(new Punto(9,"Punto9",null,new GeoPoint(-85.74858333,11.03613333),50.0, new int[]{R.drawable.punto_09_1},0,R.raw.audio_9bl,0));
            add(new Punto(10,"Punto10",null,new GeoPoint(-85.73871667,11.03813333),50.0, new int[]{R.drawable.punto_10_1, R.drawable.punto_10_1a},0,R.raw.audio_10bl,0));
            add(new Punto(11,"Punto11",null,new GeoPoint(-85.7417,11.04481667),50.0, new int[]{R.drawable.punto_11_1, R.drawable.punto_11_2},0,R.raw.audio_11bl,0));
            add(new Punto(12,"Punto12",null,new GeoPoint(-85.73806667,11.04623333),50.0, new int[]{R.drawable.punto_12_1, R.drawable.punto_12_2},0,R.raw.audio_12bl,0));
            add(new Punto(13,"Punto13",null,new GeoPoint(-85.72666667,11.04883333),50.0, new int[]{R.drawable.punto_13_1},0,R.raw.audio_13bl,0));
            add(new Punto(14,"Punto14",null,new GeoPoint(-85.7219,11.04576667),50.0, new int[]{R.drawable.punto_14_1, R.drawable.punto_14_2, R.drawable.punto_14_3},0,R.raw.audio_14bl,0));
            add(new Punto(15,"Punto15",null,new GeoPoint(-85.70835,11.04653333),50.0, new int[]{R.drawable.punto_15_1},0,R.raw.audio_15bl,0));
            add(new Punto(16,"Punto16",null,new GeoPoint(-85.70758198,11.04667491),50.0, new int[]{R.drawable.punto_16_1},0,R.raw.audio_16bl,0));
            add(new Punto(17,"Punto17",null,new GeoPoint(-85.70738301,11.04695583),50.0, new int[]{R.drawable.punto_17_1, R.drawable.punto_17_2, R.drawable.punto_17_3},0,R.raw.audio_17bl,0));
            add(new Punto(18,"Punto18",null,new GeoPoint(-85.7072228,11.04721267),50.0, new int[]{R.drawable.punto_18_1},0,R.raw.audio_18bl,0));
            add(new Punto(19,"Punto19",null,new GeoPoint(-85.70713333,11.0489),50.0, new int[]{R.drawable.punto_19_1},0,R.raw.audio_19bl,0));
            add(new Punto(20,"Punto20",null,new GeoPoint(-85.70726667,11.04898333),50.0, new int[]{R.drawable.punto_20_1},0,R.raw.audio_20bl,0));
            add(new Punto(21,"Punto21",null,new GeoPoint(-85.70728333,11.04903333),50.0, new int[]{R.drawable.punto_21_1},0,R.raw.audio_21bl,0));
            add(new Punto(22,"Punto22",null,new GeoPoint(-85.70746667,11.04913333),50.0, new int[]{R.drawable.punto_22_1, R.drawable.punto_22_1a},0,R.raw.audio_22bl,0));
            add(new Punto(23,"Punto23",null,new GeoPoint(-85.70773333,11.04918333),50.0, new int[]{R.drawable.punto_23_1},0,R.raw.audio_23bl,0));
            add(new Punto(24,"Punto24",null,new GeoPoint(-85.7082,11.04926667),50.0, new int[]{R.drawable.punto_24_1, R.drawable.punto_24_1a},0,R.raw.audio_24bl,0));
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
