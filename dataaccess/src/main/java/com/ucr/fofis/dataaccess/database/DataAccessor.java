package com.ucr.fofis.dataaccess.database;

import android.content.Context;

/**
 * Helper class to access stored data.
 *
 * Created by enrico on 4/20/17.
 */
public final class DataAccessor {
    private static Context context;

    public void init(Context context) {
        this.context = context;
    }

    /**
     * Returns the audio id associated to the file name.
     *
     * @param name the file name
     * @return the resource id for the raw file
     */
    public static int getRawId(String name) {
        int resourceId = context.getResources().getIdentifier("name", "raw", "com.ucr.fofis.dataaccess");
        return resourceId;
    }
}
