package com.ucr.fofis.businesslogic;

import android.content.Context;

import com.ucr.fofis.dataaccess.database.DataAccessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by enrico on 4/23/17.
 * Singleton class. Should be instantiated from the Application class.
 */
public class ResourceManager {
    List<List<Integer>> pointsMap = new ArrayList<>();
    List<Integer> carouselMap = new ArrayList<>();

    /**
     * The current application context.
     */
    static Context context;

    /**
     * The instance of this class.
     */
    static ResourceManager instance;

    /**
     * Creates an instance of this class with the application context.
     *
     * @param context the application context.
     * @throws Exception if attempting to instantiate this class more than once.
     */
    public ResourceManager(Context context) throws Exception {
        if (instance != null) throw new Exception("Trying to instantiate singleton class more than once");
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

        // Initialize maps
        pointsMap.add(null); // index 0 is null, 1-based list
        pointsMap.add(1, new ArrayList<Integer>() {
            {
                add(getImageFromName("punto_01_2"));
            }
        });
        pointsMap.add(2, new ArrayList<Integer>() {
            {

            }
        });
        pointsMap.add(3, new ArrayList<Integer>() {
            {

            }
        });
        pointsMap.add(4, new ArrayList<Integer>() {
            {

            }
        });
        pointsMap.add(5, new ArrayList<Integer>() {
            {
                add(getImageFromName("punto_05_1"));
                add(getImageFromName("punto_05_2"));
            }
        });
        pointsMap.add(6, new ArrayList<Integer>() {
            {
                add(getImageFromName("punto_06_1_muneco"));
                add(getImageFromName("punto_06_2"));
            }
        });
        pointsMap.add(7, new ArrayList<Integer>() {
            {

            }
        });
        pointsMap.add(8, new ArrayList<Integer>() {
            {

            }
        });
        pointsMap.add(9, new ArrayList<Integer>() {
            {

            }
        });
        pointsMap.add(10, new ArrayList<Integer>() {
            {
                add(getImageFromName("punto_10_1"));
            }
        });
        pointsMap.add(11, new ArrayList<Integer>() {
            {
                add(getImageFromName("punto_11_1"));
            }
        });
        pointsMap.add(12, new ArrayList<Integer>() {
            {
                add(getImageFromName("punto_12_1"));
            }
        });
        pointsMap.add(13, new ArrayList<Integer>() {
            {

            }
        });
        pointsMap.add(14, new ArrayList<Integer>() {
            {
                add(getImageFromName("punto_14_1"));
            }
        });
        pointsMap.add(15, new ArrayList<Integer>() {
            {
                add(getImageFromName("punto_15_1"));
            }
        });
        pointsMap.add(16, new ArrayList<Integer>() {
            {
                add(getImageFromName("punto_16_2"));
            }
        });
        pointsMap.add(17, new ArrayList<Integer>() {
            {
                add(getImageFromName("punto_17_1"));
            }
        });
        pointsMap.add(18, new ArrayList<Integer>() {
            {
                add(getImageFromName("punto_18_1"));
            }
        });
        pointsMap.add(19, new ArrayList<Integer>() {
            {
                add(getImageFromName("punto_19_1"));
            }
        });
        pointsMap.add(20, new ArrayList<Integer>() {
            {
                add(getImageFromName("punto_20_1"));
            }
        });
        pointsMap.add(21, new ArrayList<Integer>() {
            {

            }
        });
        pointsMap.add(22, new ArrayList<Integer>() {
            {
                add(getImageFromName("punto_22_1"));
                add(getImageFromName("punto_22_1a"));
            }
        });
        pointsMap.add(23, new ArrayList<Integer>() {
            {
                add(getImageFromName("punto_23_1"));
            }
        });
        pointsMap.add(24, new ArrayList<Integer>() {
            {
                add(getImageFromName("punto_24_1"));
                add(getImageFromName("punto_24_1a"));
            }
        });
    }

    /**
     * Finds a list of random images for the carousel.
     *
     * @param N the maximum amount of images to find.
     * @return a list with the image resource ids.
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
     * Determines if a vector has a specific value.
     *
     * @param vector the vector.
     * @param value the value to find in the vector.
     * @return true if the value exists, otherwise false.
     */
    private boolean hasValue(int[] vector, int value) {
        for (int i = 0; i < vector.length; i++) {
            if (vector[i] == value) return true;
        }
        return false;
    }

    /**
     * Finds an image resource by name.
     *
     * @param name the name of the image.
     * @return the id, -1 if the resource is not found.
     */
    public int getImageFromName(String name) {
        if (isInitialized()) {
            int id = DataAccessor.getImageId(name);
            return id;
        }
        return -1;
    }

    /**
     * Finds the associated id for the image resources for the point N.
     *
     * @param N the point.
     * @return a list of identifiers for the image resources.
     */
    public List<Integer> getImagesFromPoint(int N) {
        if (isInitialized()) {
            if (N > 0 && N < pointsMap.size()) {
                return pointsMap.get(N);
            }
        }
        return null;
    }

    private boolean isInitialized() {
        return instance != null;
    }

    public static ResourceManager getInstance() {
        return instance;
    }
}
