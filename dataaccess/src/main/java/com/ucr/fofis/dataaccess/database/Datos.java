package com.ucr.fofis.dataaccess.database;

import com.ucr.fofis.dataaccess.R;
import com.ucr.fofis.dataaccess.entity.Palabra;
import com.ucr.fofis.dataaccess.entity.Punto;
import com.ucr.fofis.dataaccess.entity.Recomendacion;
import com.ucr.fofis.dataaccess.entity.Resource;

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
            add(new Punto(1,"Secuencia de estratos sedimentarios",null, new GeoPoint(10.9514833333333,-85.7151),300.0d,new Resource[]{new Resource(R.drawable.punto_01_1,""), new Resource(R.drawable.punto_01_2,""), new Resource(R.drawable.punto_01_3,"")}, new Resource(0,""),new Resource[]{new Resource(R.raw.audio1b,"Los Estratos")},new Resource(R.drawable.anim01_6,"Los Estratos")));
            add(new Punto(2,"Formas causadas por la erosión",null, new GeoPoint(10.9570833333333,-85.7336333333333),500.0d,new Resource[]{new Resource(R.drawable.punto_02_1,"")}, new Resource(0,""),new Resource[]{new Resource(R.raw.audio2b, "")},new Resource(R.drawable.anim02_5,"")));
            add(new Punto(3,"El sinclinal de Bahía Junquillal",null, new GeoPoint(10.9698798844808,-85.7271389630961),600.0d,new Resource[]{new Resource(R.drawable.punto_03_1,"")}, new Resource(0,""),new Resource[]{new Resource(R.raw.audio3b, "")},new Resource(R.drawable.anim03_4,"")));
            add(new Punto(4,"Isla Muñecos y alrededores",null, new GeoPoint(10.9777046049834,-85.7178071072068),200.0d,new Resource[]{new Resource(R.drawable.punto_04_1,""), new Resource(R.drawable.punto_04_3,"")}, new Resource(0,""),new Resource[]{new Resource(R.raw.audio4b, "")},new Resource(0,"")));
            add(new Punto(5,"Isla Muñecos y sus corales",null, new GeoPoint(10.98176182858,-85.7177965097929),50.0d,new Resource[]{new Resource(R.drawable.punto_05_1,""), new Resource(R.drawable.punto_05_2,"")}, new Resource(0,""),new Resource[]{new Resource(R.raw.audio5b, "")},new Resource(0,"")));
            add(new Punto(6,"Isla Muñecos",null, new GeoPoint(10.9821743821365,-85.7190790800442),50.0d,new Resource[]{new Resource(R.drawable.punto_06_1,""), new Resource(R.drawable.punto_06_2,"")}, new Resource(R.raw.video06_3,""),new Resource[]{new Resource(R.raw.audio6b, "")},new Resource(0,"")));
            add(new Punto(7,"Isla Muñecos",null, new GeoPoint(10.9839,-85.7189),50.0d,new Resource[]{new Resource(R.drawable.punto_07_1,"")}, new Resource(0,""),new Resource[]{new Resource(R.raw.audio7b, "")},new Resource(0,"")));
            add(new Punto(8,"Punto 8",null, new GeoPoint(11.0016833333333,-85.748105),200.0d,new Resource[]{new Resource(R.drawable.punto_08_1,""), new Resource(R.drawable.punto_08_2,""), new Resource(R.drawable.punto_08_2a,"")}, new Resource(R.raw.video08_3,""),new Resource[]{new Resource(R.raw.audio8b, "")},new Resource(0,"")));
            add(new Punto(9,"Punto 9",null, new GeoPoint(11.0361333333333,-85.7485833333333),500.0d,new Resource[]{new Resource(R.drawable.punto_09_1,"")}, new Resource(R.raw.video09_6,""),new Resource[]{new Resource(R.raw.audio9b, "")},new Resource(0,"")));
            add(new Punto(10,"Punto 10",null, new GeoPoint(11.0381333333333,-85.7387166666666),100.0d,new Resource[]{new Resource(R.drawable.punto_10_1,""), new Resource(R.drawable.punto_10_1a,"")}, new Resource(0,""),new Resource[]{new Resource(R.raw.audio10b, "")},new Resource(0,"")));
            add(new Punto(11,"Punto 11",null, new GeoPoint(11.0448166666666,-85.7417),200.0d,new Resource[]{new Resource(R.drawable.punto_11_1,""), new Resource(R.drawable.punto_11_2,"")}, new Resource(0,""),new Resource[]{new Resource(R.raw.audio11b, "")},new Resource(0,"")));
            add(new Punto(12,"Punto 12",null, new GeoPoint(11.0462333333333,-85.7380666666666),200.0d,new Resource[]{new Resource(R.drawable.punto_12_1,""), new Resource(R.drawable.punto_12_2,"")}, new Resource(0,""),new Resource[]{new Resource(R.raw.audio12b, "")},new Resource(R.drawable.anim12_3,"")));
            add(new Punto(13,"Punto 13",null, new GeoPoint(11.0488333333333,-85.7266666666666),100.0d,new Resource[]{new Resource(R.drawable.punto_13_1,"")}, new Resource(0,""),new Resource[]{new Resource(R.raw.audio13b, "")},new Resource(0,"")));
            add(new Punto(14,"Punto 14",null, new GeoPoint(11.0457666666666,-85.7219),200.0d,new Resource[]{new Resource(R.drawable.punto_14_1,""), new Resource(R.drawable.punto_14_2,""), new Resource(R.drawable.punto_14_3,"")}, new Resource(R.raw.video14_4,""),new Resource[]{new Resource(R.raw.audio14b, "")},new Resource(0,"")));
            add(new Punto(15,"Punto 15",null, new GeoPoint(11.0466881500087,-85.7075191718204),100.0d,new Resource[]{new Resource(R.drawable.punto_15_1,""), new Resource(R.drawable.punto_16_1,""), new Resource(R.drawable.punto_17_1,""), new Resource(R.drawable.punto_17_2,""), new Resource(R.drawable.punto_17_3,""), new Resource(R.drawable.punto_18_1,"")}, new Resource(R.raw.video15_2,""),new Resource[]{new Resource(R.raw.audio17b, "")},new Resource(0,"")));
            add(new Punto(19,"Punto 16",null, new GeoPoint(11.0491333333333,-85.7074666666666),50.0d,new Resource[]{new Resource(R.drawable.punto_19_1,""), new Resource(R.drawable.punto_20_1,""), new Resource(R.drawable.punto_21_1,""), new Resource(R.drawable.punto_22_1,""), new Resource(R.drawable.punto_22_1a,""), new Resource(R.drawable.punto_23_1,""), new Resource(R.drawable.punto_24_1,""), new Resource(R.drawable.punto_24_1a,"")}, new Resource(0,""),new Resource[]{new Resource(R.raw.audio19b,""),new Resource(R.raw.audio19_1b, ""),new Resource(R.raw.audio19_2b, ""),new Resource(R.raw.audio19_3b, ""),new Resource(R.raw.audio19_4b, ""),new Resource(R.raw.audio19_5b, "")},new Resource(0,"")));

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


    public final static ArrayList<Palabra> GLOSARIO = new ArrayList<Palabra>() {
        {
            add(new Palabra(R.raw.Arenisca,"Arenisca","Roca sedimentaria, en la que el tamaño de grano varía entre 0,062 y 2 mm."));
            add(new Palabra(R.raw.Brecha,"Brecha","Roca compuesta por elementos de diversos tamaños de forma angulosa, dispuestos irregularmente, contenidos en una pasta o matriz."));
            add(new Palabra(R.raw.Conglomerado,"Conglomerado","Roca compuesta por elementos de diversos tamaños de forma redondeada, dispuestos irregularmente, contenidos en una pasta o matriz."));
            add(new Palabra(R.raw.Corales,"Corales","Son animales que comúnmente forman colonias, conformadas a su vez por cientos de zooides."));
            add(new Palabra(R.raw.Erosión,"Erosión","Desgaste y modelación de la corteza terrestre causados por la acción del viento, la lluvia, los procesos fluviales, marítimos, glaciales y por la acción de los seres vivos."));
            add(new Palabra(R.raw.Estrato,"Estrato","Es cada una de las capas en que se presentan divididos los sedimentos."));
            add(new Palabra(R.raw.Lutita,"Lutita","Roca sedimentaria constituida por granos muy finos, de menos de 0,062 mm."));
            add(new Palabra(R.raw.Meteorizacion,"Meteorizacion","Conjunto de procesos físicos, químicos y biológicos de alteración, que provocan la descomposición de una roca superficial."));
            add(new Palabra(R.raw.Sedimento,"Sedimento","Partículas móviles depositadas por agua, viento y otros agentes de erosión."));
            add(new Palabra(R.raw.Zooide,"Zooide","Individuo de una comunidad colonial que funciona como un organismo y cuya estructura difiere de la de otros organismos de su misma especie, según el papel fisiológico que desempeñe dentro del conjunto."));
        }
    };
}
