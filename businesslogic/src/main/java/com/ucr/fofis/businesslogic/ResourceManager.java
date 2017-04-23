package com.ucr.fofis.businesslogic;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by enrico on 4/23/17.
 * Singleton class. Should be instantiated from the Application class.
 */
public class ResourceManager {
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
        this.context = context;
        instance = this;
    }

    /**
     * Finds a list of random images for the carousel.
     *
     * @param N the maximum amount of images to find.
     * @return a list with the image resource ids.
     */
    public List<Integer> getCarouselImages(int N) {

    }

    /**
     * Finds an image resource by name.
     *
     * @param name the name of the image.
     * @return the id, -1 if the resource is not found.
     */
    public int getImageFromName(String name) {

    }

    /**
     * Finds the associated id for the image resources for the point N.
     *
     * @param N the point.
     * @return a list of identifiers for the image resources.
     */
    public List<Integer> getImagesFromPoint(int N) {
        List<Integer> list = new ArrayList<>();

        return list;
    }
}
