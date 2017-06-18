package com.ucr.fofis.dataaccess.database;

import android.content.Context;

/**
 * Helper para acceder a las imágenes. <br/>
 *
 * Created by enrico on 4/20/17.
 */
public final class DataAccessor {
    private static Context context;


    public static void init(Context c) {
        context = c;
    }

    /**
     * Devuelve el id asociado a un nombre de imagen.
     *
     * @param name el nombre.
     * @return el id.
     */
    public static int getImageId(String name) {
        int resourceId = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
        return resourceId;
    }
}
