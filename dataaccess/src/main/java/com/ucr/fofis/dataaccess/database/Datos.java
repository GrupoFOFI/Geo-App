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
<<<<<<< Updated upstream
            add(new Punto(1,"Punto 1",null, new GeoPoint(10.9514833333333,-85.7151),300.0d,new int[]{R.drawable.punto_01_1,R.drawable.punto_01_2,R.drawable.punto_01_3},0,new int[]{R.raw.audio1b},R.drawable.anim01_6));
            add(new Punto(2,"Bajo Rojo",null, new GeoPoint(10.9570833333333,-85.7336333333333),500.0d,new int[]{R.drawable.punto_02_1},0,new int[]{R.raw.audio2b},R.drawable.anim02_5));
            add(new Punto(3,"Bahia Junquillal",null, new GeoPoint(10.9698798844808,-85.7271389630961),600.0d,new int[]{R.drawable.punto_03_1},0,new int[]{R.raw.audio3b},R.drawable.anim3_4));
            add(new Punto(4,"Isla Muñecos",null, new GeoPoint(10.9777046049834,-85.7178071072068),200.0d,new int[]{R.drawable.punto_04_1, R.drawable.punto_04_3},0,new int[]{R.raw.audio4b},0));
            add(new Punto(5,"Punto 5",null, new GeoPoint(10.98176182858,-85.7177965097929),50.0d,new int[]{R.drawable.punto_05_1,R.drawable.punto_05_2},0,new int[]{R.raw.audio5b},0));
            add(new Punto(6,"Punto 6",null, new GeoPoint(10.9821743821365,-85.7190790800442),50.0d,new int[]{R.drawable.punto_06_1,R.drawable.punto_06_2},R.raw.video6_3_small,new int[]{R.raw.audio6b},0));
            add(new Punto(7,"Punto 7",null, new GeoPoint(10.9839,-85.7189),50.0d,new int[]{R.drawable.punto_07_1},0,new int[]{R.raw.audio7b},0));
            add(new Punto(8,"Punto 8",null, new GeoPoint(11.0016833333333,-85.748105),200.0d,new int[]{R.drawable.punto_08_1,R.drawable.punto_08_2, R.drawable.punto_08_2a},R.raw.video8_3_small,new int[]{R.raw.audio8b},0));
            add(new Punto(9,"Punto 9",null, new GeoPoint(11.0361333333333,-85.7485833333333),500.0d,new int[]{R.drawable.punto_09_1},R.raw.video9_6_small,new int[]{R.raw.audio9b},0));
            add(new Punto(10,"Punto 10",null, new GeoPoint(11.0381333333333,-85.7387166666666),100.0d,new int[]{R.drawable.punto_10_1,R.drawable.punto_10_1a},0,new int[]{R.raw.audio10b},0));
            add(new Punto(11,"Punto 11",null, new GeoPoint(11.0448166666666,-85.7417),200.0d,new int[]{R.drawable.punto_11_1,R.drawable.punto_11_2},0,new int[]{R.raw.audio11b},0));
            add(new Punto(12,"Punto 12",null, new GeoPoint(11.0462333333333,-85.7380666666666),200.0d,new int[]{R.drawable.punto_12_1,R.drawable.punto_12_2},0,new int[]{R.raw.audio12b},R.drawable.anim12_3));
            add(new Punto(13,"Punto 13",null, new GeoPoint(11.0488333333333,-85.7266666666666),100.0d,new int[]{R.drawable.punto_13_1},0,new int[]{R.raw.audio13b},0));
            add(new Punto(14,"Punto 14",null, new GeoPoint(11.0457666666666,-85.7219),200.0d,new int[]{R.drawable.punto_14_1,R.drawable.punto_14_2, R.drawable.punto_14_3},R.raw.video14_4_small,new int[]{R.raw.audio14b},0));
            add(new Punto(15,"Punto 15",null, new GeoPoint(11.0466881500087,-85.7075191718204),100.0d,new int[]{R.drawable.punto_15_1, R.drawable.punto_16_1,R.drawable.punto_17_1,R.drawable.punto_17_2, R.drawable.punto_17_3,R.drawable.punto_18_1},R.raw.video15_2_small,new int[]{R.raw.audio17b},0));
            add(new Punto(19,"Punto 16",null, new GeoPoint(11.0491333333333,-85.7074666666666),50.0d,new int[]{R.drawable.punto_19_1,R.drawable.punto_20_1,R.drawable.punto_21_1,R.drawable.punto_22_1, R.drawable.punto_22_1a,R.drawable.punto_23_1,R.drawable.punto_24_1, R.drawable.punto_24_1a},0,new int[]{R.raw.audio19b,R.raw.audio19_1b,R.raw.audio19_2b,R.raw.audio19_3b,R.raw.audio19_4b,R.raw.audio19_5b},0));
        }
    };

    public final static ArrayList<Recomendacion> RECOMENDACIONES = new ArrayList<Recomendacion>() {
        {
            add(new Recomendacion("En todo momento del viaje se debe de utilizar el chaleco salvavidas.", R.drawable.rec_04));
            add(new Recomendacion("Siempre acatar las instrucciones del capitán.", R.drawable.captain));
            add(new Recomendacion("¡Protéjase del sol! Use protector solar", R.drawable.rec_06));
            add(new Recomendacion("¡Protéjase del sol! Use sombrero", R.drawable.rec_05));
        }
    };


}
