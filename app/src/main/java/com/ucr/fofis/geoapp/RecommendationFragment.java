package com.ucr.fofis.geoapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Esteban on 23/04/2017.
 * Fragment usado para cada carta dentro del ViewPager del Diálogo de Recomendaciones
 */
public class RecommendationFragment extends Fragment {
    //Titulo e imagen de la recomendacion
    private String title;
    private int imagenId;


    public RecommendationFragment() {
    }

    /**
     * Usado para mandar ids de recursos para cargar Recommendacíon
     * @param args
     */
    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        title = args.getString("title");
        imagenId = args.getInt("imageId");
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.recommendation_fragment, container, false);
        TextView tvLabel = (TextView) view.findViewById(R.id.recommendation_fragment_text);
        ImageView imagen = (ImageView) view.findViewById(R.id.recommendation_fragment_image);
        tvLabel.setText(title);
        imagen.setImageResource(imagenId);
        return view;
    }


}
