package com.ucr.fofis.geoapp;

import android.app.*;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ucr.fofis.dataaccess.database.Datos;
import com.ucr.fofis.dataaccess.entity.Recomendacion;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase para mostrar el diálogo de las Recomendaciones de seguridad.
 * @author rapuc
 */
public class RecommendationDialog extends DialogFragment implements View.OnClickListener{

    /**
     * Título del diálogo
     */
    private TextView title;

    /**
     * El numero de páginas de las recomendaciones.
     */
    private static final int NUM_PAGES = 5;

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
    public static List<Recomendacion> recomendaciones = new ArrayList<>(0);

    /**
     * índice de la lista de {@link Recomendacion Recomendaciones} que corresponde a la Recomendación actualmente mostrada.
     */
    private int index;

    public RecommendationDialog() {
    }

    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.recommmendation_dialog, container);
        android.support.v4.app.FragmentManager fm = getChildFragmentManager();

        title = (TextView) view.findViewById(R.id.recommendation_dialog_title);
        viewPager = (ViewPager)view.findViewById(R.id.recommendation_dialog_viewpager);
        mPagerAdapter = new MyPagerAdapter(fm);
        viewPager.setAdapter(mPagerAdapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title.setText("Recomendaciones");
        recomendaciones = Datos.RECOMENDACIONES;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {

    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = recomendaciones.size();

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
            return FirstFragment.newInstance(recomendaciones.get(position));
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

    }
}
