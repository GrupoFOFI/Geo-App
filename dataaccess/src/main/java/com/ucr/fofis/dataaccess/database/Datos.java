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
            add(new Punto(1,"Secuencia de estratos sedimentarios",null, new GeoPoint(10.9514833333333,-85.7151),300.0d,
                    new Resource[]{
                        new Resource(R.drawable.punto_01_1,"Secuencia de estratos rocosos"),
                        new Resource(R.drawable.punto_01_2,"Estatos de la Formación Junquillal"),
                        new Resource(R.drawable.punto_01_3,"Estatos de la Formación Junquillal"),
                        new Resource(R.drawable.punto_01_4,"Estatos de la Formación Junquillal"),
                        new Resource(R.drawable.punto_01_5,"Estatos de la Formación Junquillal"),
                        new Resource(R.drawable.punto_01_6,"Estatos de la Formación Junquillal"),
                        new Resource(R.drawable.punto_01_7,"Estatos de la Formación Junquillal")
                    },
                    new Resource(0,""),
                    new Resource[]{new Resource(R.raw.audio1,"Los Estratos")},
                    new Resource(R.raw.anim01_6,"Los Estratos")));
            add(new Punto(2,"Formas causadas por la erosión",null, new GeoPoint(10.9570833333333,-85.7336333333333),500.0d,
                    new Resource[]{
                            new Resource(R.drawable.punto_02_1,"Rocas sedimentarias de Bajo Rojo"),
                            new Resource(R.drawable.punto_02_2,"Una vista a la península de Santa Elena"),
                            new Resource(R.drawable.punto_02_3,"Estratificación en Isla David"),
                            new Resource(R.drawable.punto_02_4,"Isla David"),new Resource(R.drawable.punto_02_5,"Isla David"),
                            new Resource(R.drawable.punto_02_6,"Isla David"),new Resource(R.drawable.punto_02_7,"Isla David"),
                            new Resource(R.drawable.punto_02_8,"Isla David"),new Resource(R.drawable.punto_02_9,"Gradación normal"),
                            new Resource(R.drawable.punto_02_10,"Bajo Rojo"),new Resource(R.drawable.punto_02_11,"Bajo Rojo"),
                            new Resource(R.drawable.punto_02_12,"Bajo Rojo")
                    },
                    new Resource(0,""),
                    new Resource[]{new Resource(R.raw.audio2, "La Erosión")},
                    new Resource(R.raw.anim02_5,"La Erosión")));
            add(new Punto(3,"El sinclinal de Bahía Junquillal",null, new GeoPoint(10.9698798844808,-85.7271389630961),600.0d,
                    new Resource[]{
                            new Resource(R.drawable.punto_03_1,"Una vista de Bahía Junquillal")
                    },
                    new Resource(0,""),
                    new Resource[]{new Resource(R.raw.audio3, "Los Sinclinales")},
                    new Resource(R.raw.anim03_4,"Los Sinclinales")));
            add(new Punto(4,"Isla Muñecos y alrededores",null, new GeoPoint(10.9777046049834,-85.7178071072068),200.0d,
                    new Resource[]{
                            new Resource(R.drawable.punto_04_1,"Arrecifes coralinos fosilizados"),
                            new Resource(R.drawable.punto_04_3,"Arrecife de coral")
                    }, new Resource(0,""),
                    new Resource[]{new Resource(R.raw.audio4, "Los Arrecifes y las Calizas")},
                    new Resource(0,"")));
            add(new Punto(5,"Isla Muñecos y sus corales",null, new GeoPoint(10.98176182858,-85.7177965097929),50.0d,
                    new Resource[]{
                            new Resource(R.drawable.punto_05_1,"Fragmentos de corales fosilizados"),
                            new Resource(R.drawable.punto_05_2,"Fragmentos de corales fosilizados")
                    },
                    new Resource(0,""),
                    new Resource[]{new Resource(R.raw.audio5, "Isla Muñecos")},
                    new Resource(0,"")));
            add(new Punto(6,"Isla Muñecos",null, new GeoPoint(10.9821743821365,-85.7190790800442),50.0d,
                    new Resource[]{
                            new Resource(R.drawable.punto_06_1,"Estructuras verticales por erosión"),
                            new Resource(R.drawable.punto_06_2,"El Muñeco")
                    },
                    new Resource(R.raw.video06_3,"Una vista de Bahía Junquillal"),
                    new Resource[]{new Resource(R.raw.audio6, "Los Muñecos")},
                    new Resource(0,"")));
            add(new Punto(7,"Isla Muñecos",null, new GeoPoint(10.9839,-85.7189),50.0d,
                    new Resource[]{
                            new Resource(R.drawable.punto_07_1,"Plegamiento de estratos de roca"),
                            new Resource(R.drawable.punto_07_2,"Isla Muñecos"),
                            new Resource(R.drawable.punto_07_3,"Isla Muñecos"),
                            new Resource(R.drawable.punto_07_4,"Isla Muñecos"),
                            new Resource(R.drawable.punto_07_5,"Isla Muñecos"),
                            new Resource(R.drawable.punto_07_6,"Isla Muñecos"),
                            new Resource(R.drawable.punto_07_7,"Fragmento de coral"),
                            new Resource(R.drawable.punto_07_8,"Fragmento de coral"),
                            new Resource(R.drawable.punto_07_9,"El Muñeco"),
                            new Resource(R.drawable.punto_07_10,"El Muñeco"),
                            new Resource(R.drawable.punto_07_11,"El Muñeco"),
                            new Resource(R.drawable.punto_07_12,"El Muñeco"),
                            new Resource(R.drawable.punto_07_13,"El Muñeco"),
                            new Resource(R.drawable.punto_07_14,"Disolución de carbonato de calcio"),
                            new Resource(R.drawable.punto_07_15,"Arrecife de coral fosilizado")
                    },
                    new Resource(0,""),
                    new Resource[]{new Resource(R.raw.audio7, "¿Rocas dobladas?")},
                    new Resource(0,"")));
            add(new Punto(8,"Isla Despensa (Isla Loro) y la estratificación cruzada",null, new GeoPoint(11.0016833333333,-85.748105),200.0d,
                    new Resource[]{
                            new Resource(R.drawable.punto_08_1,"Estratificación cruzada"),
                            new Resource(R.drawable.punto_08_2,"Una vista de Bahía Junquillal"),
                            new Resource(R.drawable.punto_08_5,"Isla Despensa"),
                            new Resource(R.drawable.punto_08_6,"Isla Despensa"),
                            new Resource(R.drawable.punto_08_7,"Isla Despensa"),
                            new Resource(R.drawable.punto_08_8,"Isla Despensa"),
                            new Resource(R.drawable.punto_08_9,"Isla Despensa"),
                            new Resource(R.drawable.punto_08_10,"Isla Despensa"),
                            new Resource(R.drawable.punto_08_11,"Isla Despensa"),
                            new Resource(R.drawable.punto_08_12,"Ichnofósiles"),
                            new Resource(R.drawable.punto_08_13,"Gradación normal"),
                            new Resource(R.drawable.punto_08_14,"Formas generadas por erosión"),
                            new Resource(R.drawable.punto_08_15,"Formas generadas por erosión"),
                            new Resource(R.drawable.punto_08_16,"Formas generadas por erosión"),
                            new Resource(R.drawable.punto_08_17,"Estratificación cruzada"),
                            new Resource(R.drawable.punto_08_18,"Conglomerado"),
                            new Resource(R.drawable.punto_08_19,"Conglomerado")
                    },
                    new Resource(R.raw.video08_3,"Relieve producto de la erosión cerca de la Isla Despensa (Isla Loro)"),
                    new Resource[]{new Resource(R.raw.audio8, "Estratificación Cruzada")},
                    new Resource(R.raw.anim08_2,"Estratificación Cruzada")));
            add(new Punto(9,"La cordillera volcánica de Guanacaste",null, new GeoPoint(11.0361333333333,-85.7485833333333),500.0d,
                    new Resource[]{
                            new Resource(R.drawable.punto_09_1,"Vista del complejo Orosí - Cacao")
                    },
                    new Resource(R.raw.video09_6,"Relieve producto de la erosión"),
                    new Resource[]{new Resource(R.raw.audio9, "La Subducción y otros procesos")},
                    new Resource(R.raw.anim09_5,"La Subducción y otros procesos")));
            add(new Punto(10,"Bahía Jobo y el secreto del dragón",null, new GeoPoint(11.0381333333333,-85.7387166666666),100.0d,
                    new Resource[]{
                            new Resource(R.drawable.punto_10_1,"Pliegue sinsedimentario"),
                            new Resource(R.drawable.punto_10_1a,"El dragón de roca")
                    },
                    new Resource(0,""),
                    new Resource[]{new Resource(R.raw.audio10, "Pliegues sinsedimentarios")},
                    new Resource(0,"")));
            add(new Punto(11,"El Gallito y la erasión",null, new GeoPoint(11.0448166666666,-85.7417),200.0d,
                    new Resource[]{
                            new Resource(R.drawable.punto_11_1,"Rocas rojas por efectos de la meteorización"),
                            new Resource(R.drawable.punto_11_2,"El gallito"),
                            new Resource(R.drawable.punto_11_3,"Rocas ordenadas en capas"),
                            new Resource(R.drawable.punto_11_4,"Relictos de erosión"),
                            new Resource(R.drawable.punto_11_5,"Relictos de erosión"),
                            new Resource(R.drawable.punto_11_6,"El gallito"),
                            new Resource(R.drawable.punto_11_7,"El gallito"),
                            new Resource(R.drawable.punto_11_8,"El gallito"),
                            new Resource(R.drawable.punto_11_9,"El gallito"),
                            new Resource(R.drawable.punto_11_10,"El gallito"),
                            new Resource(R.drawable.punto_11_11,"El gallito"),
                            new Resource(R.drawable.punto_11_12,"El gallito"),
                            new Resource(R.drawable.punto_11_13,"El gallito"),
                            new Resource(R.drawable.punto_11_14,"El gallito"),
                            new Resource(R.drawable.punto_11_15,"De cerca en El gallito"),
                            new Resource(R.drawable.punto_11_16,"De cerca en El gallito"),
                            new Resource(R.drawable.punto_11_17,"De cerca en El gallito"),
                            new Resource(R.drawable.punto_11_18,"De cerca en El gallito"),
                            new Resource(R.drawable.punto_11_19,"De cerca en El gallito"),
                            new Resource(R.drawable.punto_11_20,"Concreción")
                    },
                    new Resource(0,""),
                    new Resource[]{new Resource(R.raw.audio11, "La Erosión")},
                    new Resource(0,"")));
            add(new Punto(12,"¿Dunas en Costa Rica?",null, new GeoPoint(11.0462333333333,-85.7380666666666),200.0d,
                    new Resource[]{
                            new Resource(R.drawable.punto_12_1,"Duna costera")
                    },
                    new Resource(0,""),
                    new Resource[]{new Resource(R.raw.audio12, "Las Dunas Costeras")},
                    new Resource(R.raw.anim12_3,"Las Dunas Costeras")));
            add(new Punto(13,"Los ricones de Bahía Salinas",null, new GeoPoint(11.0488333333333,-85.7266666666666),100.0d,
                    new Resource[]{
                            new Resource(R.drawable.punto_13_1,"Conglomerado")
                    },
                    new Resource(0,""),
                    new Resource[]{new Resource(R.raw.audio13, "Los Conglomerados")},
                    new Resource(0,"")));
            add(new Punto(14,"Upwelling: Riquezas submarinas de la costa Pacífica de Costa Rica",null, new GeoPoint(11.0457666666666,-85.7219),200.0d,
                    new Resource[]{
                            new Resource(R.drawable.punto_14_1,"Duna costera"),
                            new Resource(R.drawable.punto_14_2,"Rocas ordenadas en capas")
                    },
                    new Resource(R.raw.video14_4,"Duna activa"),
                    new Resource[]{new Resource(R.raw.audio14, "El Upwelling")},
                    new Resource(R.raw.anim14,"El Upwelling")));
            add(new Punto(15,"La isla Bolaños: el desembarco",null, new GeoPoint(11.0466881500087,-85.7075191718204),100.0d,
                    new Resource[]{
                            new Resource(R.drawable.punto_15_1,"Arenas y clastos dispersos por acción del oleaje"),
                            new Resource(R.drawable.punto_15_2,"Una vista de Isla Bolaños"),
                            new Resource(R.drawable.punto_16_2,"Mapa geológico de Isla  Bolaños"),
                            new Resource(R.drawable.punto_17_1,"Secuencia de estratos rocosos"),
                            new Resource(R.drawable.punto_18_1,"Pares conjugados"),
                            new Resource(R.drawable.punto_18_2,"Isla Bolaños"),
                            new Resource(R.drawable.punto_18_3,"Isla Bolaños"),
                            new Resource(R.drawable.punto_18_4,"Otra vista de Isla Bolaños"),
                            new Resource(R.drawable.punto_18_5,"Depósito que puede llegar a formar un conglomerado")
                    },
                    new Resource(R.raw.video15_2,"Isla Bolaños"),
                    new Resource[]{
                            new Resource(R.raw.audio15, "Efectos del oleaje"),
                            new Resource(R.raw.audio16, "Las Rocas de Isla Bolaños"),
                            new Resource(R.raw.audio17, "La Formación Junquillal"),
                            new Resource(R.raw.audio18, "Los Pares Conjugados")
                    },
                    new Resource(0,"")));
            add(new Punto(19,"Adentrándose en Isla Bolaños",null, new GeoPoint(11.0491333333333,-85.7074666666666),50.0d,
                    new Resource[]{
                            new Resource(R.drawable.punto_19_1,"Estratificación cruzada curva"),
                            new Resource(R.drawable.punto_19_1_a,"Laminaciones finas"),
                            new Resource(R.drawable.punto_19_1_b,"Ondulitas"),
                            new Resource(R.drawable.punto_19_1_c,"Concreciones"),
                            new Resource(R.drawable.punto_19_1_d,"Concreciones"),
                            new Resource(R.drawable.punto_19_1_e,"¡Carbón en rocas?"),
                            new Resource(R.drawable.punto_19_1_f,"Ichnofósiles"),
                            new Resource(R.drawable.punto_19_1_g,"Ichnofosiles"),
                            new Resource(R.drawable.punto_19_2,"Ondulitas"),
                            new Resource(R.drawable.punto_19_3,"Laminación paralela"),
                            new Resource(R.drawable.punto_19_4,"Ondulitas"),
                            new Resource(R.drawable.punto_19_5,"Estratos de la formación Junquillal"),
                            new Resource(R.drawable.punto_19_6,"Estratificación paralela fina"),
                            new Resource(R.drawable.punto_19_7,"Concreción"),
                            new Resource(R.drawable.punto_19_2,"Concreción")
                    },
                    new Resource(0,""),
                    new Resource[]{
                            new Resource(R.raw.audio19_1,"Ondulitas"),
                            new Resource(R.raw.audio19_1cyd, "Concreciones"),
                            new Resource(R.raw.audio19_1e, "¿Carbón en rocas?"),
                            new Resource(R.raw.audio19_1fyg, "Ichnofósiles")
                    },
                    new Resource(0,"")));
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
            add(new Palabra(R.raw.arenisca,"Arenisca","Roca sedimentaria, en la que el tamaño de grano varía entre 0,062 y 2 mm."));
            add(new Palabra(R.raw.brecha,"Brecha","Roca compuesta por elementos de diversos tamaños de forma angulosa, dispuestos irregularmente, contenidos en una pasta o matriz."));
            add(new Palabra(R.raw.conglomerado,"Conglomerado","Roca compuesta por elementos de diversos tamaños de forma redondeada, dispuestos irregularmente, contenidos en una pasta o matriz."));
            add(new Palabra(R.raw.corales,"Corales","Son animales que comúnmente forman colonias, conformadas a su vez por cientos de zooides."));
            add(new Palabra(R.raw.erosion,"Erosión","Desgaste y modelación de la corteza terrestre causados por la acción del viento, la lluvia, los procesos fluviales, marítimos, glaciales y por la acción de los seres vivos."));
            add(new Palabra(R.raw.estrato,"Estrato","Es cada una de las capas en que se presentan divididos los sedimentos."));
            add(new Palabra(R.raw.lutita,"Lutita","Roca sedimentaria constituida por granos muy finos, de menos de 0,062 mm."));
            add(new Palabra(R.raw.meteorizacion,"Meteorización","Conjunto de procesos físicos, químicos y biológicos de alteración, que provocan la descomposición de una roca superficial."));
            add(new Palabra(R.raw.sedimento,"Sedimento","Partículas móviles depositadas por agua, viento y otros agentes de erosión."));
            add(new Palabra(R.raw.zooide,"Zooide","Individuo de una comunidad colonial que funciona como un organismo y cuya estructura difiere de la de otros organismos de su misma especie, según el papel fisiológico que desempeñe dentro del conjunto."));

        }
    };
}
