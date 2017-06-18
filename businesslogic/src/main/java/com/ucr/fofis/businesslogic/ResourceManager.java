package com.ucr.fofis.businesslogic;

import android.content.Context;

import com.ucr.fofis.dataaccess.database.DataAccessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Creada por Enrico 4/23/17. <br/>
 * Clase de tipo Singleton, debería inicializarse dede la Clase com.ucr.fofis.geoapp.Application.GeoApp.java
 */
public class ResourceManager {
    List<List<Integer>> pointsMap = new ArrayList<>();
    List<Integer> carouselMap = new ArrayList<>();

    /**
     * El contexto de aplicación actual
     */
    static Context context;

    /**
     * La única instancia de esta clase.
     */
    static ResourceManager instance;

    /**
     * Crea una instancia de esta clase a partir del Contexto
     *
     * @param context contexto de aplicación
     * @throws Exception Si se intenta instanciar más de una vez..
     */
    public ResourceManager(Context context) throws Exception {
        if (instance != null) throw new Exception("Se está intantando instanciarl Singleton más de una vex");
        DataAccessor.init(context);
        this.context = context;
        instance = this;

        carouselMap.add(getImageFromName("carousel_01"));
        carouselMap.add(getImageFromName("carousel_02"));
        carouselMap.add(getImageFromName("carousel_03"));
        carouselMap.add(getImageFromName("carousel_04"));
        carouselMap.add(getImageFromName("arco_en_roca"));
        carouselMap.add(getImageFromName("el_gallito"));
        carouselMap.add(getImageFromName("estratos_descartes"));
        carouselMap.add(getImageFromName("macizo_orosi"));

    }

    /**
     * Encuentra una lista aleatoria de las imágenes del carrusel
     *
     * @param N cantidad máxima de imágenes.
     * @return una lista con los ids de las imágenes.
     */
    public int[] getCarouselImages(int N) {
        if (isInitialized() && N > 0) {
            int[] chosen = new int[N];
            Random random = new Random();
            for (int i = 0; i < N; i++) {
                int value;
                do {
                    value = random.nextInt(carouselMap.size());
                    value = carouselMap.get(value);
                } while (hasValue(chosen, value));
                chosen[i] = value;
            }
            return chosen;
        }
        return null;
    }

    /**
     * Determina si el vector tiene un valor determinado
     *
     * @param vector el vector.
     * @param value el valor a encontrar en el vector.
     * @return true si sí existe el valor, false si no.
     */
    private boolean hasValue(int[] vector, int value) {
        for (int i = 0; i < vector.length; i++) {
            if (vector[i] == value) return true;
        }
        return false;
    }

    /**
     * Encuentra el id de una imagen por nombre.
     *
     * @param name el nombre de la imagen.
     * @return el id, -1 si no encuentra el recurso.
     */
    public int getImageFromName(String name) {
        if (isInitialized()) {
            int id = DataAccessor.getImageId(name);
            return id;
        }
        return -1;
    }



    private boolean isInitialized() {
        return instance != null;
    }

    public static ResourceManager getInstance() {
        return instance;
    }
}
