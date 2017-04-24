package com.ucr.fofis.geoapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.DialogFragment;

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
     * El numero de páginas de las recomendaciones.
     */
    private static final int NUM_PAGES = 3;

    /**
     * Viewpager para moverse entre recomendaciones
     */

    private ViewPager viewPager;

    /**
     * Proveedor de páginas para el view pager.
     */
    private PagerAdapter mPagerAdapter;

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

        viewPager = (ViewPager) dialog.findViewById(R.id.recommendation_dialog_viewpager);
        viewPager.setAdapter(mPagerAdapter);
    }

    @Override
    public void onClick(View v) {

    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 3;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public FirstFragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return FirstFragment.newInstance(0, "Page # 1");
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return FirstFragment.newInstance(0, "Page # 1");
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

    }
}
