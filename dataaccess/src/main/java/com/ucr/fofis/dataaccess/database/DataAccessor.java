package com.ucr.fofis.dataaccess.database;

import android.content.Context;

/**
 * Helper class to access stored data.
 *
 * Created by enrico on 4/20/17.
 */
public final class DataAccessor {
    private static Context context;

    /**
     * Initializes the context for this class.
     *
     * @param c the context.
     */
    public static void init(Context c) {
        context = c;
    }

    /**
     * Returns the audio id associated to the file name.
     *
     * @param name the file name.
     * @return the resource id for the raw file.
     */
    public static int getRawId(String name) {
        int resourceId = context.getResources().getIdentifier(name, "raw", context.getPackageName());
        return resourceId;
    }

    /**
     * Returns the image id associated to the file name.
     *
     * @param name the file name.
     * @return the resource id for the raw file.
     */
    public static int getImageId(String name) {
        int resourceId = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
        return resourceId;
    }
}
