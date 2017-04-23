package com.ucr.fofis.geoapp;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.ucr.fofis.dataaccess.database.Datos;
import com.ucr.fofis.dataaccess.entity.Recomendacion;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase para mostrar el diálogo de las Recomendaciones de seguridad.
 * @author rapuc
 */
public class RecommendationDialog extends Dialog implements View.OnClickListener{

    /**
     * Título del diálogo
     */
    private TextView title;

    /**
     * Viewpager para moverse entre recomendaciones
     */

    private ViewPager viewPager;

    /**
     * Actividad en la que se encuentra el diálogo
     */
    private Activity mActivity;

    /**
     * Lista de {@link Recomendacion Recomendaciones} para llenar el ViewPager
     */
    private List<Recomendacion> recomendaciones = new ArrayList<>(0);

    /**
     * índice de la lista de {@link Recomendacion Recomendaciones} que corresponde a la Recomendación actualmente mostrada.
     */
    private int index;

    public RecommendationDialog(Activity activity) {
        super(activity);
        this.mActivity = activity;
    }

    /**
     * Inicializa todos los componentes del diálogo
     * @param dialog the created dialog.
     */
    @Override
    protected void onCreateDialog(final android.app.Dialog dialog) {
        title = (TextView) dialog.findViewById(R.id.recommendation_dialog_title);
        title.setText("Recomendaciones");
        viewPager = (ViewPager)dialog.findViewById(R.id.recommendation_dialog_viewpager);
        recomendaciones = Datos.RECOMENDACIONES;
    }

    @Override
    public void onClick(View v) {

    }
}
