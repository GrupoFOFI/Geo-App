package com.ucr.fofis.geoapp.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.ucr.fofis.businesslogic.ResourceManager;
import com.ucr.fofis.dataaccess.database.Datos;
import com.ucr.fofis.dataaccess.database.Ruta;
import com.ucr.fofis.geoapp.AfterCameraActivity;
import com.ucr.fofis.geoapp.MapActivity;
import com.ucr.fofis.geoapp.R;

/**
 * Creado por Óscar el 4/19/2017.
 * Fragmento de la página principal del app con el carrusel, la introduciǿn y botones de acción
 */
public class HomeFragment extends Fragment {
    public FloatingActionButton fab;
    public Button btnStart;
    public TextView intro;


    CarouselView carouselView;

    int[] sampleImages;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        FloatingActionButton btn = (FloatingActionButton) rootView.findViewById(R.id.fab);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), MapActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        //Recibido de imagenes
        sampleImages = ResourceManager.getInstance().getCarouselImages(6);

        carouselView = (CarouselView) rootView.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);

        carouselView.setImageListener(imageListener);
        carouselView.setSlideInterval(Integer.MAX_VALUE);

        //Aquí se seteaa el texto de la introduccíon
        intro = (TextView)rootView.findViewById(R.id.introText);
        intro.setText(Ruta.DESCRIPCION);
        intro.setMovementMethod(new ScrollingMovementMethod());

        //Funcionalidad del botón de iniciar Viaje
        btnStart = (Button) rootView.findViewById(R.id.btnStart);
        btnStart.setText(R.string.btn_start);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(getActivity(),MapActivity.class);
//                i.putExtra("showRecomendation",true);
//                startActivity(i);
                Intent i = new Intent(getActivity(), AfterCameraActivity.class);
                i.putExtra("punto", Datos.PUNTOS.get(8));
                startActivity(i);

            }
        });

        //Funcionalidad de botón de ir a webapp
        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(Ruta.WEB_PAGE_URL));
                startActivity(i);
            }
        });

        return rootView;
    }



    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };
}
