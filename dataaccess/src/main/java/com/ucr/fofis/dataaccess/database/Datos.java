package com.ucr.fofis.dataaccess.database;

import com.ucr.fofis.dataaccess.R;
import com.ucr.fofis.dataaccess.entity.Punto;
import com.ucr.fofis.dataaccess.entity.Recomendacion;

import java.util.ArrayList;

/**
 * Created by rapuc on 4/19/17.
 *  Esta clase guarda todos los datos estáticos que poblen las diferentes clases del app.
 *  Por ahora, las únicas utilizadas son los puntos de recomendación, las cuales separan el texto y la imagen que lleva el diálogo.
 */

public class Datos {

    public final static ArrayList<Punto> PUNTOS = new ArrayList<Punto>() {
        {
            add(new Punto(1,"Punto1",null,-85.7151,10.95148333,50,new int[]{R.drawable.01-1 R.drawable.01-2 R.drawable.01-3},0,R.raw.1bL,0));
            add(new Punto(2,"Bajo Rojo",null,-85.73363333,10.95708333,50,new int[]{R.drawable.02-1},0,R.raw.2bL,0));
            add(new Punto(3,"Bahia Junquillal",null,-85.72713896,10.96987988,50,new int[]{R.drawable.03-1},0,R.raw.3bL,0));
            add(new Punto(4,"Isla Mu��ecos",null,-85.71780711,10.9777046,50,"new int[]{R.drawable.04-1, R.drawable.04-3}",0,R.raw.4bL,0));
            add(new Punto(5,"Punto5",null,-85.71779651,10.98176183,50,"new int[]{R.drawable.05-1, R.drawable.05-2}",0,R.raw.5bL,0));
            add(new Punto(6,"Punto6",null,-85.71907908,10.98217438,50,"new int[]{R.drawable.06-1, R.drawable.06-2}",0,R.raw.6bL,0));
            add(new Punto(7,"Punto7",null,-85.7189,10.9839,50,new int[]{R.drawable.07-1},0,R.raw.7bL,0));
            add(new Punto(8,"Punto8",null,-85.748105,11.00168333,50,"new int[]{R.drawable.08-1, R.drawable.08-2, R.drawable.08-2a}",0,R.raw.8bL,0));
            add(new Punto(9,"Punto9",null,-85.74858333,11.03613333,50,new int[]{R.drawable.09-1},0,R.raw.9bL,0));
            add(new Punto(10,"Punto10",null,-85.73871667,11.03813333,50,"new int[]{R.drawable.10-1, R.drawable.10-1a}",0,R.raw.10bL,0));
            add(new Punto(11,"Punto11",null,-85.7417,11.04481667,50,"new int[]{R.drawable.11-1, R.drawable.11-2}",0,R.raw.11bL,0));
            add(new Punto(12,"Punto12",null,-85.73806667,11.04623333,50,"new int[]{R.drawable.12-1, R.drawable.12-2}",0,R.raw.12bL,0));
            add(new Punto(13,"Punto13",null,-85.72666667,11.04883333,50,new int[]{R.drawable.13-1},0,R.raw.13bL,0));
            add(new Punto(14,"Punto14",null,-85.7219,11.04576667,50,"new int[]{R.drawable.14-1, R.drawable.14-2, R.drawable.14-3}",0,R.raw.14bL,0));
            add(new Punto(15,"Punto15",null,-85.70835,11.04653333,50,new int[]{R.drawable.15-1},0,R.raw.15bL,0));
            add(new Punto(16,"Punto16",null,-85.70758198,11.04667491,50,new int[]{R.drawable.16-1},0,R.raw.16bL,0));
            add(new Punto(17,"Punto17",null,-85.70738301,11.04695583,50,"new int[]{R.drawable.17-1, R.drawable.17-2, R.drawable.17-3}",0,R.raw.17bL,0));
            add(new Punto(18,"Punto18",null,-85.7072228,11.04721267,50,new int[]{R.drawable.18-1},0,R.raw.18bL,0));
            add(new Punto(19,"Punto19",null,-85.70713333,11.0489,50,new int[]{R.drawable.19-1},0,R.raw.19bL,0));
            add(new Punto(20,"Punto20",null,-85.70726667,11.04898333,50,new int[]{R.drawable.20-1},0,R.raw.20bL,0));
            add(new Punto(21,"Punto21",null,-85.70728333,11.04903333,50,new int[]{R.drawable.21-1},0,R.raw.21bL,0));
            add(new Punto(22,"Punto22",null,-85.70746667,11.04913333,50,"new int[]{R.drawable.22-1, R.drawable.22-1a}",0,R.raw.22bL,0));
            add(new Punto(23,"Punto23",null,-85.70773333,11.04918333,50,new int[]{R.drawable.23-1},0,R.raw.23bL,0));
            add(new Punto(24,"Punto24",null,-85.7082,11.04926667,50,"new int[]{R.drawable.24-1, R.drawable.24-1a}",0,R.raw.24bL,0));
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
