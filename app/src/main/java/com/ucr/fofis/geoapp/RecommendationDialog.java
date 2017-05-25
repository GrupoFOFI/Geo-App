package com.ucr.fofis.geoapp;

import android.app.Activity;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.synnapps.carouselview.CirclePageIndicator;
import com.ucr.fofis.dataaccess.database.Datos;
import com.ucr.fofis.dataaccess.entity.Recomendacion;

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
     * índice de la lista de {@link Recomendacion Recomendaciones} que corresponde a la Recomendación actualmente mostrada.
     */
    private int index;

    private CirclePageIndicator mTitleIndicator;
    private RelativeLayout exit;

    public interface DialogDismissInterface {
        void onDialogDismiss();
    }

    private DialogDismissInterface dialogDismissInterface;

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
        mTitleIndicator = (CirclePageIndicator) view.findViewById(R.id.circle_indicator);



        title = (TextView) view.findViewById(R.id.recommendation_dialog_title);
        title.setText("Recomendaciones de Seguridad");

        //Se inicializa el viewpager(vista de tarjetas de recomendaciones) y su adapter que devuelve los fragments(tarjetas de recomendacion)
        viewPager = (ViewPager)view.findViewById(R.id.recommendation_dialog_viewpager);
        mPagerAdapter = new MyPagerAdapter(fm);
        viewPager.setAdapter(mPagerAdapter);
        mTitleIndicator.setViewPager(viewPager);
        exit = (RelativeLayout) view.findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(dialogDismissInterface != null) {
                    dialogDismissInterface.onDialogDismiss();
                }
                getDialog().dismiss();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {

    }

    public void setDialogDismissInterface(DialogDismissInterface dialogDismissInterface){
        this.dialogDismissInterface = dialogDismissInterface;
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        //**Cantidad de paginas. Cuando lista de recomendaciones este implementada deberia recomendaciones.size() deberia ir aqui.
//        private static int NUM_ITEMS = 3;
        private static int NUM_ITEMS = Datos.RECOMENDACIONES.size();
        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        /** Returns the fragment to display for that page
         * lo construye a partir de una Recomendación sacada de Datos
         * @param position
         * @return
         */
        @Override
        public RecommendationFragment getItem(int position) {
            if(NUM_ITEMS ==0 ){
                return null;
            }else {
                Recomendacion r = Datos.RECOMENDACIONES.get(position);
                RecommendationFragment rc = new RecommendationFragment();
                Bundle args = new Bundle();
                args.putString("title", r.getTexto());
                args.putInt("imageId", r.getImagen());
                rc.setArguments(args);
                return rc;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Pagina " + position;
        }

    }


}
