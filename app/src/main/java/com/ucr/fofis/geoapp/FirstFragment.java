package com.ucr.fofis.geoapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.Fragment;

import com.ucr.fofis.dataaccess.entity.Recomendacion;

/**
 * Created by Esteban on 23/04/2017.
 */

public class FirstFragment extends Fragment {
    // Store instance variables
    private String title;
    private int imagenId;

    // newInstance constructor for creating fragment with arguments
    public static FirstFragment newInstance(Recomendacion rc) {
        FirstFragment fragmentFirst = new FirstFragment();
        Bundle args = new Bundle();
        args.putString("texto", rc.getTexto());
        args.putInt("imagen", rc.getImagen());
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imagenId = getArguments().getInt("imagen", 0);
        title = getArguments().getString("texto");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recommendation_fragment, container, false);
        TextView tvLabel = (TextView) view.findViewById(R.id.recommendation_fragment_text);
        ImageView imagen = (ImageView) view.findViewById(R.id.recommendation_fragment_imagen);
        tvLabel.setText(title);
        imagen.setImageResource(imagenId);
        return view;
    }
}
